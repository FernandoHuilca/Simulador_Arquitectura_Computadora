package CPU;

import MemoriaPrincipal.MemoriaPrincipal;

public class Cache {
    private boolean dirtyBit;
    int[] memoriaCache;
    MemoriaPrincipal memoriaPrincipal;

    public Cache(int tamañoDeLaCache, MemoriaPrincipal memoriaPrincipal){
        memoriaCache = new int[4];
        this.memoriaPrincipal = memoriaPrincipal;
    }
}
