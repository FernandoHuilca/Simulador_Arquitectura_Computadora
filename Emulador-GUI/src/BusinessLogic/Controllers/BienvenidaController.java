package BusinessLogic.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fernando_Huilca
 */
public class BienvenidaController implements Initializable {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Este método se deja vacío ya que la inicialización se hace en el método start
    }

    public void start() {
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        // No necesitas volver a cargar la pantalla de bienvenida aquí
        // La pantalla de bienvenida ya se cargó en el método start de Main

        // Simula un tiempo de carga de 3 segundos
        new Thread(() -> {
            try {
                Thread.sleep(2000); // 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> showExplanationScreen());
        }).start();
    }

   private void showExplanationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/ConfiguracionMemorias.fxml"));
            Parent root = loader.load();
            // Cambia la escena en el Stage actual
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
