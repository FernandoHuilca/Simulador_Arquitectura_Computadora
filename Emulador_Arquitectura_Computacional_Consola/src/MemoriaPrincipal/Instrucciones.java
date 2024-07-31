package MemoriaPrincipal;

public enum Instrucciones {
    LOAD(0), /*Carga un valor desde la memoria a un registro.
    Ejemplo: LOAD R1, 1000 (Carga el valor en la dirección de memoria 1000 en el registro R1).*/
    STORE(1), /*  Almacena el valor de un registro en una ubicación de memoria.
    Ejemplo: STORE R1, 1000 (Almacena el valor del registro R1 en la dirección de memoria 1000).*/
    MOVE(2), /* Copia datos de un registro a otro.
    Ejemplo: MOVE R1, R2 (Copia el valor de R2 a R1).*/
    ADD(3); /* Suma dos valores.
    Ejemplo: ADD R1, R2 (Suma los valores de R1 y R2, y almacena el resultado en R1).*/
    private int direcciónDeMemoria;
    Instrucciones(int direcciónDeMemoria) {
        this.direcciónDeMemoria = direcciónDeMemoria;
    }
    public int getDirecciónDeMemoria() {
        return direcciónDeMemoria;
    }}
