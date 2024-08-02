package CPU;

import MemoriaPrincipal.*;

public class CPU {
    private int RI; // Tiene la instrucción a ejecutarse
    private int PC; // Tiene la dirección siguiente a ejecutarse como instrucción
    private Registro[] registros;
    private ALU alu;
    private UnidadDeControl unidadDeControl;
    private Cache cache;
    private MemoriaPrincipalInstrucciones memoriaPrincipalInstrucciones;


    public CPU(Cache cache) {
        this.RI = 0;
        this.PC = 0;
        registros= new Registro[2]; //Dos registros en este caso
        registros[0] = new Registro(2,"R1");
        registros[1] = new Registro(0, "R2");
        this.alu = new ALU();
        this.unidadDeControl = new UnidadDeControl();
        this.cache = cache;
        memoriaPrincipalInstrucciones = new MemoriaPrincipalInstrucciones();
    }

    public void ejecutarInstrucción(Instrucciones instrucción, int numRegistro1, int numRegistro2_Dirección) {
        PC = instrucción.getDirecciónDeMemoria();
        RI = memoriaPrincipalInstrucciones.obtenerElemento(PC);
        unidadDeControl.ejecutarInstrucción(instrucción, numRegistro1, numRegistro2_Dirección, alu, this);
    }

    public int getPC() {
        return PC;
    }

    public int getRI() {
        return RI;
    }

    public void setValorRegistro(int numRegistro, int valor) {
        registros[numRegistro].setValor(valor);
    }

    public int getValorRegistro(int numRegistro) {
        return registros[numRegistro].getValor();
    }

    public int getValorMemoriaCache(String direcciónDeMemoriaPrincipal) {
        return cache.DameElDatoDeLaDirección(direcciónDeMemoriaPrincipal); //cache.getValor(direcciónDeMemoriaPrincipal); TODO: hacer esto
    }
}
