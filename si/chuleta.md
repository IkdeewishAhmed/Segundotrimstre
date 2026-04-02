# 📋 CHULETA UD6 — TCP/IP, OSI, HTTP y json-server

---

## 🧱 MODELO OSI — 7 CAPAS (de arriba a abajo)

| # | Capa | Función principal | Protocolos |
|---|------|-------------------|------------|
| 7 | **Aplicación** | Interfaz con el usuario y apps | HTTP, FTP, SMTP, DNS, POP3 |
| 6 | **Presentación** | Cifrado, compresión, formato | SSL/TLS |
| 5 | **Sesión** | Abre, mantiene y cierra sesiones | RPC, NetBIOS |
| 4 | **Transporte** | Control de flujo, segmentación, errores | TCP (fiable), UDP (rápido) |
| 3 | **Red** | Direccionamiento y enrutamiento | IP, ICMP, ARP, RIP, OSPF |
| 2 | **Enlace de datos** | Transmisión dentro de la red física | Ethernet, Wi-Fi, MAC, PPP |
| 1 | **Física** | Bits por el medio físico | Cable UTP, fibra, Wi-Fi, 4G/5G |

---

## 🌐 MODELO TCP/IP — 4 CAPAS

| Capa TCP/IP | Equivale a OSI | Protocolos |
|-------------|---------------|------------|
| **Aplicación** | 7 + 6 + 5 | HTTP, HTTPS, FTP, SSH, DNS, SMTP, POP3, IMAP |
| **Transporte** | 4 | TCP (fiable, con conexión), UDP (rápido, sin conexión) |
| **Internet** | 3 | IP (IPv4/IPv6), ICMP, ARP |
| **Acceso a la red** | 2 + 1 | Ethernet, Wi-Fi, PPP, DSL, 4G/5G |

> 💡 **TCP**: garantiza entrega y orden. **UDP**: más rápido, sin garantías (videos, juegos).

---

## 🔌 PUERTOS IMPORTANTES

| Servicio | Puerto | Protocolo |
|----------|--------|-----------|
| HTTP | 80 | TCP |
| HTTPS | 443 | TCP |
| FTP | 21 | TCP |
| SSH | 22 | TCP |
| DNS | 53 | UDP/TCP |
| SMTP | 25 | TCP |
| POP3 | 110 | TCP |
| IMAP | 143 | TCP |

---

## 📡 HTTP — CARACTERÍSTICAS

- **Sin estado**: cada petición es independiente (se usan cookies/tokens para persistir).
- **Sobre TCP/IP**, capa de aplicación.
- Usa **métodos/verbos** para indicar la acción.
- Accede a recursos mediante **URLs**.
- Transfiere HTML, JSON, XML, imágenes, etc.
- **HTTPS** = HTTP + cifrado TLS.

---

## 📨 MÉTODOS HTTP (VERBOS)

| Método | Para qué sirve | ¿Idempotente? |
|--------|---------------|---------------|
| **GET** | Obtener/leer datos, sin modificar nada | ✅ Sí |
| **POST** | Crear un nuevo recurso, enviar datos | ❌ No |
| **PUT** | Actualizar un recurso **completo** (o crearlo si no existe) | ✅ Sí |
| **PATCH** | Modificar **solo algunos campos** de un recurso | ✅ Sí |
| **DELETE** | Eliminar un recurso | ✅ Sí |
| **HEAD** | Como GET pero **solo devuelve cabeceras**, sin cuerpo | ✅ Sí |
| **OPTIONS** | Pregunta qué métodos están permitidos en un recurso | ✅ Sí |

---

## 📬 CÓDIGOS DE RESPUESTA HTTP

| Código | Significado |
|--------|-------------|
| `200 OK` | Petición correcta |
| `201 Created` | Recurso creado (POST) |
| `204 No Content` | Éxito pero sin cuerpo de respuesta (DELETE) |
| `404 Not Found` | Recurso no encontrado |
| `500 Internal Server Error` | Error del servidor |

---

## 🛠️ JSON-SERVER — MONTAR UNA API FALSA

### 1. Instalar
```bash
sudo apt install json-server
# o con npm:
npm install -g json-server
```

