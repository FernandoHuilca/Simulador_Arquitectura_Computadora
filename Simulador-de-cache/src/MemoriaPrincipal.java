import java.util.HashMap;
import java.util.Map;

public class MemoriaPrincipal {
    private Map<Integer, Integer> memoriaPrincipal;

    public MemoriaPrincipal(){
        memoriaPrincipal = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            memoriaPrincipal.put(i, i * 10); //Se llena con algunos valores la memoria
        }
    }
    public int getDatoDeMemoria(int dirección){
        return memoriaPrincipal.get(dirección);
    }
}
