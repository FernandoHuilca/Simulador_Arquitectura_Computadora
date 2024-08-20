package BusinessLogic.Controllers;

import BusinessLogic.MetodosFrecuentes;
import CPU.CPU;
import CPU.Cache;
import MemoriaPrincipal.MemoriaPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ConfiguracionMemoriasController implements Initializable {

    @FXML
    private Label LabelDireccionRam;
    @FXML
    private Label LabelTamanoBloque;
    @FXML
    private Label LabelTamanoCache;

    @FXML
    private RadioButton Radio4bitsDireccionRam;
    @FXML
    private ToggleGroup GrupoTamañoDireccionBitsRam;
    @FXML
    private RadioButton Radio6bitsDireccionRam;
    @FXML
    private RadioButton Radio8bitsDireccionRam;
    @FXML
    private RadioButton Radio12bitsDireccionRam;
    @FXML
    private RadioButton Radio4BytesBloqueRam;
    @FXML
    private ToggleGroup GrupoTamañoBloqueRam;
    @FXML
    private RadioButton Radio6BytesBloqueRam;
    @FXML
    private RadioButton Radio8BytesBloqueRam;
    @FXML
    private RadioButton Radio12BytesBloqueRam;
    @FXML
    private RadioButton Radio8BytesCache;
    @FXML
    private ToggleGroup GrupoTamañoCacheBytes;
    @FXML
    private RadioButton Radio16BytesCache;
    @FXML
    private RadioButton Radio32BytesCache;
    @FXML
    private RadioButton Radio12BytesCache;
    @FXML
    private Button ButtonSiguiente;
    @FXML
    private Label LabelInfo;

    private int tamañoDeDirecciónBits;
    private int tamañoDeBloqueRam;
    private int tamañoDeCacheBytes;
    private MemoriaPrincipal memoriaPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Añadir listeners a los RadioButtons para actualizar la información
        agregarListeners();
    }

    private void agregarListeners() {
        Radio4bitsDireccionRam.setOnAction(e -> actualizarInfo());
        Radio6bitsDireccionRam.setOnAction(e -> actualizarInfo());
        Radio8bitsDireccionRam.setOnAction(e -> actualizarInfo());
        Radio12bitsDireccionRam.setOnAction(e -> actualizarInfo());
        Radio4BytesBloqueRam.setOnAction(e -> actualizarInfo());
        Radio6BytesBloqueRam.setOnAction(e -> actualizarInfo());
        Radio8BytesBloqueRam.setOnAction(e -> actualizarInfo());
        Radio12BytesBloqueRam.setOnAction(e -> actualizarInfo());
        Radio8BytesCache.setOnAction(e -> actualizarInfo());
        Radio16BytesCache.setOnAction(e -> actualizarInfo());
        Radio32BytesCache.setOnAction(e -> actualizarInfo());
        Radio12BytesCache.setOnAction(e -> actualizarInfo());
    }

private void actualizarInfo() {
    obtenerConfiguración();
    int numeroDeDatosEnRAM = (int) Math.pow(2, tamañoDeDirecciónBits);
    int numeroDeBloques = numeroDeDatosEnRAM / tamañoDeBloqueRam;
    int numeroDeLineasCache = tamañoDeCacheBytes / tamañoDeBloqueRam;

    // Actualizar las etiquetas con la información relevante
    String direccionRamInfo = String.format(
        "Número de datos en RAM: %d",
        numeroDeDatosEnRAM
    );

    String tamanoBloqueInfo = String.format(
        "Número de bloques: %d",
        numeroDeBloques
    );

    String tamanoCacheInfo = String.format(
        "Número de líneas de caché: %d",
        numeroDeLineasCache
    );

    LabelDireccionRam.setText(direccionRamInfo);
    LabelTamanoBloque.setText(tamanoBloqueInfo);
    LabelTamanoCache.setText(tamanoCacheInfo);
}



    @FXML
    private void SiguienteCambiaAPestaña(ActionEvent event) {
        obtenerConfiguración();
        memoriaPrincipal = new MemoriaPrincipal(tamañoDeDirecciónBits, tamañoDeBloqueRam, tamañoDeCacheBytes);
        // Cargar datos desde archivo
        try {
            memoriaPrincipal.cargarDatosDesdeArchivo("src/Data/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE CARGÓ CORRECTAMENTE EL ARCHIVO: " + e.getMessage());
        }
        Cache cache = new Cache(tamañoDeCacheBytes, memoriaPrincipal);
        CPU cpu = new CPU(cache);

        MetodosFrecuentes.cambiarVentanaConObjetos((Stage) ButtonSiguiente.getScene().getWindow(),
                "/Presentation/PestañaPrincipal.fxml",
                "Emulador",
                memoriaPrincipal,
                cache,
                cpu);
    }

    private void obtenerConfiguración() {
        if (Radio4bitsDireccionRam.isSelected()) {
            tamañoDeDirecciónBits = 4;
        } else if (Radio6bitsDireccionRam.isSelected()) {
            tamañoDeDirecciónBits = 6;
        } else if (Radio8bitsDireccionRam.isSelected()) {
            tamañoDeDirecciónBits = 8;
        } else if (Radio12bitsDireccionRam.isSelected()) {
            tamañoDeDirecciónBits = 12;
        }

        if (Radio4BytesBloqueRam.isSelected()) {
            tamañoDeBloqueRam = 4;
        } else if (Radio6BytesBloqueRam.isSelected()) {
            tamañoDeBloqueRam = 6;
        } else if (Radio8BytesBloqueRam.isSelected()) {
            tamañoDeBloqueRam = 8;
        } else if (Radio12BytesBloqueRam.isSelected()) {
            tamañoDeBloqueRam = 12;
        }

        if (Radio8BytesCache.isSelected()) {
            tamañoDeCacheBytes = 8;
        } else if (Radio16BytesCache.isSelected()) {
            tamañoDeCacheBytes = 16;
        } else if (Radio32BytesCache.isSelected()) {
            tamañoDeCacheBytes = 32;
        } else if (Radio12BytesCache.isSelected()) {
            tamañoDeCacheBytes = 12;
        }
    }
}
