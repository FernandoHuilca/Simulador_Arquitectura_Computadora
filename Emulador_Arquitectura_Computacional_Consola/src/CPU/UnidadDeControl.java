package CPU;

import MemoriaPrincipal.Instrucciones;

public class UnidadDeControl {
    private CPU cpu;
    public UnidadDeControl(CPU cpu) {
    this.cpu = cpu;
    }

    public void ejercutarInstrucción(Instrucciones instrucción, int registro1, int registro2, ALU alu) {
        switch (instrucción) {
            case ADD:
                cpu.setRegistro1(alu.suma(registro1, registro2)) ;
                break;
            /*case STORE:
                registros[reg1].setValor(alu.resta(registros[reg1].getValor(), registros[reg2].getValor()));
                break;
            case AND:
                registros[reg1].setValor(alu.and(registros[reg1].getValor(), registros[reg2].getValor()));
                break;
            case OR:
                registros[reg1].setValor(alu.or(registros[reg1].getValor(), registros[reg2].getValor()));
                break;
            case XOR:
                registros[reg1].setValor(alu.xor(registros[reg1].getValor(), registros[reg2].getValor()));
                break;*/
            default:
                System.out.println("Operación desconocida: " + instrucción);
        }
    }
}
