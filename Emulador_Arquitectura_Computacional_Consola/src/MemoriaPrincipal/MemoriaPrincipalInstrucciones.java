package MemoriaPrincipal;

public class MemoriaPrincipalInstrucciones {
    int[] instrucciones;
    public MemoriaPrincipalInstrucciones(){
        instrucciones = new int[8];
        instrucciones[0] = 1000;
        instrucciones[1] = 1001;
        instrucciones[2] = 1010;
        instrucciones[3] = 1110;
    }

    public int obtenerElemento(int posición) {
    return instrucciones[posición];
    }
}
