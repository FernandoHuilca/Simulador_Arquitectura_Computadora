package MemoriaPrincipal;


import java.io.*;

public class MemoriaPrincipal {

    private int[] datos;
    private int númeroDeDatos;
    private int númeroDeBloques;
    private int tamañoDeBloque;
    private int tamañoDeDirecciónBits;
    private BloqueDeMemoriaPrincipal[] bloques;
    private int númeroDeLíneasDeCache;

    public MemoriaPrincipal(int tamañoDeDirecciónBits, int tamañoDeBloqueBytes, int tamañoDeLaCache) {
        this.tamañoDeBloque = tamañoDeBloqueBytes;
        this.tamañoDeDirecciónBits = tamañoDeDirecciónBits;
        númeroDeDatos = (int) Math.pow(2, tamañoDeDirecciónBits); // 2^tamañoDeDireccion // me devuelve la cantidad de
        // números que puedo escribir con esa cantidad de bits
        this.númeroDeBloques = (númeroDeDatos / tamañoDeBloqueBytes);
        this.númeroDeLíneasDeCache = tamañoDeLaCache / númeroDeBloques;

        datos = new int[númeroDeDatos];
        bloques = new BloqueDeMemoriaPrincipal[númeroDeBloques];

        //Datos de la memoria principal en cero:
        for (int i = 0; i < númeroDeDatos; i++) {
            datos[i] = 0;
        }
        //Pasar los datos en los bloques:
        // Pasar los datos de la memoria principal a los bloques
        int contador = 0;
        for (int i = 0; i < bloques.length; i++) {
            bloques[i] = new BloqueDeMemoriaPrincipal(tamañoDeBloqueBytes,contador,i);
            for (int j = 0; j < tamañoDeBloqueBytes; j++) {
                int direccion = i * tamañoDeBloqueBytes + j;
                if (direccion < númeroDeDatos) {
                    bloques[i].setDatoEnPosicion(j, datos[direccion]);
                }
            }
            contador++;
            if (contador == númeroDeLíneasDeCache){
                contador = 0;
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

    public int getTamañoDelBloque() {
        return tamañoDeBloque;
    }

    public int getTamañoDeDirecciónBits() {
        return tamañoDeDirecciónBits;
    }

}
