package CPU;

import MemoriaPrincipal.BloqueDeMemoriaPrincipal;

public class LíneaDeCache {
    private String tag_Etiqueta;
    private BloqueDeMemoriaPrincipal bloque;

    public LíneaDeCache(int tamañoDelBloque){
        this.tag_Etiqueta = "x";
        this.bloque = new BloqueDeMemoriaPrincipal(tamañoDelBloque, -1, -1);
    }

    public String getTag() {
    return tag_Etiqueta;
    }

    public int getDato(int offset) {
       return bloque.getDatoEnPosición(offset);
    }

    public void setDato(int offset, int dato) {
        bloque.setDatoEnPosicion(offset, dato);
    }
    public void setBloque(BloqueDeMemoriaPrincipal bloque) {
        this.bloque = bloque;
    }

    public void setTag(String tag) {
        this.tag_Etiqueta = tag;
    }

    public BloqueDeMemoriaPrincipal getBloque() {
        return bloque;
    }
}
