package CPU;

import MemoriaPrincipal.Instrucciones;
import MemoriaPrincipal.SistemasNumericos;

public class UnidadDeControl {

    public void ejecutarInstrucción(Instrucciones instrucción, int numRegistro1, int numRegistro2_Dirección, ALU alu, CPU cpu) {
        switch (instrucción) {
            case ADD:
                cpu.setValorRegistro(numRegistro1, (alu.suma(cpu.getValorRegistro(numRegistro1), cpu.getValorRegistro(numRegistro1))));
                break;
            case LOAD:
                String direcciónABinario = SistemasNumericos.decimalABinarioDevuelveString(numRegistro2_Dirección);
                cpu.setValorRegistro(numRegistro1, cpu.getValorMemoriaCache(direcciónABinario));
                break;
            /*case AND:
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
