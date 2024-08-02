package CPU;

import MemoriaPrincipal.BloqueDeMemoriaPrincipal;

public class LíneaDeCache {
    private String tag_Etiqueta;
    private BloqueDeMemoriaPrincipal bloque;

    public LíneaDeCache(){
        this.tag_Etiqueta = "0000";
        //this.bloque = new BloqueDeMemoriaPrincipal(bloque.getTamaño());
    }

    public String getTag() {
    return tag_Etiqueta;
    }

    public int getDato(int offset) {
       return bloque.getDatoEnPosición(offset);
    }

    public void setBloque(BloqueDeMemoriaPrincipal bloque) {
        this.bloque = bloque;
    }

    public void setTag(String tag) {
        this.tag_Etiqueta = tag;
    }
}
