import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WinOrRestart {
    @FXML
    Label label;
    @FXML
    Button button1;
    @FXML
    Button button2;
    Button restart_button;
    Stage Parentstage;
    Stage Currentstage;
    public void setCurrentStage(Stage stage1)
    {
        Currentstage = stage1;
    }
    public void setParentStage(Stage stage1)
    {
        Parentstage = stage1;
    }
    public void yes(ActionEvent event) {
        if (!Parentstage.isShowing())Parentstage.show();

        restart_button.fire();
        Currentstage.close();
    }

    public void no(ActionEvent event) throws IOException {
        FXMLLoader root1 = new FXMLLoader(getClass().getResource("outro_page.fxml"));
        Scene scene1 = new Scene(root1.load());
        Stage stage1 = new Stage();
        OutroPage controller = root1.getController();
        controller.setParentStage(Currentstage);
        controller.setCurrentStage(stage1);
        stage1.setScene(scene1);
        stage1.show();
        Parentstage.close();
        Currentstage.close();
    }

    public void setVariables(String label_name, String button1_name, String button2_name) {
        label.setText(label_name);
        button1.setText(button1_name);
        button2.setText(button2_name);
    }

    public void setRestart_button(Button restart) {
        restart_button = restart;
    }
}
