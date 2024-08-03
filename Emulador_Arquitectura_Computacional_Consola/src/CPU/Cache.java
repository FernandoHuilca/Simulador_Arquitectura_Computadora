package CPU;

import MemoriaPrincipal.*;

public class Cache {
    private boolean dirtyBit;
    private boolean válido;
    private boolean inválido;
    private int númeroDeLíneas;
    private LíneaDeCache[] líneasDeCache;
    private MemoriaPrincipal memoriaPrincipal;
    private String tag;
    private String index;
    private String offset;

    public Cache(int tamañoDeLaCache, MemoriaPrincipal memoriaPrincipal) {
        this.memoriaPrincipal = memoriaPrincipal;
        this.númeroDeLíneas = tamañoDeLaCache / this.memoriaPrincipal.getTamañoDelBloque();
        líneasDeCache = new LíneaDeCache[númeroDeLíneas];
        instanciarLíneasDeCache();
        tag = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());
        index = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());;
        offset = SistemasNumericos.decimalABinarioDevuelveString(0, getTamañoDeDireccion());;
    }


    /* Métodos Principales dame Carga y cambia Escribe palabra */
    public int dameElDatoDeLaDirección(String direcciónEnBinario) {
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
        }

        // Devolver el dato almacenado en la posición del offset
        return líneasDeCache[indexDecimal].getDato(offsetDecimal);
    }


    public void cambiaElDatoEnLaDirección(int dato, String direcciónEnBinario) {
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
        }
        líneasDeCache[indexDecimal].setDato(offsetDecimal,dato);
        memoriaPrincipal.setDatoEnPosición(SistemasNumericos.binarioADecimalRecibeString(direcciónEnBinario), dato);
    }


    private void instanciarLíneasDeCache() {
        for (int i = 0; i < númeroDeLíneas; i++) {
            líneasDeCache[i] = new LíneaDeCache(memoriaPrincipal.getTamañoDelBloque());
        }
    }

    public void imprimirCacheAtributos(){
        System.out.println("El número de líneas de la cache es: " + númeroDeLíneas);
        System.out.println("El tag es: " + tag);
        System.out.println("El index es: " + index);
        System.out.println("El offset es: " + offset);
    }
    public void imprimirLíneas(){
        System.out.println("Las líneas de chache son:");
        for (int i = 0 ; i < númeroDeLíneas ; i ++ ){
            System.out.println("        "+ i + " tagDeLínea: " + líneasDeCache[i].getTag() + " bloque: " + líneasDeCache[i].getBloque().getNombre());
        }
    }


    public int getTamañoDeDireccion() {
        return memoriaPrincipal.getTamañoDeDirecciónBits();
    }
}
