import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class IntroPage {
    @FXML
    TextField field1;
    @FXML
    TextField field2;
    @FXML
    Button submit;
    FXMLLoader root = new FXMLLoader(getClass().getResource("game_ui.fxml"));
    Scene scene;
    Stage stage;
    public void submitBut(ActionEvent event) throws IOException
    {
        scene = field1.getScene();
        stage = (Stage) scene.getWindow();
        scene.setRoot(root.load());
        stage.setWidth(1100);
        stage.setHeight(800);
        controller_fxml obj = root.getController();
        obj.setParentStage(stage);
        obj.setCurrentStage(stage);
        obj.setOnClose();

        String f1 = field1.getText();
        f1 = f1.toUpperCase();
        String f2 = field2.getText();
        f2 = f2.toUpperCase();
        obj.setLabels(f1,f2);
    }
}
