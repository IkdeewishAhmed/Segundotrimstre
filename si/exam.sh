#Crea un script de bash con las siguientes 10 funciones:

#4. Función multiplicar
#Crea una función que multiplique dos números pasados como argumentos.

#Uso: multiplicar 7 8


multiplicar() {
    validar_argumentos 2 $@ || return 1
    
    resultado=$(( $1 * $2 ))
    echo $resultado
}



#Salida esperada: 56

#5. Función potencia
#Crea una función que eleve un número a una potencia. El primer argumento es la base y el segundo el exponente.

#Uso: potencia 2 5

#Salida esperada: 32

#7. Función volumen_cubo
#Crea una función que calcule el volumen de un cubo recibiendo el lado como argumento. Formula: V = lado³

#Uso: volumen_cubo 4

#Salida esperada: 64