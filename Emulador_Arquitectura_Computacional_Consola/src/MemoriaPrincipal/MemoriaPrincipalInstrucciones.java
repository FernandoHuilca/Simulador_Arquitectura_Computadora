package MemoriaPrincipal;

public class MemoriaPrincipalInstrucciones {
    int[] instrucciones;
    public MemoriaPrincipalInstrucciones(){
        // Número de Instrucciones
        instrucciones = new int[10];
        // Movimiento de datos:
        instrucciones[0] = 1; // LOAD
        instrucciones[1] = 2; // STORE
        instrucciones[2] = 3; // MOVE
        // Operaciones Aritméticas
        instrucciones[3] = 4; //ADD
        instrucciones[4] = 5; // SUB
        instrucciones[5] = 6; // MUL
        //Operaciones Lógicas
        instrucciones[6] = 7; // AND
        instrucciones[7] = 8; // NOT
        instrucciones[8] = 9; // OR
        instrucciones[9] = 10; // XOR

    }

    public int obtenerElemento(int posición) {
    return instrucciones[posición];
    }
}
