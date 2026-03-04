fun convertirCadenaMatriz(casillas: String): Array<IntArray> { // Convertimos el string a una matriz de enteros

    // Validamos que el string no este vacío
    if (casillas.isEmpty()) {
        throw IllegalArgumentException("Debes agregar las casillas del Sudoku")
    }

    // Validamos que el número de casillas sea correcto
    if (casillas.length != 81) {
        throw IllegalArgumentException("El número de casillas debe ser exactamente 81")
    }

    // Validamos que el string solo contenga números del 0 al 9
    if (!casillas.matches(Regex("^[0-9]+$"))) {
        throw IllegalArgumentException("Las casillas solo deben contener números enteros del 0 al 9")
    }

    val tablero = Array(9) { IntArray(9) } // Almacen del tablero 9x9

    // Convertimos el string a una matriz de enteros
    for (i in casillas.indices) {
        val fila = i / 9
        val columna = i % 9
        tablero[fila][columna] = casillas[i].digitToInt()
    }

    return tablero  
}

fun esValido(tablero: Array<IntArray>, fila: Int, columna: Int, numero: Int): Boolean { // Verificamos que un número sea válido en la posicion donde se quiere colocar

    // Verificamos si el número ya existe en la fila
    for (i in 0 until 9) {
        if (tablero[fila][i] == numero) {
            return false
        }
    }

    // Verificamos si el número ya existe en la columna
    for (i in 0 until 9){
        if(tablero[i][columna] == numero) {
            return false
        }
    }

    // Verificamos si el número ya existe en el subcuadro 3x3
    val subTableroFila = fila / 3 * 3           // Usamos la división entera para encontrar el inicio del subcuadro
    val subTableroColumna = columna / 3 * 3    

    for (i in subTableroFila until subTableroFila + 3) { 
        for (j in subTableroColumna until subTableroColumna + 3) {

            // Verificacion del número en el subcuadro
            if (tablero[i][j] == numero) {
                return false
            }
        }
    }

    // Si el número no existe en la fila, columna y subcuadro, es válido
    return true
}

fun resolver(tablero: Array<IntArray>): Boolean { // Recursion que resuelve el Sudoku usando backtracking

    // Si no hay casillas vacías, el Sudoku estaba resuelto
    for (fila in 0 until 9) {
        for (columna in 0 until 9) {

            // Accedemos a la casilla para verificar si esta vacía
            if (tablero[fila][columna] == 0) { 

                // Probamos números del 1 al 9 en la casilla vacía 
                for (numero in 1..9) {

                    // Verificamos si el número es válido
                    if (esValido(tablero, fila, columna, numero)) {
                        tablero[fila][columna] = numero

                        // Llamamos recursivamente a resolver para continuar con el siguiente número
                        if (resolver(tablero)) {
                            return true
                        } else {
                            // Deshacemos y probamos el siguiente número
                            tablero[fila][columna] = 0 
                        }
                    }
                }
                return false
            }
        }
    }
    return true
}

fun convertirMatrizCadena(tablero: Array<IntArray>): String { // Convertimos el tablero de enteros a un string

    val resultado = StringBuilder()     // Almacen para construir el resultado final como un string

    // Recorremos el tablero 
    for (fila in tablero) {
        for (numero in fila) {
            resultado.append(numero) // Agregamos cada número al resultado como un string
        }
    }

    // Devolvemos el resultado como un string
    return resultado.toString() 
}

fun main (args: Array<String>) {

    // Validamos que se haya proporcionado un argumento
    if (args.isEmpty()) {
        throw IllegalArgumentException("Debes enviar las casillas del Sudoku como argumento")
    }   

    // Validamos que se haya proporcionado solo un argumento
    if (args.size > 1) {
        throw IllegalArgumentException("Evita tener mas de un argumento, solo se necesita el string de las casillas del Sudoku")
    }

    // Recibimos el string de consola
    val casillas = args[0]

    // Convertimos el string a una matriz de enteros
    val tablero = convertirCadenaMatriz(casillas)

    // Resolvemos el Sudoku y mostramos el resultado
    if (resolver(tablero)) {
        System.out.println(convertirMatrizCadena(tablero)) // Convertimos el tablero resuelto a un string y lo mostramos
    } else {
        System.out.println("NOSOLUTION") // Si no se pudo resolver,
    }
}