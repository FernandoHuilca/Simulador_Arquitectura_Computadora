package MemoriaPrincipal;

public class BloqueDeMemoriaPrincipal {
    int tag_DeBloque;
    int tamañoDelBloque;
    private int[] datos;
    private String nombre;

    public BloqueDeMemoriaPrincipal(int tamañoDeBloque, int tag_DeBloque, int indiceDelNombre) {
        this.tag_DeBloque = tag_DeBloque;
        this.tamañoDelBloque = tamañoDeBloque;
        datos = new int[this.tamañoDelBloque];
        instanciarDatosConCeros();
        this.nombre = " BLOQUE  " + indiceDelNombre;
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
    public int getTag_DeBloque(){
        return tag_DeBloque;
    }

    public String getNombre() {
        return nombre;
    }
}
