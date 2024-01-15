import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InputTransaction {
    public void inputTransaction(String message, int identifire) {
        // class
        Transaction transaction = new Transaction();

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(message + " Page");
        window.setMinHeight(400);
        window.setMinWidth(400);

        // box filed
        VBox vBox = new VBox();

        HBox balanceBox = new HBox();
        HBox recivBox = new HBox();
        HBox buttonBox = new HBox();

        // control fild
        Label balanceLabel = new Label("Enter Amoun:  ");
        balanceLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
        balanceLabel.setTextFill(Color.WHITE);
        TextField balanceTextField = new TextField();
        Label recivLabel = new Label("Enter Reciver Acount No:  ");
        recivLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
        recivLabel.setTextFill(Color.WHITE);
        TextField recivField = new TextField();

        Button sumiButton = new Button("Semmit");
        sumiButton.setStyle("-fx-background-color: blue;");
        sumiButton.setTextFill(Color.WHITE);
        Button clearButton = new Button("Clear");

        // set box
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);
        balanceBox.setAlignment(Pos.CENTER_RIGHT);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setSpacing(15);
        buttonBox.setPadding(new Insets(15, 0, 0, 120));

        if (message.equals("Transfer")) {
            recivBox.getChildren().addAll(recivLabel, recivField);
            recivBox.setAlignment(Pos.CENTER_RIGHT);

            // box conteiner
            vBox.getChildren().addAll(balanceBox, recivBox, buttonBox);

            sumiButton.setOnAction(e -> {
                double bala = Double.parseDouble(balanceTextField.getText());
                int reciverNO = Integer.parseInt(recivField.getText());

                // call withdraw method
                transaction.transferMoney(identifire, bala, reciverNO);
                window.close();
            });
        }

        else {

            // box conteiner
            vBox = new VBox();
            vBox.getChildren().addAll(balanceBox, buttonBox);

            if (message.equals("Deposit")) {
                Label accountLabel = new Label("Enter Account Number: ");
                accountLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
                accountLabel.setTextFill(Color.WHITE);
                TextField acountField = new TextField();
                vBox = new VBox();
                HBox acountBox = new HBox();

                acountBox.getChildren().addAll(accountLabel, acountField);
                acountBox.setAlignment(Pos.CENTER_RIGHT);
                vBox.getChildren().addAll(acountBox, balanceBox, buttonBox);

                sumiButton.setOnAction(e -> {
                    int accNo = Integer.parseInt(acountField.getText());
                    double bala = Double.parseDouble(balanceTextField.getText());

                    // call diposit method
                    transaction.dipositMoney(accNo, bala);
                    window.close();
                });
            }

            // Withdraw
            else {
                sumiButton.setOnAction(e -> {
                    double bala = Double.parseDouble(balanceTextField.getText());

                    // call withdraw method
                    transaction.withdrawMoney(identifire, bala);
                    window.close();
                });
            }
        }

        clearButton.setOnAction(e -> {
            recivField.setText("");
            balanceTextField.setText("");
        });

        // background image
        Image backgroudImage = new Image("/image/money.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(400, 400, false, false, false, false));

        vBox.setSpacing(10);
        vBox.setBackground(new Background(background));
        vBox.setPadding(new Insets(100, 10, 10, 10));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}
