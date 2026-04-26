package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Carga la vista principal (el menú)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_beneficiario.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);

        // Agrega los estilos
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setTitle("Sistema de Gestión - Padrón de Beneficiarios");
        stage.setScene(scene);
        stage.show();
    }
}