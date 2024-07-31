package MemoriaPrincipal;


import java.io.*;

public class MemoriaPrincipal {

    private int[] datos;
    private int númeroDeDatos;
    private int númeroDeBloques;
    private BloqueDeMemoriaPrincipal[] bloques;

    public MemoriaPrincipal(int tamañoDeDirección, int tamañoDeBloque) {
        númeroDeDatos = (int) Math.pow(2, tamañoDeDirección); // 2^tamañoDeDireccion // me devuelve la cantidad de
        // números que puedo escribir con esa cantidad de bits
        númeroDeBloques = (númeroDeDatos / tamañoDeBloque);

        datos = new int[númeroDeDatos];
        bloques = new BloqueDeMemoriaPrincipal[númeroDeBloques];

        //Datos de la memoria principal en cero:
        for (int i = 0; i < númeroDeDatos; i++) {
            datos[i] = 0;
        }
        //Pasar los datos en los bloques:
        // Pasar los datos de la memoria principal a los bloques
        for (int i = 0; i < bloques.length; i++) {
            bloques[i] = new BloqueDeMemoriaPrincipal(tamañoDeBloque);
            for (int j = 0; j < tamañoDeBloque; j++) {
                int direccion = i * tamañoDeBloque + j;
                if (direccion < númeroDeDatos) {
                    bloques[i].setDatoEnPosicion(j, datos[direccion]);
                }
            }
        }
    }



    public BloqueDeMemoriaPrincipal getBloqueEnIndice(int indice) {
        return bloques[indice];
    }

    public void setDatoEnPosición(int posición, int valor) {
        this.datos[posición] = valor;
        int bloqueIndex = posición / bloques[0].getDatos().length;
        int bloquePosición = posición % bloques[0].getDatos().length;
        bloques[bloqueIndex].setDatoEnPosicion(bloquePosición, valor);
    }
    public void agregarDato(int posición, int dato) {
        setDatoEnPosición(posición, dato);
    }

    public void cargarDatosDesdeArchivo(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int direccion = 0;
        while ((line = reader.readLine()) != null) {
            int valor = Integer.parseInt(line.trim());
            if (direccion < númeroDeDatos) {
                agregarDato(direccion, valor);
                direccion++;
            } else {
                break; // Si excede el tamaño de la memoria, salir del bucle
            }
        }
        reader.close();
    }



    public void guardarDatosEnArchivo(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (int i = 0; i < númeroDeDatos; i++) {
            writer.write(Integer.toString(datos[i]));
            writer.newLine();
        }
        writer.close();
    }
    //getters y setters
    public int[] getDatos(){
        return datos;
    }
    public BloqueDeMemoriaPrincipal[] getBloques(){
        return bloques;
    }
    public int getNúmeroDeDatos(){
        return númeroDeDatos;
    }
    public int getNúmeroDeBloques(){
        return númeroDeBloques;
    }
}
