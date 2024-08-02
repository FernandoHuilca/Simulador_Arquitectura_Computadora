package CPU;

import MemoriaPrincipal.*;

public class Cache {
    private boolean dirtyBit;
    private boolean válido;
    private boolean inválido;
    private int númeroDeLíneas;
    private LíneaDeCache[] líneasDeCache;
    private MemoriaPrincipal memoriaPrincipal;

    public Cache(int tamañoDeLaCache, MemoriaPrincipal memoriaPrincipal) {
        this.memoriaPrincipal = memoriaPrincipal;
        this.númeroDeLíneas = tamañoDeLaCache / this.memoriaPrincipal.getTamañoDelBloque();
        líneasDeCache = new LíneaDeCache[númeroDeLíneas];
        instanciarLíneasDeCache();
    }


    /* Método Principal */
    public int DameElDatoDeLaDirección(String direcciónEnBinario){
        // Extraer tag, índice y offset de la dirección binaria
        int offsetBits = (int) (Math.log(memoriaPrincipal.getTamañoDelBloque()) / Math.log(2));
        int indexBits = (int) (Math.log(númeroDeLíneas) / Math.log(2));

        String tag = direcciónEnBinario.substring(0, direcciónEnBinario.length() - offsetBits - indexBits);
        int index = Integer.parseInt(direcciónEnBinario.substring(direcciónEnBinario.length() - offsetBits - indexBits, direcciónEnBinario.length() - offsetBits), 2);
        int offset = Integer.parseInt(direcciónEnBinario.substring(direcciónEnBinario.length() - offsetBits), 2);


        if (tag == líneasDeCache[index].getTag()){
            return líneasDeCache[index].getDato(offset);
        }
        else {
            líneasDeCache[index].setTag(tag);
            líneasDeCache[index].setBloque(memoriaPrincipal.getBloqueEnIndice(SistemasNumericos.binarioADecimalRecibeString(tag)));
            return líneasDeCache[index].getDato(offset);
        }
    }



    private void instanciarLíneasDeCache() {
        for (int i = 0 ; i < númeroDeLíneas; i++){
            líneasDeCache[i] = new LíneaDeCache();
        }
    }


}
