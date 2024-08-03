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
        registros[0] = new Registro(0,"R1");
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
        return cache.dameElDatoDeLaDirección(direcciónDeMemoriaPrincipal);
    }


    public void imprimirEnConsolaCPU() {
        System.out.println("__________ Los registros en el CPU __________");
        for (int i = 0; i < registros.length; i++) {
            System.out.println("  Registro" + i + " " +
                    SistemasNumericos.decimalABinarioDevuelveString(getValorRegistro(i), 8) +
                    "(" + getValorRegistro(i) + ")");
        }
        System.out.println("  PC: " + SistemasNumericos.decimalABinarioDevuelveString(getPC(), cache.getTamañoDeDireccion()) +
                "(" + getPC() + ")");
        System.out.println("  RI: " + SistemasNumericos.decimalABinarioDevuelveString(getRI(), cache.getTamañoDeDireccion()) +
                "(" + getRI() + ")");
        System.out.println("___________ Banderas de la ALU ______________");

        // Colores para las banderas
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";

        // Imprimir bandera Zero
        if (alu.isZero()) {
            System.out.println(" Zero     = " +  ANSI_GREEN + alu.isZero() + ANSI_RESET);
        } else {
            System.out.println(" Zero     = " + alu.isZero());
        }

        // Imprimir bandera OverFlow
        if (alu.isOverFlow()) {
            System.out.println(" OverFlow = " + ANSI_GREEN + alu.isOverFlow() + ANSI_RESET);
        } else {
            System.out.println(" OverFlow = " + alu.isOverFlow());
        }

        // Imprimir bandera Negative
        if (alu.isNegative()) {
            System.out.println(" Negative = " + ANSI_GREEN + alu.isNegative() + ANSI_RESET);
        } else {
            System.out.println(" Negative = " + alu.isNegative());
        }
    }


    public int getBitsDeDirección() {
        return cache.getTamañoDeDireccion();
    }

    public void setValorMemoriaCache(int numRegistro, String direcciónABinario) {
        cache.cambiaElDatoEnLaDirección(registros[numRegistro].getValor(), direcciónABinario);
    }
}
