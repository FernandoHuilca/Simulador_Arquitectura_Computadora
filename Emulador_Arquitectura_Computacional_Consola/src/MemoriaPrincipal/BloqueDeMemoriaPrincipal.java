package MemoriaPrincipal;

public class BloqueDeMemoriaPrincipal {
    private int[] datos;

    public BloqueDeMemoriaPrincipal(int tamañoDeBloque) {
    datos = new int[tamañoDeBloque];
    }

    public void setDatoEnPosicion(int posición, int dato) {
        datos[posición] = dato;
    }

    public int[] getDatos() {
        return datos;
    }
}
