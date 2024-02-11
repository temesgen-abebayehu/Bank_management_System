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
        HBox accouBox = new HBox();
        HBox idBox = new HBox();
        HBox buttonBox = new HBox();

        // control fild
        Label balanceLabel = new Label("Enter Amoun:  ");
        balanceLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
        balanceLabel.setTextFill(Color.WHITE);
        TextField balanceTextField = new TextField();

        Label accouLabel = new Label();
        accouLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
        accouLabel.setTextFill(Color.WHITE);
        TextField accouField = new TextField();

        Label idLabel = new Label("Enter Customer ID:  ");
        idLabel.setFont(Font.font("Times", FontWeight.BOLD, 16));
        idLabel.setTextFill(Color.WHITE);
        TextField idField = new TextField();

        //summit and clear button
        Button sumiButton = new Button("Submit");
        sumiButton.setStyle("-fx-background-color: blue;");
        sumiButton.setTextFill(Color.WHITE);
        Button clearButton = new Button("Clear");

        // set box
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);
        balanceBox.setAlignment(Pos.CENTER_RIGHT);

        accouBox.getChildren().addAll(accouLabel, accouField);
        accouBox.setAlignment(Pos.CENTER_RIGHT);

        idBox.getChildren().addAll(idLabel, idField);
        idBox.setAlignment(Pos.CENTER_RIGHT);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setSpacing(15);
        buttonBox.setPadding(new Insets(15, 0, 0, 120));

        if (message.equals("Transfer")) {
            accouLabel.setText("Enter Reciver Acount No:  ");
            // box conteiner
            vBox.getChildren().addAll(balanceBox, accouBox, buttonBox);

            sumiButton.setOnAction(e -> {
                double bala = Double.parseDouble(balanceTextField.getText());
                int reciverNO = Integer.parseInt(accouField.getText());

                // call withdraw method
                transaction.transferMoney(identifire, bala, reciverNO);
                window.close();
            });
        }

        else {       

            if (message.equals("Deposit")) {
                accouLabel.setText("Enter Account No: ");
                
                //Deposit box
                vBox.getChildren().addAll(accouBox, balanceBox, buttonBox);

                sumiButton.setOnAction(e -> {
                    int accNo = Integer.parseInt(accouField.getText());
                    double bala = Double.parseDouble(balanceTextField.getText());

                    // call diposit method
                    transaction.dipositMoney(accNo, bala);
                    window.close();
                });
            }

            // Withdraw
            else {
                accouLabel.setText("Enter Account NO: ");
                sumiButton.setOnAction(e -> {
                    double bala = Double.parseDouble(balanceTextField.getText());
                    int accNo = Integer.parseInt(accouField.getText());
                    int ID = Integer.parseInt(idField.getText());

                    // call withdraw method
                    transaction.withdrawMoney(accNo,ID, bala);
                    window.close();
                });

                //withdraw box
                vBox.getChildren().addAll(accouBox,idBox,balanceBox, buttonBox);
            }
        }

        clearButton.setOnAction(e -> {
            accouField.setText("");
            balanceTextField.setText("");
        });

        // background image
        Image backgroudImage = new Image("/image/money.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        vBox.setSpacing(10);
        vBox.setBackground(new Background(background));
        vBox.setPadding(new Insets(100, 10, 10, 10));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}
