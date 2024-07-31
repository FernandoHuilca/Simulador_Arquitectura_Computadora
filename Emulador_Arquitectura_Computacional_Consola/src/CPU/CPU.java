package CPU;

import MemoriaPrincipal.*;

public class CPU {
    private int RI; // Tiene la instrucción a ejecutarse
    private int PC; // Tiene la dirección siguiente a ejecutarse como instrucción
    private int registro1;
    private int registro2;
    private ALU alu;
    private UnidadDeControl unidadDeControl;
    private Cache cache;
    private MemoriaPrincipalInstrucciones memoriaPrincipalInstrucciones;


    public CPU(Cache cache) {
        this.RI = 0;
        this.PC = 0;
        this.registro1 = 0;
        this.registro2 = 0;
        this.alu = new ALU();
        this.unidadDeControl = new UnidadDeControl(this);
        this.cache = cache;
        memoriaPrincipalInstrucciones = new MemoriaPrincipalInstrucciones();
    }

    public void ejecutarInstrucción(Instrucciones instrucción) {
        PC = instrucción.getDirecciónDeMemoria();
        RI = memoriaPrincipalInstrucciones.obtenerElemento(PC);
        unidadDeControl.ejercutarInstrucción(instrucción, registro1, registro2, alu);
    }

    public int getRegistro1() {
        return registro1;
    }

    public int getRegistros2() {
        return registro2;
    }

    public void setRegistro1(int valor) {
        registro1 = valor;
    }

    public int getPC() {
        return PC;
    }

    public int getRI() {
        return RI;
    }
}
