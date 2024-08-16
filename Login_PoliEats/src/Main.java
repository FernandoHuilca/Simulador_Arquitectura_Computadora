

import BusinessLogic.Controllers.BienvenidaController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fernando_Huilca
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/Bienvenida.fxml"));
        Parent root = loader.load();
        
        BienvenidaController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.start();
        
        primaryStage.setTitle("Open...");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
