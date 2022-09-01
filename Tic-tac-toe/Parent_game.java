import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Parent_game extends Application {
    boolean flag = true;
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("intro_page.fxml"));
        Scene scene = new Scene(root.load());
        scene.setOnKeyPressed(event -> {
            if (flag){
            if (event.getCode() == KeyCode.ENTER)
            {
                IntroPage controller = root.getController();
                controller.submit.fire();
                flag = false;
            }
        }});
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