### 2. Crear `db.json`
```json
{
  "alumnos": [
    { "id": 1, "nombre": "Cristian", "edad": 30, "profe_fav_id": 1 }
  ],
  "profes": [
    { "id": 1, "nombre": "Paco", "asignaturas": [
        { "titulo": "Sistemas", "curso": 1 },
        { "titulo": "Bases", "curso": 1 }
    ]}
  ]
}
```

> 💡 Los datos **embebidos** (como `asignaturas` dentro de `profes`) se guardan directamente dentro del objeto, sin necesidad de un campo `_id` externo.

### 3. Arrancar el servidor
```bash
json-server --watch db.json --port 3000
```
El servidor queda en `http://localhost:3000`. Las peticiones se hacen **desde otra terminal**.

---

## 🚗 EJEMPLO COMPLETO — personas con coches embebidos

Este es el `db.json` que usamos de ejemplo en clase para practicar todos los ejercicios:

```json
{
  "personas": [
    {
      "id": "1",
      "nombre": "Ana",
      "edad": 28,
      "coches": [
        { "marca": "Toyota", "modelo": "Corolla", "año": 2019 },
        { "marca": "Ford",   "modelo": "Focus",   "año": 2021 }
      ]
    },
    {
      "id": "2",
      "nombre": "Luis",
      "edad": 35,
      "coches": [
        { "marca": "BMW", "modelo": "Serie 3", "año": 2022 }
      ]
    }
  ],
  "$schema": "./node_modules/json-server/schema.json"
}
```

Arrancamos el servidor:
```bash
json-server --watch db.json --port 3000
```

### EJ. 3 — Ver todas las personas
```bash
curl http://localhost:3000/personas
```

### EJ. 4 — Ver la segunda persona (id = 2)
```bash
curl http://localhost:3000/personas/2
```

### EJ. 5 — Añadir una nueva persona (POST)
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"id":"3","nombre":"Sara","edad":42,"coches":[{"marca":"Seat","modelo":"Ibiza","año":2020}]}' \
  http://localhost:3000/personas
```

### EJ. 6 — Modificar solo la edad de la tercera persona (PATCH)
```bash
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"edad": 45}' \
  http://localhost:3000/personas/3
```

### EJ. 7 — Personas con más de 30 años
```bash
curl "http://localhost:3000/personas?edad_gte=31"
```

### EJ. 8 — Solo los coches de una persona (jq)
```bash
curl http://localhost:3000/personas/1 2>/dev/null | jq '.coches'
```

---

## 💻 CURL — PETICIONES DESDE TERMINAL

### Estructura general
```bash
curl -X MÉTODO -H "Content-Type: application/json" -d 'DATOS_JSON' URL
```

| Opción | Significado |
|--------|-------------|
| `-X` | Especifica el método HTTP (GET por defecto) |
| `-d` | Datos a enviar en el cuerpo (POST, PUT, PATCH) |
| `-H` | Cabecera HTTP |
| `2>/dev/null` | Redirige los errores (stderr) a /dev/null para no verlos |
| `\|` (pipe) | Pasa la salida del comando anterior al siguiente |

### `-X` — Elegir el método HTTP
Sin `-X` el método es GET por defecto. Con `-X` lo especificas tú:
```bash
# Sin -X → GET por defecto
curl http://localhost:3000/personas

# Con -X → tú eliges el método
curl -X DELETE http://localhost:3000/personas/1
curl -X PATCH -H "Content-Type: application/json" -d '{"edad":99}' http://localhost:3000/personas/2
```

### `-d` — Enviar datos en el cuerpo
Se usa con POST, PUT y PATCH para mandar un JSON:
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"id":"4","nombre":"Mario","edad":25,"coches":[]}' \
  http://localhost:3000/personas
```
> ⚠️ Sin `-H "Content-Type: application/json"` el servidor no sabe que le estás mandando JSON.

### `-H` — Añadir una cabecera HTTP
Se usa para indicar el formato de los datos que envías:
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Lucía","edad":29}' \
  http://localhost:3000/personas
