# Laboratorio 2 - 19-10114 - CI2693
- Nombre: Stefano Casale
- Carnet: 1910114
- Universidad Simón Bolívar
- Trimestre Ene - Mar 26

# Pasos de Ejecución 
1. Instalar Kotlin:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - sudo apt install kotlin

2. Ubica tu entorno:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - cd ubicacion/de/tu/entorno

3. Compila el archivo "Sudoku.kt"
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - kotlinc Sudoku.kt -include-runtime -d Sudoku.jar

4. Consigue un Sudoku en forma de una secuencia de números entre 0 y 9. Los "0" representan las casillas vacías.
    - Un ejemplo puede ser el siguiente sudoku: 001790000400000080000200600000170000060000050000000300300008000080000007000000009

5. Ejecuta el archivo para resolver el Sudoku
    - Desde tu terminal de WSL ejecuta el siguiente comando: 
        - java -jar Sudoku.jar argumento

    - Como ejemplo de ejecución, tomamos el Sudoku anterior
    - java -jar Sudoku.jar 001790000400000080000200600000170000060000050000000300300008000080000007000000009
    - La salida debe ser el Sudoku resuelto: 821796543476531982935284671548173296163829754792645318317968425689452137254317869

El programa maneja errores, pero para garantizar su funcionalidad se recomienda que el único parámetro sea una cadena de enteros entre 0 y 9 de exactamente 81 caracteres 

# Funcionamiento
    - El programa recibe una cadena de 81 caracteres que representa un tablero de Sudoku de 9x9.
    - Los dígitos del 1 al 9 indican celdas fijas, y el '0' representa una celda vacía.
    - Utiliza la técnica de backtracking para encontrar una solución válida.

    - Posibles salidas:
        - Si el Sudoku tiene solución: Imprime la cadena de 81 caracteres con el tablero resuelto
        - Si el Sudoku no tiene solucion: Imprime "NOSOLUTION"

# Decisiones de Implementación
    - Representación del tablero: Opté por una matriz 9x9 ya que facilita el acceso a filas, columnas y
    subtableros, haciendo que sea mas fácil optimizar y hacer el backtracking

    - Verificar que número podia usar para llenar los "0": Se implementó la funcion esValido(), comprueba
    que un número no esté ya presente en la misma fila, en la misma columna ni en el subtablero 3×3 
    correspondiente. Esto evita que el algoritmo continúe explorando combinaciones que de todas formas 
    violarían las reglas del Sudoku. Al descartar rápidamente los números no válidos, el espacio de búsqueda
    se reduce drásticamente y el programa puede encontrar la solución (o determinar que no existe) en un 
    tiempo razonable, incluso para sudokus con muchas celdas vacías.

    - Resolución del Sudoku: Se usó la funcion resolver() con backtracking. Se recorre el tablero en orden
    buscando la primera celda vacía. Prueba números del 1 al 9, verifica si son válidos con esValido() y se
    llama recursivamente.

# Tabla de complejidad computacional (Big O)

|         Método         |   Complejidad   |              Descripción
|------------------------|-----------------|--------------------------------------------------
| convertirCadenaMatriz	 |   O(81) = O(1)  | Recorre los 81 caracteres una vez, operaciones constantes.
| esValido	             |   O(27) = O(1)  | Verifica 27 posiciones
| resolver               |   	O(9ⁿ)      | Resuelve el Sudoku, n es el número de celdas vacías. 
| convertirMatrizCadena  |   O(81) = O(1)  | Recorre las 81 celdas para contruir la cadena