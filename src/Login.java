
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class Login {

    // class    
    Customer customer = new Customer();
    Employee employee = new Employee();

   

    static int identifire;

    public int loginForm(String lable, String message) {
        Stage window = new Stage();
        identifire = -1;

        window.initModality(Modality.WINDOW_MODAL);
        window.setTitle("Login");
        window.setMinHeight(400);
        window.setMinWidth(400);

        // box filed
        VBox vBox = new VBox();

        HBox buttonBox = new HBox();
        HBox acountBox = new HBox();
        HBox passwordBox = new HBox();

        Label choice = new Label("Fill Approprate Information ");
        choice.setFont(Font.font("Times", FontWeight.BLACK, 20));
        choice.setTextFill(Color.RED);
        choice.setPadding(new Insets(25, 0, 40, 25));

        Label passwordLabel = new Label("Password:  ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        passwordLabel.setTextFill(Color.WHITE);

        TextField passwordTextField = new TextField();
        TextField accounTextField = new TextField();

        Label accountLabel = new Label(lable);
        accountLabel.setTextFill(Color.WHITE);
        accountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // image icon
        Image passiconImage = new Image(getClass().getResourceAsStream("/image/iconpassword.png"));
        ImageView passicon = new ImageView(passiconImage);
        passicon.setFitWidth(20);
        passicon.setFitHeight(20);

        Image idiconImage = new Image(getClass().getResourceAsStream("/image/iconid.png"));
        ImageView idicon = new ImageView(idiconImage);
        idicon.setFitWidth(20);
        idicon.setFitHeight(20);

        // set box
        acountBox.getChildren().addAll(accountLabel, idicon, accounTextField);
        acountBox.setSpacing(10);
        acountBox.setAlignment(Pos.CENTER_RIGHT);
        passwordBox.getChildren().addAll(passwordLabel, passicon, passwordTextField);
        passwordBox.setSpacing(10);
        passwordBox.setAlignment(Pos.CENTER_RIGHT);

        // buttun
        Button sumiButton = new Button("Semmit");
        Button clearButton = new Button("Clear");

        sumiButton.setStyle("-fx-background-color: blue;");
        sumiButton.setTextFill(Color.WHITE);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setSpacing(15);
        buttonBox.setPadding(new Insets(15, 0, 0, 120));

        // background image
        Image backgroudImage = new Image("/image/lockk.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(400, 400, false, false, false, false));

        // box conteiner
        vBox.getChildren().addAll(choice, acountBox, passwordBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setBackground(new Background(background));
        vBox.setPadding(new Insets(0, 50, 0, 20));

        if (message.equals("User")) {
            sumiButton.setOnAction(e -> {
                int accNo = Integer.parseInt(accounTextField.getText());
                String passcode = passwordTextField.getText();
                identifire = customer.loginCustomer(accNo, passcode);
                window.close();
            });
        }

        else {
            sumiButton.setOnAction(e -> {
                int ID = Integer.parseInt(accounTextField.getText());
                String passcode = passwordTextField.getText();
                identifire = employee.loginEmployee(ID, passcode);
                window.close();
            });
        }

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            passwordTextField.setText("");
        });
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        return identifire;
    }

    
}
