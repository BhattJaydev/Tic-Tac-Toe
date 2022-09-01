import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class controller_fxml {
    @FXML
    GridPane gridpane;
    @FXML
    Text finishtext;
    @FXML
    Pane main_pane;
    @FXML
    Button undo;
    @FXML
    GridPane scorepane;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Button restart;


    Image img = new Image(String.valueOf(getClass().getResource("O.png")));
    Image img2 = new Image(String.valueOf(getClass().getResource("X.png")));
    int row, col, size = 200;
    functions_backend obj = new functions_backend();
    stack st = new stack();
    ImageView[][] imageViews = new ImageView[obj.n][obj.n];
    BorderPane[][] borderPanes = new BorderPane[obj.n][obj.n];
    private int num = 0;
    Stage Currentstage;
    Stage Parentstage;
//    Stage Currentstage;
    public void setClick(MouseEvent event) throws IOException {
        row = (int) event.getY() / size;
        col = (int) event.getX() / size;
        if (!obj.isEmpty(obj.a, row, col)) return;
        obj.setArray(num, row, col);
        imageViews[row][col] = setImageView(num);
        borderPanes[row][col] = newBorderPane(imageViews[row][col]);
        gridpane.add(borderPanes[row][col], col, row);
        if (!st.push(row * obj.n + col)) return;
        //  Below if condition will execute if any player won the game.
        if (obj.Checker(obj.a, num)) {
            setLine();
            setLabel();
            finishtext.setText(getName(num) + " You won the game");
            gridpane.setDisable(true);
            undo.setDisable(true);
            setWinOrRestart(getName(num)+" won the game! \nDo you want to Restart");
            return;
        }
        //Below if condition will execute if game is tied.
        else if (obj.isTied(obj.a))
        {
            setWinOrRestart("Game Tied! \nDo you want to Restart");
            return;
        }
        toggle();
    }
    public void setWinOrRestart(String label_name) throws IOException {
        FXMLLoader root2 = new FXMLLoader(getClass().getResource("win_or_restart.fxml"));
        Stage stage2 = new Stage();
        Scene scene = new Scene(root2.load());
        stage2.setScene(scene);
        WinOrRestart controller = root2.getController();
        controller.setVariables(label_name,"Restart","Exit");
        controller.setRestart_button(restart);
        controller.setParentStage(Currentstage);
        controller.setCurrentStage(stage2);
//        Currentstage.close();
        stage2.show();
    }
    public String getName(int num)
    {
        if (num==0)return label1.getText();
        return label2.getText();
    }


    public ImageView setImageView(int num) {
        ImageView imageView = new ImageView(getImage(num));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        return imageView;
    }

    public BorderPane newBorderPane(ImageView imageView) {
        BorderPane pane = new BorderPane();
        pane.setCenter(imageView);
        return pane;
    }

    public void setLabel() {
        Label label = (Label) ((BorderPane) getNodeFromGridPane(scorepane, num, 1)).getChildren().get(0);
        label.setText(String.valueOf(Integer.parseInt(label.getText()) + 1));
    }

    public void toggle() {
        if (num == 0) {
            num = 1;
            return;
        }
        num = 0;
    }

    public Image getImage(int num) {
        if (num == 0) return img;
        return img2;
    }

    public String getCh(int num) {
        if (num == 0) return "O";
        return "X";
    }

    public void setLine() {
        if (obj.getWin(obj.a, num) == -1) {
            setRow(obj.win);
        } else if (obj.getWin(obj.a, num) == 0) {
            setCol(obj.win);
        } else {
            setDiag(obj.win);
        }
    }

    public void setUndo(ActionEvent event) {
        setUndo();
    }

    public void setUndo() {
        if (st.isEmpty()) return;
        int row, col;
        int val;
        val = st.pop();
        row = val / obj.n;
        col = val % obj.n;
        gridpane.getChildren().remove(borderPanes[row][col]);
        obj.setArray(-1, row, col);
        toggle();
    }

    public void setRestart(ActionEvent event) {
        setRestart();
    }

    public void setRestart() {
        gridpane.setDisable(false);
        while (!st.isEmpty())
            setUndo();
        finishtext.setText("");
        num = 0;
        undo.setDisable(false);
    }

    public void setRow(int row) {
        for (int i = 0; i < obj.n; i++) {
            borderPanes[row][i].toBack();
            borderPanes[row][i].getStyleClass().add("bdpane");
        }
    }

    public void setCol(int col) {
        for (int i = 0; i < obj.n; i++) {
            borderPanes[i][col].toBack();
            borderPanes[i][col].getStyleClass().add("bdpane");
        }
    }

    public void setDiag(int diag) {
        for (int i = 0; i < obj.n; i++) {
            borderPanes[i][Math.abs(diag - i)].toBack();
            borderPanes[i][Math.abs(diag - i)].getStyleClass().add("bdpane");
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            int colindex = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
            int rowindex = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
            if (colindex == col && rowindex == row) {
                return node;
            }
        }
        return null;
    }

    public void setLabels(String s1, String s2) {
        label1.setText(s1);
        label2.setText(s2);
    }

    public void setExit() throws IOException {
        FXMLLoader root1 = new FXMLLoader(getClass().getResource("outro_page.fxml"));
        Scene scene1 = new Scene(root1.load());
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        OutroPage controller = root1.getController();
        controller.setParentStage(Currentstage);
        controller.setCurrentStage(stage1);
        stage1.show();
    }

    public void setParentStage(Stage stage1)
    {
        Parentstage = stage1;
    }
    public void setCurrentStage(Stage stage1)
    {
        Currentstage = stage1;
    }
    public void setOnClose()
    {
        Currentstage.setOnCloseRequest(event1 -> {
                    try {
                        setExit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
