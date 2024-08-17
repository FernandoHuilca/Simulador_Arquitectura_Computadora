package CPU;

import MemoriaPrincipal.*;

public class Cache {
    private boolean dirtyBit;
    private boolean válido;
    private int contadorDeVálidos;
    private int contadorDeInVálidos;
    private int númeroDeLíneas;
    private LíneaDeCache[] líneasDeCache;
    private MemoriaPrincipal memoriaPrincipal;
    private String tag;
    private String index;
    private String offset;

    public Cache(int tamañoDeLaCache, MemoriaPrincipal memoriaPrincipal) {
        this.contadorDeVálidos = 0;
        this.contadorDeInVálidos = 0;
        this.memoriaPrincipal = memoriaPrincipal;
        this.númeroDeLíneas = tamañoDeLaCache / this.memoriaPrincipal.getTamañoDelBloque();
        líneasDeCache = new LíneaDeCache[númeroDeLíneas];
        instanciarLíneasDeCache();
        tag = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());
        index = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());
        offset = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());
    }

    /* Métodos Principales dame Carga y cambia Escribe palabra */
    public int dameElDatoDeLaDirección(String direcciónEnBinario) {
        dirtyBit = false;
        // Extraer tag, índice y offset de la dirección binaria
        int númeroDeBitsDelOffset = (int) (Math.log(memoriaPrincipal.getTamañoDelBloque()) / Math.log(2));
        int númeroDeBitsDelIndex = (int) (Math.log(númeroDeLíneas) / Math.log(2));
        int longitudDelTag = direcciónEnBinario.length() - númeroDeBitsDelIndex - númeroDeBitsDelOffset;

        // Extraer las partes de la dirección binaria
        String tag = direcciónEnBinario.substring(0, longitudDelTag);
        String indexBinario = direcciónEnBinario.substring(longitudDelTag, longitudDelTag + númeroDeBitsDelIndex);
        String offsetBinario = direcciónEnBinario.substring(longitudDelTag + númeroDeBitsDelIndex);
        // Convertir los valores binarios a decimales
        int indexDecimal = Integer.parseInt(indexBinario, 2);
        int offsetDecimal = Integer.parseInt(offsetBinario, 2);

        // Guardar los valores para la caché
        this.tag = tag;
        this.index = indexBinario;
        this.offset = offsetBinario;

        // Comprobar si el tag coincide
        if (!tag.equals(líneasDeCache[indexDecimal].getTag())) {
            // Cargar el bloque de la memoria principal a la caché
            líneasDeCache[indexDecimal].setBloque(memoriaPrincipal.getBloqueEnIndice(SistemasNumericos.binarioADecimalRecibeString(tag + indexBinario)));
            líneasDeCache[indexDecimal].setTag(tag);
            contadorDeInVálidos++;
        } else {
            contadorDeVálidos++;
        }

        // Devolver el dato almacenado en la posición del offset
        return líneasDeCache[indexDecimal].getDato(offsetDecimal);
    }

    public void cambiaElDatoEnLaDirección(int dato, String direcciónEnBinario) {
        dirtyBit = true;
        // Extraer tag, índice y offset de la dirección binaria
        int númeroDeBitsDelOffset = (int) (Math.log(memoriaPrincipal.getTamañoDelBloque()) / Math.log(2));
        int númeroDeBitsDelIndex = (int) (Math.log(númeroDeLíneas) / Math.log(2));
        int longitudDelTag = direcciónEnBinario.length() - númeroDeBitsDelIndex - númeroDeBitsDelOffset;

        // Extraer las partes de la dirección binaria
        String tag = direcciónEnBinario.substring(0, longitudDelTag);
        String indexBinario = direcciónEnBinario.substring(longitudDelTag, longitudDelTag + númeroDeBitsDelIndex);
        String offsetBinario = direcciónEnBinario.substring(longitudDelTag + númeroDeBitsDelIndex);

        // Convertir los valores binarios a decimales
        int indexDecimal = Integer.parseInt(indexBinario, 2);
        int offsetDecimal = Integer.parseInt(offsetBinario, 2);

        // Guardar los valores para la caché
        this.tag = tag;
        this.index = indexBinario;
        this.offset = offsetBinario;

        // Comprobar si el tag coincide
        if (!tag.equals(líneasDeCache[indexDecimal].getTag())) {
            // Cargar el bloque de la memoria principal a la caché
            líneasDeCache[indexDecimal].setBloque(memoriaPrincipal.getBloqueEnIndice(SistemasNumericos.binarioADecimalRecibeString(tag + indexBinario)));
            líneasDeCache[indexDecimal].setTag(tag);
            contadorDeInVálidos++;
        } else {
            contadorDeVálidos++;
        }
        líneasDeCache[indexDecimal].setDato(offsetDecimal, dato);
        memoriaPrincipal.setDatoEnPosición(SistemasNumericos.binarioADecimalRecibeString(direcciónEnBinario), dato);
    }

    private void instanciarLíneasDeCache() {
        for (int i = 0; i < númeroDeLíneas; i++) {
            líneasDeCache[i] = new LíneaDeCache(memoriaPrincipal.getTamañoDelBloque());
        }
    }

    public void imprimirCacheAtributos() {
        System.out.println("El número de líneas de la cache es: " + númeroDeLíneas);
        System.out.println("El tag es: " + tag);
        System.out.println("El index es: " + index);
        System.out.println("El offset es: " + offset);
        System.out.println("El bit Sucio es: " + dirtyBit);
        System.out.println("El contador de válidos es: " + contadorDeVálidos);
        System.out.println("El contador de inválidos es: " + contadorDeInVálidos);
    }

    public void imprimirLíneas() {
        System.out.println("\nLas líneas de cache son:");
        System.out.println("--------------------------------");
        System.out.println("|    Tag       |     Bloque    |");
        System.out.println("--------------------------------");
        for (int i = 0; i < númeroDeLíneas; i++) {
            System.out.println("|     " + líneasDeCache[i].getTag() + "        |     " + líneasDeCache[i].getBloque().getNombre() + "     |");
        }
        System.out.println("--------------------------------");
    }

    public int getTamañoDeDireccion() {
        return memoriaPrincipal.getTamañoDeDirecciónBits();
    }

    public boolean isDirtyBit() {
        return dirtyBit;
    }

    public boolean isVálido() {
        return válido;
    }

    public int getContadorDeVálidos() {
        return contadorDeVálidos;
    }

    public int getContadorDeInVálidos() {
        return contadorDeInVálidos;
    }

    public int getNúmeroDeLíneas() {
        return númeroDeLíneas;
    }

    public LíneaDeCache[] getLíneasDeCache() {
        return líneasDeCache;
    }


    public MemoriaPrincipal getMemoriaPrincipal() {
        return memoriaPrincipal;
    }

    public String getTag() {
        return tag;
    }

    public String getIndex() {
        return index;
    }


    public String getOffset() {
        return offset;
    }

}
