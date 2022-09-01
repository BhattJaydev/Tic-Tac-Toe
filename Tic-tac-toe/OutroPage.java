import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class OutroPage {
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
    public void no(ActionEvent event) {
        Parentstage.show();
        Currentstage.close();
    }

    public void yes(ActionEvent event) {
        Currentstage.close();
    }
}
