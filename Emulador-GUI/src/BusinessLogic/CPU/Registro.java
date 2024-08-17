package CPU;

public class Registro {
    private int valor;
    private String nombreDelRegistro;

    public Registro(int valor,String nombre) {
        this.valor = valor;
        this.nombreDelRegistro = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNombreDelRegistro() {
        return nombreDelRegistro;
    }
}
