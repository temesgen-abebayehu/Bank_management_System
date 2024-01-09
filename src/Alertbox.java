
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Alertbox {

    public void alertConfirm(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle("Information Dialog");
        alert.setHeaderText("This is the header text");
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();

        // Close the application
        primaryStage.close();
    }

    public void alertError(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.ERROR);

        alert.setTitle("Error Dialog");
        alert.setHeaderText("This is the header text");
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();

        // Close the application
        primaryStage.close();
    }

    public void alertWarnning(String message) {
        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is the header text");
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();

        // Close the application
        primaryStage.close();
    }

    public void alertAbout() {
        // founder name
        String message = "\n***********************************************\n" +
                "\tMembers Name\t\tID Number" +
                "1. Temesgen Abebayehu\tETS 1534/14\n" +
                "2. Tesfamicael Almaw\tETS 1544/14\n" +
                "3. Tewuhbo Mihret\tETS 1554/14\n" +
                "4. Tinsae Daniel\tETS 1560/14\n" +
                "5. Tsion Kassahun\tETS 1585/14\n" +
                "6. Tsiyon Gashaw\tETS 1588/14\n" +

                "\n\n\tSubmitted to: Ms. Fuad yimer" +
                "\n\tSubmission date: Nov 18/2023"+
                "\n***************************************************\n";

        Stage primaryStage = new Stage();
        Alert alert = new Alert(AlertType.WARNING);

        alert.setTitle("Warning Dialog");
        alert.setHeaderText("This is the header text");
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();

        // Close the application
        primaryStage.close();
    }
}