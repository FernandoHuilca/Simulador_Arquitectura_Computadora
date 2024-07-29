public class LineasCaché {
    boolean bitValidez;
    int tag;
    int dato;

    public boolean isBitValidez() {
        return bitValidez;
    }

    public void setBitValidez(boolean bitValidez) {
        this.bitValidez = bitValidez;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public LineasCaché() {
        this.bitValidez = false;
        this.tag = -1;
        this.dato = 0;
    }

}
