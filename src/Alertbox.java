
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Alertbox {

    public void alertConfirm(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText(message);
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

    public void alertAbout() {
        // founder name
        String message = "\n***********************************************\n" +
                "\tMembers Name\t\tID Number\n" +
                "1. Temesgen Abebayehu\tETS 1534/14\n" +
                "2. Tesfamicael Almaw\t\tETS 1544/14\n" +
                "3. Tewuhbo Mihret\t\tETS 1554/14\n" +
                "4. Tinsae Daniel\t\t\tETS 1560/14\n" +
                "5. Tsion Kassahun\t\tETS 1585/14\n" +
                "6. Tsiyon Gashaw\t\t\tETS 1588/14\n" +

                "\n\n\tSubmitted to: Ms. Fuad Yimer" +
                "\n\tSubmission date: Jan 28/2024" +
                "\n***********************************************\n";

        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Information Dialog");
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
