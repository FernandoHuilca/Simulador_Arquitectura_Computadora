package BusinessLogic.Controllers;

import BusinessLogic.MemoriaPrincipal.MemoriaPrincipalEntry;
import BusinessLogic.MetodosFrecuentes;
import CPU.*;
import CPU.Cache;
import CPU.LíneaDeCache;
import static MemoriaPrincipal.Instrucciones.*;
import MemoriaPrincipal.MemoriaPrincipal;
import MemoriaPrincipal.SistemasNumericos;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PestañaPrincipalController implements Initializable {

     // Define los colores para los estados del dirty bit
    private final Paint colorDirty = Color.web("#E34E51"); // Rojo para dirty
    private final Paint colorClean = Color.web("#A6A6A6"); // Gris para limpio
    
    private final Paint colorOverflow = Color.web("#E34E51"); // Rojo para Overflow
    private final Paint colorZero = Color.web("#E34E51");    // Rojo para Zero
    private final Paint colorNegative = Color.web("#E34E51"); // Rojo para Negative
    private final Paint colorNeutral = Color.web("#A6A6A6"); // Gris para neutral
    
    private MemoriaPrincipal memoriaPrincipal;
    private Cache cache;
    private CPU cpu;
    @FXML
    private ImageView gifImageView;
    @FXML
    private Circle circleDirtyBit;
    @FXML
    private TableView<MemoriaPrincipalEntry> TablaMemoriaPrincipal;
    @FXML
    private TableColumn<MemoriaPrincipalEntry, String> columnaDireccion; // Cambiado a String
    @FXML
    private TableColumn<MemoriaPrincipalEntry, String> columnaValor; // Cambiado a String
    @FXML
    private TableView<RegistroEntry> TablaRegistros;
    @FXML
    private TableColumn<RegistroEntry, String> columnaNombreRegistro;
    @FXML
    private TableColumn<RegistroEntry, Integer> columnaValorRegistro;
    @FXML
    private TableView<CacheEntry> TablaCache;
    @FXML
    private TableColumn<CacheEntry, String> columnaTag;
    @FXML
    private TableColumn<CacheEntry, String> columnaBloque;
    @FXML
    private RadioButton RadioLOAD;
    @FXML
    private ToggleGroup Instruccion;
    @FXML
    private RadioButton RadioMOVE;
    @FXML
    private RadioButton RadioSUB;
    @FXML
    private RadioButton RadioSTORE;
    @FXML
    private RadioButton RadioADD;
    @FXML
    private RadioButton RadioMUL;
    @FXML
    private Button ButtonRealizar;
    @FXML
    private TextField TextRegistro1;
    @FXML
    private TextField TextRegisDirec;
    @FXML
    private Button ButtonVolver;
    @FXML
    private Label labelTag;
    @FXML
    private Label labelIndex;
    @FXML
    private Label labelOffset;
    @FXML
    private Label labelContadorValidos;
    @FXML
    private Label labelContadorInvalidos;
    @FXML
    private Circle circleCeroAlu;
    @FXML
    private Circle circleNegativoAlu;
    @FXML
    private Circle circleOverFlowAlu;
    @FXML
    private Label labelEntradaA;
    @FXML
    private Label labelEntradaB;
    @FXML
    private Label labelSalida;
    @FXML
    private RadioButton RadioBUBBLE;
    @Override           
public void initialize(URL url, ResourceBundle rb) {
    // Configura las columnas de la tabla de registros
    columnaNombreRegistro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    columnaValorRegistro.setCellValueFactory(new PropertyValueFactory<>("valor"));
    // Configura las columnas de la tabla de memoria principal
    columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    columnaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    TablaMemoriaPrincipal.setRowFactory(tv -> new ColoredTableRow<>(memoriaPrincipal.getTamañoDelBloque()));

    // Configura las columnas de la tabla de caché
    columnaTag.setCellValueFactory(new PropertyValueFactory<>("tag"));
    columnaBloque.setCellValueFactory(new PropertyValueFactory<>("bloqueContenido"));
    
    // Cargar el GIF en el ImageView (sin mostrarlo aún)
    gifImageView.setVisible(false); // Lo ocultas inicialmente
}


@FXML
    private void manejarRealizarInstruccion() {
        // Obtén el valor del RadioButton seleccionado
        RadioButton selectedRadioButton = (RadioButton) Instruccion.getSelectedToggle();
        String instruccionSeleccionada = selectedRadioButton != null ? selectedRadioButton.getText() : "";

         // Obtén los datos de los TextField y realiza la conversión a enteros
    int registro1 = convertirTextoAEntero(TextRegistro1.getText());
    int regisMemoria = convertirTextoAEntero(TextRegisDirec.getText());

        // Ejecuta la operación correspondiente
        switch (instruccionSeleccionada) {
            case "LOAD":
                realizarLOAD(registro1, regisMemoria);
                break;
            case "MOVE":
                realizarMOVE(registro1, regisMemoria);
                break;
            case "SUB":
                realizarSUB(registro1, regisMemoria);
                break;
            case "STORE":
                realizarSTORE(registro1, regisMemoria);
                break;
            case "ADD":
                realizarADD(registro1, regisMemoria);
                break;
            case "MUL":
                realizarMUL(registro1, regisMemoria);
                break;
            case "BUBBLE":
                realizarBUBBLE(registro1, regisMemoria);
                break;
            default:
                System.out.println("Instrucción no válida");
                break;
        }
         actualizarInformacionCache();
         // Actualizar la información de la ALU
         actualizarInformacionALU();
         // Actualizar la informacionde los registros:
         actualizarInformacionRegistros();
         //Actualizar la tabla de memoriaPrincipal
         actualizarLaTablaDeMemoriaPrincipal();
         //Actualizar tabla de cache
         actualizarLaTablaDeCache();
    }

   private void realizarLOAD(int registro1, int regisMemoria) {
   /*
       // Mostrar el GIF antes de ejecutar la instrucción
    gifImageView.setVisible(true);

    // Define la duración total de la animación (por ejemplo, 5 segundos)
    Duration totalDuration = Duration.seconds(2);

    // Mueve la imagen de 0 a 200 unidades en X y de 0 a -100 unidades en Y
    double totalMovementX = 7;
    double totalMovementY = 0;

    // Crear un Timeline con pasos periódicos
    Timeline timeline = new Timeline();

    // Define el intervalo de actualización (por ejemplo, 0.01 segundos)
    Duration interval = Duration.millis(500);

    // Crea el KeyFrame que actualiza la posición
    KeyFrame keyFrame = new KeyFrame(interval, event -> {
        // Calcula el tiempo transcurrido desde el inicio
        double elapsedTime = totalDuration.toMillis() - timeline.getCurrentTime().toMillis();
        double progress = (totalDuration.toMillis() - elapsedTime) / totalDuration.toMillis();

        // Calcula el desplazamiento en base al progreso
        double moveX = totalMovementX * progress;
        double moveY = totalMovementY * progress;

        // Actualiza la posición de la imagen
        gifImageView.setLayoutX(gifImageView.getLayoutX() + moveX);
        gifImageView.setLayoutY(gifImageView.getLayoutY() + moveY);
    });

    // Agrega el KeyFrame al Timeline
    timeline.getKeyFrames().add(keyFrame);

    // Configura el Timeline para que se ejecute durante el totalDuration
    timeline.setCycleCount(Timeline.INDEFINITE); // Necesario para que funcione en el intervalo
    timeline.setRate(1 / (interval.toMillis() / 15000.0)); // Ajusta la velocidad

    // Inicia la animación
    timeline.play();

    // Ocultar el GIF después de la duración total de la animación
    Timeline hideTimeline = new Timeline(new KeyFrame(
        totalDuration, // Duración antes de ocultar el GIF
        ae -> gifImageView.setVisible(false)
    ));
    hideTimeline.play();
*/
    // Ejecutar la instrucción LOAD en la CPU
    cpu.ejecutarInstrucción(LOAD, registro1, regisMemoria);
}




    private void realizarMOVE(int registro1, int regisMemoria) {
         cpu.ejecutarInstrucción(MOVE, registro1, regisMemoria);
    }

    private void realizarSUB(int registro1, int regisMemoria) {
         cpu.ejecutarInstrucción(SUB, registro1, regisMemoria);
    }

    private void realizarSTORE(int registro1, int regisMemoria) {
       cpu.ejecutarInstrucción(STORE, registro1, regisMemoria);
    }

    private void realizarADD(int registro1, int regisMemoria) {
         cpu.ejecutarInstrucción(ADD, registro1, regisMemoria);
    }

    private void realizarMUL(int registro1, int regisMemoria) {
       cpu.ejecutarInstrucción(MUL, registro1 , regisMemoria);
    }


@FXML
private void volverAConfiguracionMemorias(){
    MetodosFrecuentes.cambiarVentana((Stage) ButtonVolver.getScene().getWindow(), "/Presentation/ConfiguracionMemorias.fxml", "ConfiguraciónMemorias");
}
public void inicializar(MemoriaPrincipal memoriaPrincipal, Cache cache, CPU cpu) {
    this.memoriaPrincipal = memoriaPrincipal;
    this.cache = cache;
    this.cpu = cpu;
    actualizarInformacionCache();
     // Actualizar la información de la ALU
        actualizarInformacionALU();
        // Actualizar la informacionde los registros:
         actualizarInformacionRegistros();
    
         //Actualizar la tabla de memoria principal
    actualizarLaTablaDeMemoriaPrincipal();
    //Actualizar tabla de cache
    actualizarLaTablaDeCache();
    
}
private void actualizarLaTablaDeCache(){
    // Actualiza la tabla de caché
    ObservableList<CacheEntry> cacheData = FXCollections.observableArrayList();
    for (LíneaDeCache linea : cache.getLíneasDeCache()) {
        cacheData.add(new CacheEntry(linea.getTag(), linea.getBloque().getNombre()));
    }
    TablaCache.setItems(cacheData);
}
private void actualizarLaTablaDeMemoriaPrincipal(){
    // Actualiza la tabla de memoria principal
    ObservableList<MemoriaPrincipalEntry> data = FXCollections.observableArrayList();
    String[] datos = memoriaPrincipal.getDatosBinariosStrings(); // Obtiene los datos en el formato binario
    for (int i = 0; i < datos.length; i++) {
        String direccionBinario = SistemasNumericos.decimalABinarioDevuelveString(i, memoriaPrincipal.getTamañoDeDirecciónBits()) + "(" + i + ")";
        String valorBinario = datos[i];
        data.add(new MemoriaPrincipalEntry(direccionBinario, valorBinario)); // Se pasa el formato String
    }
    TablaMemoriaPrincipal.setItems(data);
}
private void actualizarInformacionCache() {
        if (cache != null) {
            labelTag.setText("Tag: " + cache.getTag());
            labelIndex.setText("Index: " + cache.getIndex());
            labelOffset.setText("Offset: " + cache.getOffset());
            labelContadorValidos.setText("Válidos: " + cache.getContadorDeVálidos());
            labelContadorInvalidos.setText("Inválidos: " + cache.getContadorDeInVálidos());
            boolean dirtyBit = cache.isDirtyBit();
        circleDirtyBit.setFill(dirtyBit ? colorDirty : colorClean);
        }
    }
private void actualizarInformacionALU() {
        if (cpu != null && cpu.getALU() != null) {
            if (cpu != null && cpu.getALU() != null) {
            // Obtén los valores actuales de la ALU
            int entradaA = cpu.getALU().getEntradaA();
            int entradaB = cpu.getALU().getEntradaB();
            int salida = cpu.getALU().getSalida();
            
             // Actualiza los Labels con los valores obtenidos
            labelEntradaA.setText("A: " + SistemasNumericos.decimalABinarioDevuelveString(entradaA, 8) + "("+ entradaA +")");
            labelEntradaB.setText("B: " + SistemasNumericos.decimalABinarioDevuelveString(entradaB, 8) + "("+ entradaB +")");
            labelSalida.setText("Salida: " + SistemasNumericos.decimalABinarioDevuelveString(salida, 8) + "("+ salida +")");
            
            boolean overflow = cpu.getALU().isOverFlow();
            boolean zero = cpu.getALU().isZero();
            boolean negative = cpu.getALU().isNegative();

            // Actualiza los círculos con los colores correspondientes
            circleOverFlowAlu.setFill(overflow ? colorOverflow : colorNeutral);
            circleCeroAlu.setFill(zero ? colorZero : colorNeutral);
            circleNegativoAlu.setFill(negative ? colorNegative : colorNeutral);
        }
    }
}
private int convertirTextoAEntero(String texto) {
    try {
        return Integer.parseInt(texto);
    } catch (NumberFormatException e) {
        // Maneja el caso en que el texto no es un número válido
        System.out.println("Número no válido: " + texto);
        return 0; // O cualquier valor predeterminado que consideres apropiado
    }
}

private void actualizarInformacionRegistros() {
    if (cpu != null) {
        // Crea una lista observable para la tabla de registros
        ObservableList<RegistroEntry> registrosData = FXCollections.observableArrayList();
        
        // Agrega el PC y RI a la tabla
        registrosData.add(new RegistroEntry("PC",SistemasNumericos.decimalABinarioDevuelveString(cpu.getPC(), memoriaPrincipal.getTamañoDeDirecciónBits()) + "(" + cpu.getPC() + ")"));
        registrosData.add(new RegistroEntry("RI", SistemasNumericos.decimalABinarioDevuelveString(cpu.getRI(), memoriaPrincipal.getTamañoDeDirecciónBits()) + "(" + cpu.getRI() + ")"));
        
        // Agrega los registros individuales
        for (int i = 0; i < 2; i++) { // Supone que tienes 2 registros (R0, R1)
            registrosData.add(new RegistroEntry("R" + i, SistemasNumericos.decimalABinarioDevuelveString(cpu.getValorRegistro(i), memoriaPrincipal.getTamañoDeDirecciónBits()) + "(" + cpu.getValorRegistro(i) + ")"));
        }
        
        // Establece los datos en la tabla
        TablaRegistros.setItems(registrosData);
    }
}

    private void actualizarMemoriaYTxt() {
       try {
            memoriaPrincipal.cargarDatosDesdeArchivo("src/Data/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE CARGÓ CORRECTAMENTE EL ARCHIVO: " + e.getMessage());
        }
        try {
           memoriaPrincipal.guardarDatosEnArchivo("src/Data/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE CARGÓ CORRECTAMENTE EL ARCHIVO: " + e.getMessage());
        }
       
    } 
    private void realizarBUBBLE3(int registro1, int regisMemoria) {
    int aux = registro1;
    boolean intercambio; 
    for (int i = 0; i < aux - 1; i++) {
        intercambio = false;
        for (int j = 0; j < aux - 1 - i; j++) {
            realizarLOAD(0, j);        // Cargar valor de la posición j en R0
            realizarLOAD(1, j + 1);    // Cargar valor de la posición j+1 en R1
            realizarSUB(0, 1);         // Comparar R0 y R1 (R0 - R1)
                        System.out.println("AQUIII" + j);

            if (!cpu.getALU().isNegative()) { // Si R0 >= R1, intercambiar
                // Intercambiar los valores si están en el orden incorrecto
                realizarLOAD(0, j);
                realizarSTORE(1, j);      // Almacenar R1 en la posición j
                realizarSTORE(0, j + 1);  // Almacenar R0 en la posición j+1
                intercambio = true; 
            }
        }
        // Si no hubo intercambios, el arreglo ya está ordenado
        if (!intercambio) {
            break;
        }
    }
}

    
    /*
    private void realizarBUBBLE(int registro1, int regisMemoria) {
    int n = registro1; // Se asume que 'registro1' es la longitud del array
    boolean[] intercambio = {false};

    // Crear un Timeline para manejar la animación
    Timeline timeline = new Timeline();

    // Duración del retraso entre cada paso del Bubble Sort
    Duration delay = Duration.seconds(1);
    int totalKeyFrames = 0; // Contador de KeyFrames

    for (int i = 0; i < n - 1; i++) {
        intercambio[0] = false;
        for (int j = 0; j < n - 1 - i; j++) {
            int finalJ = j; // Necesario para la expresión lambda
            int index = totalKeyFrames++; // Índice único para cada KeyFrame

            // Añadir un KeyFrame para cada comparación e intercambio
            timeline.getKeyFrames().add(new KeyFrame(
                delay.multiply(index), // Tiempo para cada KeyFrame
                event -> {
                    realizarLOAD(0, finalJ);       // Cargar valor de la posición j en R0
                    realizarLOAD(1, finalJ + 1);   // Cargar valor de la posición j+1 en R1
                    realizarSUB(0, 1);            // Comparar R0 y R1 (R0 - R1)

                    if (!cpu.getALU().isNegative()) { // Si R0 >= R1, intercambiar
                        realizarLOAD(0, finalJ);
                        realizarSTORE(1, finalJ);      // Almacenar R1 en la posición j
                        realizarSTORE(0, finalJ + 1);  // Almacenar R0 en la posición j+1
                        intercambio[0] = true;
                    }

                    // Actualizar la información de la ALU y tablas
                    actualizarInformacionCache();
                    actualizarInformacionALU();
                    actualizarInformacionRegistros();
                    actualizarLaTablaDeMemoriaPrincipal();
                    actualizarLaTablaDeCache();
                }
            ));
        }
        // Si no hubo intercambios, el arreglo ya está ordenado
        if (!intercambio[0]) {
            break;
        }
    }

    // Asegúrate de que la animación se repita o se detenga adecuadamente
    timeline.setOnFinished(event -> {
        // Aquí puedes agregar código para manejar lo que sucede después de la animación
        System.out.println("Ordenamiento completado.");
    });

    // Reproducir la animación
    timeline.play();
}*/

    private void realizarBUBBLE(int registro1, int regisMemoria) {
    int n = registro1; // Se asume que 'registro1' es la longitud del array
    boolean[] intercambio = {false};

    // Crear un Timeline para manejar la animación
    Timeline timeline = new Timeline();

    // Duración del retraso entre cada paso del Bubble Sort
    Duration delay = Duration.seconds(1);
    int totalKeyFrames = 0; // Contador de KeyFrames

    for (int i = 0; i < n - 1; i++) {
        intercambio[0] = false;
        for (int j = 0; j < n - 1 - i; j++) {
            int finalJ = j; // Necesario para la expresión lambda
            int index = totalKeyFrames++; // Índice único para cada KeyFrame

            // Añadir un KeyFrame para cada comparación e intercambio
            timeline.getKeyFrames().add(new KeyFrame(
                delay.multiply(index), // Tiempo para cada KeyFrame
                event -> {
                    realizarLOAD(0, finalJ);       // Cargar valor de la posición j en R0
                    realizarLOAD(1, finalJ + 1);   // Cargar valor de la posición j+1 en R1
                    realizarSUB(0, 1);            // Comparar R0 y R1 (R0 - R1)

                    if (!cpu.getALU().isNegative()) { // Si R0 >= R1, intercambiar
                        realizarLOAD(0, finalJ);
                        realizarSTORE(1, finalJ);      // Almacenar R1 en la posición j
                        realizarSTORE(0, finalJ + 1);  // Almacenar R0 en la posición j+1
                        intercambio[0] = true;
                    }

                    // Actualizar la información de la ALU y tablas
                    actualizarInformacionCache();
                    actualizarInformacionALU();
                    actualizarInformacionRegistros();
                    actualizarLaTablaDeMemoriaPrincipal();
                    actualizarLaTablaDeCache();
                }
            ));
        }
        // Si no hubo intercambios, el arreglo ya está ordenado
        if (!intercambio[0]) {
            break;
        }
    }

    // Después de que la animación termine, verifica si es necesario seguir con la recursión
    timeline.setOnFinished(event -> {
        if (registro1 > 1) {
            realizarBUBBLE(registro1 - 1, regisMemoria);
        }
        else{
                     realizarLOAD(0, 0);       // Cargar valor de la posición j en R0
                    realizarLOAD(1, 1);   // Cargar valor de la posición j+1 en R1
                    realizarSUB(0, 1);            // Comparar R0 y R1 (R0 - R1)

                    if (!cpu.getALU().isNegative()) { // Si R0 >= R1, intercambiar
                        realizarLOAD(0, 0);
                        realizarSTORE(1, 0);      // Almacenar R1 en la posición j
                        realizarSTORE(0, 1);  // Almacenar R0 en la posición j+1
                    }
                     // Actualizar la información de la ALU y tablas
                    actualizarInformacionCache();
                    actualizarInformacionALU();
                    actualizarInformacionRegistros();
                    actualizarLaTablaDeMemoriaPrincipal();
                    actualizarLaTablaDeCache();
        }
    });

    // Reproducir la animación
    timeline.play();
}

}