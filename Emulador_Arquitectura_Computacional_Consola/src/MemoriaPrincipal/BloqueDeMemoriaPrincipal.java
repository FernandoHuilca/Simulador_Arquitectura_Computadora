package MemoriaPrincipal;

import java.util.concurrent.locks.StampedLock;

public class BloqueDeMemoriaPrincipal {
    int tamañoDelBloque;
    private int[] datos;

    public BloqueDeMemoriaPrincipal(int tamañoDeBloque) {
        this.tamañoDelBloque = tamañoDeBloque;
        datos = new int[this.tamañoDelBloque];
        instanciarDatosConCeros();
    }

    private void instanciarDatosConCeros() {
        for (int i = 0; i < tamañoDelBloque; i++){
            datos[i] = 0;
        }
    }

    public void setDatoEnPosicion(int posición, int dato) {
        datos[posición] = dato;
    }

    public int[] getDatos() {
        return datos;
    }

    public int getDatoEnPosición(int posición) {
        return datos[posición];
    }

    public int getTamaño() {
        return tamañoDelBloque;
    }
}
