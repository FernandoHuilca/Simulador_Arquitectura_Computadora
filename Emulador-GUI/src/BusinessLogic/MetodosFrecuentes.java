package BusinessLogic;

import BusinessLogic.Controllers.PestañaPrincipalController;
import CPU.CPU;
import CPU.Cache;
import MemoriaPrincipal.MemoriaPrincipal;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MetodosFrecuentes {

    public static void cambiarVentana(Stage currentStage, String rutaFXML, String titulo) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }

    public static void cambiarVentanaConObjetos(Stage stage, String rutaFXML, String titulo, MemoriaPrincipal memoriaPrincipal, Cache cache, CPU cpu) {
    try {
        FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
        Parent root = loader.load();

        // Obtener el controlador de la nueva ventana
        PestañaPrincipalController controller = loader.getController();
        controller.inicializar(memoriaPrincipal, cache, cpu);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
