package BusinessLogic.MemoriaPrincipal;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Fernando_Huilca
 */
public class MemoriaPrincipalEntry {
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty valor; // Cambiado de SimpleIntegerProperty a SimpleStringProperty

    public MemoriaPrincipalEntry(String direccion, String valor) {
        this.direccion = new SimpleStringProperty(direccion);
        this.valor = new SimpleStringProperty(valor); // Cambiado para aceptar String
    }

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public String getValor() {
        return valor.get();
    }

    public StringProperty valorProperty() {
        return valor;
    }
}
