
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Alertbox {

    public void alertConfirm(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Confirmion Dialog");
        alert.setContentText(message);
        //alert.setHeaderText(message);
        alert.showAndWait();
        primaryStage.close();
    }

    public void alertError(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("Error Dialog");
        alert.setContentText(message);
        alert.showAndWait();
        primaryStage.close();
    }

    public void alertWarnning(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Warning Dialog");
        alert.setContentText(message);
        alert.showAndWait();
        primaryStage.close();
    }
    
    public void dispalyInfo(String message) {
        

        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
        primaryStage.close();
    }
}
