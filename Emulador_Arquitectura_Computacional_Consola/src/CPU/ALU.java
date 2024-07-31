package CPU;

public class ALU { //El cerebro matemático //Ejemplo de alu: Intel 74181 en 1970

    private int entradaA;
    private int entradaB;
    private int códigoDeOperación;
    private int salida;
    //Banderas:
    private boolean overFlow;
    private boolean zero;
    private boolean negative;

    //Constructor General
    public ALU(){
        this.entradaA = 0;
        this.entradaB = 0;
        this.códigoDeOperación = 0;
        this.salida = 0 ;
        this.overFlow = false;
        this.zero = false;
        this.negative = false;
    }


    // ________________ Espacio de Unidad Aritmética ________________
    public double add(double a, double b){
        return a + b;
    }


}