```
> La cabecera más común es `Content-Type: application/json`. Sin ella el POST/PUT/PATCH puede fallar.

### `2>/dev/null` — Silenciar los errores de curl
curl imprime info de progreso por `stderr` (canal 2). Esto lo manda a la papelera:
```bash
# Sin 2>/dev/null → aparece basura de progreso mezclada con el JSON
curl http://localhost:3000/personas/1 | jq '.coches'

# Con 2>/dev/null → salida limpia, solo el JSON
curl http://localhost:3000/personas/1 2>/dev/null | jq '.coches'
```
> `2` = stderr (errores), `1` = stdout (salida normal). `>` redirige. `/dev/null` = papelera del sistema.

### `|` (pipe) — Encadenar comandos
Pasa la salida de un comando como entrada al siguiente. Se combina con `jq` para filtrar JSON:
```bash
# La salida de curl entra en jq, que filtra solo .coches
curl http://localhost:3000/personas/1 2>/dev/null | jq '.coches'

# También puedes encadenar más de uno
curl http://localhost:3000/personas/1 2>/dev/null | jq '.coches' | jq '.[0]'
#                                                         ↑ array de coches    ↑ primer coche
```

---

## 📋 CURL — EJEMPLOS PRÁCTICOS CON json-server

### GET — Ver todos los registros
```bash
curl http://localhost:3000/alumnos
```

### GET — Ver un registro por ID
```bash
curl http://localhost:3000/alumnos/1
```

### GET — Filtrar (edad mayor de 30)
```bash
curl "http://localhost:3000/alumnos?edad_gte=31"
```
> `_gte` = mayor o igual que. Para "mayor que 30" usa `_gte=31`.

### GET — Solo una propiedad embebida (con jq)
```bash
curl -X GET http://localhost:3000/profes/1 2>/dev/null | jq '.asignaturas'
```

### POST — Crear un nuevo registro
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"id": 2, "nombre": "Carlota", "edad": 22, "profe_fav_id": 1}' \
  http://localhost:3000/alumnos
```

### PUT — Reemplazar un registro completo
```bash
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "nombre": "Cristian", "edad": 31, "profe_fav_id": 2}' \
  http://localhost:3000/alumnos/1
```

### PATCH — Modificar solo un campo
```bash
curl -X PATCH \
  -H "Content-Type: application/json" \
  -d '{"edad": 35}' \
  http://localhost:3000/alumnos/1
```

### DELETE — Eliminar un registro
```bash
curl -X DELETE http://localhost:3000/alumnos/1
```

---

## 🔍 JQ — PARSEAR JSON EN TERMINAL

`jq` filtra y formatea JSON que llega por pipe desde curl.

```bash
# Ver todo el JSON formateado
curl http://localhost:3000/profes/1 | jq '.'

# Ver solo una propiedad
curl http://localhost:3000/profes/1 2>/dev/null | jq '.asignaturas'

# Ver un campo concreto
curl http://localhost:3000/alumnos/1 | jq '.nombre'
```

> 💡 `2>/dev/null` → el `2` es el **descriptor de stderr** (errores). `>` redirige. `/dev/null` es "la papelera". Así ocultamos los mensajes de progreso de curl para que el pipe llegue limpio a jq.

---

## 📐 FLUJO COMPLETO DE UNA PETICIÓN HTTP

```
Cliente                          Servidor
  |                                  |
  |--- GET /index.html HTTP/1.1 ---->|
  |    Host: www.example.com         |
  |                                  |
  |<-- HTTP/1.1 200 OK --------------|
  |    Content-Type: text/html       |
  |    [cuerpo con el HTML]          |
```

---

## ⚡ RESUMEN RÁPIDO: ¿qué método uso?

| Quiero… | Método |
|---------|--------|
| Ver todos los elementos | `GET /tabla` |
| Ver uno por ID | `GET /tabla/id` |
| Ver con filtro | `GET /tabla?campo_gte=valor` |
| Crear uno nuevo | `POST /tabla` + body JSON |
| Reemplazar uno entero | `PUT /tabla/id` + body JSON completo |
| Cambiar solo un campo | `PATCH /tabla/id` + body JSON parcial |
| Borrar uno | `DELETE /tabla/id` |
| Ver solo propiedad embebida | `GET /tabla/id` + pipe a `jq '.propiedad'` |