package CPU;

public class ALU {
    private static final int MAX_VALUE = 255;  // Valores de 0 a 255 (8 bits sin signo)
    private static final int MIN_VALUE = 0;

    private int entradaA;
    private int entradaB;
    private int salida;
    // Banderas:
    private boolean overFlow;
    private boolean zero;
    private boolean negative;

    // Constructor General
    public ALU(){
        this.entradaA = 0;
        this.entradaB = 0;
        this.salida = 0;
        this.overFlow = false;
        this.zero = false;
        this.negative = false;
    }

    // Métodos para las banderas
    private void updateFlags(int result) {
        this.zero = (result == 0);
        this.negative = (result < MIN_VALUE);
        this.overFlow = (result > MAX_VALUE);
    }

    // ________________ Espacio de Unidad Aritmética ________________

    public int suma(int valor1, int valor2) {
        entradaA = valor1;
        entradaB = valor2;
        salida = valor1 + valor2;
        updateFlags(salida);
        if (overFlow) {
            //salida = MAX_VALUE;
            salida = valor1;
        }
        return salida;
    }

    public int resta(int valor1, int valor2) {
        entradaA = valor1;
        entradaB = valor2;
        salida = valor1 - valor2;
        updateFlags(salida);
        if (negative) {
            salida = valor1;
        }
        return salida;
    }

    public int multiplicar(int valor1, int valor2) {
        entradaA = valor1;
        entradaB = valor2;
        salida = valor1 * valor2;
        updateFlags(salida);
        if (overFlow) {
            //salida = MAX_VALUE;
            salida = valor1;
        }
        return salida;
    }

    // Getters para las banderas
    public boolean isOverFlow() {
        return overFlow;
    }

    public boolean isZero() {
        return zero;
    }

    public boolean isNegative() {
        return negative;
    }

    // Métodos adicionales si son necesarios, como getters para las entradas y la salida
    public int getEntradaA() {
        return entradaA;
    }

    public int getEntradaB() {
        return entradaB;
    }

    public int getSalida() {
        return salida;
    }
}
