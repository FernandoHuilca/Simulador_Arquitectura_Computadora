public class MapeoDirecto {
    private static final int LINEAS_CACHE = 8; //Tamaño de la caché
    private static final int TAMAÑO_BLOQUES = 4; //Tamaño de cada bloque de la memoria principal
    private static LineasCaché[] caché;
    MemoriaPrincipal memoriaPrincipal = new MemoriaPrincipal();

    public static void main(String[] args) {
        MapeoDirecto cacheSimulator = new MapeoDirecto();
        iniciarCaché();
        cacheSimulator.accesoAMemoria(14); //Se llena la memoria caché
        cacheSimulator.accesoAMemoria(18); //Se llena la memoria caché
        cacheSimulator.accesoAMemoria(22); //Se llena la memoria caché
        cacheSimulator.accesoAMemoria(26); //Acierto cuando se trate de acceder
        cacheSimulator.accesoAMemoria(26); //Acierto cuando se trate de acceder
    }
    public void accesoAMemoria(int direcciónMemoria) {
        int nLínea = (direcciónMemoria /TAMAÑO_BLOQUES) % LINEAS_CACHE;
        int tag = direcciónMemoria / (TAMAÑO_BLOQUES * LINEAS_CACHE);

        LineasCaché lineaABuscar = caché[nLínea];

        if (lineaABuscar.isBitValidez() && lineaABuscar.getTag() == tag) {
            System.out.println("Acierto Caché | Dirección: " + direcciónMemoria + " dato: " + lineaABuscar.getDato());
        } else {
            System.out.println("Fallo de Caché | Se cargará de la memoria principal.");
            lineaABuscar.setBitValidez(true);
            lineaABuscar.setTag(tag);
            lineaABuscar.setDato(memoriaPrincipal.getDatoDeMemoria(direcciónMemoria));
            System.out.println("Dirección: " + direcciónMemoria + " dato: " + lineaABuscar.getDato());
        }
    }
    public static void iniciarCaché() {
        caché = new LineasCaché[LINEAS_CACHE];
        for (int i = 0; i < LINEAS_CACHE; i++) {
            caché[i] = new LineasCaché();
        }
    }

}
