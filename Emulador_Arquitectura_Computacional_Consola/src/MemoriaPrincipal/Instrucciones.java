package MemoriaPrincipal;

public enum Instrucciones {

    // Movimiento de datos:
    LOAD(0), /*Carga un valor desde la memoria a un registro.
    Ejemplo: LOAD R1, 1000 (Carga el valor en la dirección de memoria 1000 en el registro R1).*/
    STORE(1), /*  Almacena el valor de un registro en una ubicación de memoria.
    Ejemplo: STORE R1, 1000 (Almacena el valor del registro R1 en la dirección de memoria 1000).*/
    MOVE(2), /* Copia datos de un registro a otro.
    Ejemplo: MOVE R1, R2 (Copia el valor de R2 a R1).*/

    // Operaciones Aritméticas
    ADD(3), /* Suma dos valores.
    Ejemplo: ADD R1, R2 (Suma los valores de R1 y R2, y almacena el resultado en R1).*/
    SUB(4), /*Resta dos valores.
    Ejemplo: SUB R1, R2 (Resta el valor de R1 del valor de R2, y almacena el resultado en R1).*/
    MUL(5),/*Multiplica dos valores.
    Ejemplo: MUL R1, R2 (Multiplica los valores de R1 y R2, y almacena el resultado en R1).*/

    // Operaciones Lógicas
    AND(6),/*Realiza una operación lógica AND entre dos valores.
    Ejemplo: AND R1, R2 (Realiza un AND bit a bit entre R1 y R2, y almacena el resultado en R1).*/
    NOT(7),/*Realiza una operación lógica NOT en un valor.
    Ejemplo: NOT R1, R2 (Invierte todos los bits en R2 y almacena el resultado en R1).*/
    OR(8),/*Realiza una operación lógica OR entre dos valores.
    Ejemplo: OR R1, R2 (Realiza un OR bit a bit entre R1 y R2, y almacena el resultado en R1).*/
    XOR(9);/*Realiza una operación lógica XOR entre dos valores.
    Ejemplo: XOR R1, R2 (Realiza un XOR bit a bit entre R1 y R2, y almacena el resultado en R1).*/




    private int direcciónDeMemoria;
    Instrucciones(int direcciónDeMemoria) {
        this.direcciónDeMemoria = direcciónDeMemoria;
    }
    public int getDirecciónDeMemoria() {
        return direcciónDeMemoria;
    }}
