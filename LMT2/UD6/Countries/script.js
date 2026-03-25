/**
 * GENERIC FETCH FUNCTION
 * Handles the request and basic error checking
 */
const fetchData = async (url) => {
    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("No results found.");
        return await response.json();
    } catch (error) {
        console.error("Fetch error:", error);
        return null;
    }
};

/**
 * ERROR HANDLING UI
 * Displays a message to the user inside the specific result container
 */
const showUIError = (containerId, message) => {
    const container = document.getElementById(containerId);
    container.innerHTML = `<p class="error-msg" style="color: #d9534f; font-weight: bold; padding: 10px; border: 1px solid #d9534f; border-radius: 5px;">⚠️ ${message}</p>`;
};

// --- 1. SEARCH COUNTRY BY NAME ---
const formNombre = document.getElementById('form-nombre');
formNombre.addEventListener('submit', async (e) => {
    e.preventDefault();
    const input = document.getElementById('input-nombre');
    const query = input.value.trim();
    const container = document.getElementById('resultado-nombre');

    if (!query) return showUIError('resultado-nombre', "Please enter a country name.");

    container.innerHTML = "<p>Searching...</p>";
    const data = await fetchData(`https://restcountries.com/v3.1/name/${query}`);
    
    if (!data) return showUIError('resultado-nombre', "Country not found. Try 'Spain' or 'Japan'.");

    const country = data[0];
    
    // Helper to extract values from dynamic objects (languages/currencies)
    const languages = country.languages ? Object.values(country.languages).join(', ') : 'N/A';
    const currencies = country.currencies ? Object.values(country.currencies).map(c => `${c.name} (${c.symbol})`).join(', ') : 'N/A';

    container.innerHTML = `
        <div class="detalle-pais" style="display: flex; gap: 20px; align-items: center; border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <img src="${country.flags.svg}" alt="Flag" style="width: 150px; box-shadow: 0 2px 5px rgba(0,0,0,0.2)">
            <div>
                <h3>${country.name.common}</h3>
                <p><strong>Official Name:</strong> ${country.name.official}</p>
                <p><strong>Capital:</strong> ${country.capital ? country.capital[0] : 'N/A'}</p>
                <p><strong>Region:</strong> ${country.region} (${country.subregion})</p>
                <p><strong>Population:</strong> ${country.population.toLocaleString()}</p>
                <p><strong>Languages:</strong> ${languages}</p>
                <p><strong>Currencies:</strong> ${currencies}</p>
            </div>
        </div>
    `;
});

// --- 2. COUNTRIES BY REGION ---
const regionButtons = document.querySelectorAll('.btn-region');
regionButtons.forEach(btn => {
    btn.addEventListener('click', async () => {
        const region = btn.getAttribute('data-region');
        const container = document.getElementById('resultado-region');
        
        container.innerHTML = "<p>Loading region...</p>";
        const data = await fetchData(`https://restcountries.com/v3.1/region/${region}`);
        
        if (!data) return showUIError('resultado-region', "Could not load region data.");

        container.innerHTML = data.map(country => `
            <div class="tarjeta" style="border: 1px solid #eee; padding: 10px; text-align: center; border-radius: 5px;">
                <img src="${country.flags.svg}" alt="Flag" style="width: 100%; height: 100px; object-fit: cover;">
                <h4>${country.name.common}</h4>
                <p>Cap: ${country.capital ? country.capital[0] : 'N/A'}</p>
                <p>Pop: ${country.population.toLocaleString()}</p>
            </div>
        `).join('');
    });
});

// --- 3. SEARCH BY CAPITAL ---
const formCapital = document.getElementById('form-capital');
formCapital.addEventListener('submit', async (e) => {
    e.preventDefault();
    const query = document.getElementById('input-capital').value.trim();
    const container = document.getElementById('resultado-capital');

    if (!query) return showUIError('resultado-capital', "Please enter a capital city.");

    const data = await fetchData(`https://restcountries.com/v3.1/capital/${query}`);
    if (!data) return showUIError('resultado-capital', "Capital not found.");

    const c = data[0];
    container.innerHTML = `
        <div class="detalle-pais" style="border-left: 5px solid #007bff; padding-left: 15px;">
            <img src="${c.flags.svg}" alt="Flag" width="60">
            <p><strong>Country:</strong> ${c.name.common}</p>
            <p><strong>Capital:</strong> ${c.capital[0]}</p>
            <p><strong>Region:</strong> ${c.region}</p>
            <a href="${c.maps.googleMaps}" target="_blank" style="color: #007bff; text-decoration: none; font-weight: bold;">📍 View on Google Maps</a>
        </div>
    `;
});

// --- 4. SEARCH BY CODE (Alpha) ---
const formCodigo = document.getElementById('form-codigo');
formCodigo.addEventListener('submit', async (e) => {
    e.preventDefault();
    const code = document.getElementById('input-codigo').value.trim();
    const container = document.getElementById('resultado-codigo');

    if (!code) return showUIError('resultado-codigo', "Enter a code (e.g., 'it', 'jp').");

    const data = await fetchData(`https://restcountries.com/v3.1/alpha/${code}`);
    if (!data) return showUIError('resultado-codigo', "Invalid country code.");

    const c = data[0];
    container.innerHTML = `
        <div class="detalle-pais">
            <img src="${c.flags.svg}" alt="Flag" width="80" style="margin-bottom: 10px;">
            <h3>${c.name.common}</h3>
            <p><strong>CCA2:</strong> ${c.cca2} | <strong>CCA3:</strong> ${c.cca3}</p>
            <p><strong>Continent:</strong> ${c.continents[0]}</p>
            <p><strong>Timezone:</strong> ${c.timezones[0]}</p>
        </div>
    `;
});

// --- 5. QUICK LIST (ALL) ---
const btnTodos = document.getElementById('btn-todos');
btnTodos.addEventListener('click', async () => {
    const container = document.getElementById('resultado-todos');
    container.innerHTML = "<p>Loading list...</p>";

    const data = await fetchData(`https://restcountries.com/v3.1/all?fields=name,flags,capital,population`);
    
    if (!data) return showUIError('resultado-todos', "Error loading the list.");

    // Limit to 12 as per instructions
    const limitedList = data.slice(0, 12);
    
    container.innerHTML = limitedList.map(country => `
        <div class="tarjeta" style="border: 1px solid #eee; padding: 10px; text-align: center; border-radius: 5px;">
            <img src="${country.flags.svg}" alt="Flag" style="width: 100%; height: 80px; object-fit: cover;">
            <h4 style="font-size: 0.9rem; margin: 10px 0;">${country.name.common}</h4>
            <p style="font-size: 0.8rem;">Cap: ${country.capital[0] || 'N/A'}</p>
            <p style="font-size: 0.8rem;">Pop: ${country.population.toLocaleString()}</p>
        </div>
    `).join('');
});