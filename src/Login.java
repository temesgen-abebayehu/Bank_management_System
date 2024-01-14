
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();

    public void inputTransaction(String message, int identifire) {
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

    static String newData = "";

    public String acceptNewdata(String choice) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Input Data Page");
        window.setMinHeight(300);
        window.setMinWidth(400);

        // box filed
        VBox vBox = new VBox();

        HBox buttonBox = new HBox();
        HBox nameBox = new HBox();

        Label nameLabel = new Label("Enter " + choice + " : ");
        TextField nameTextField = new TextField();

        // control fild
        Button sumiButton = new Button("Semmit");
        sumiButton.setStyle("-fx-background-color: blue;");
        sumiButton.setTextFill(Color.WHITE);
        Button clearButton = new Button("Clear");

        // summit and reset
        sumiButton.setOnAction(e -> {
            newData = nameTextField.getText();
            window.close();
        });

        clearButton.setOnAction(e -> {
            nameTextField.setText("");
        });

        nameBox.getChildren().addAll(nameLabel, nameTextField);
        nameBox.setAlignment(Pos.CENTER_RIGHT);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(15, 0, 0, 120));

        // box conteiner
        vBox.getChildren().addAll(nameBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: lightblue;");
        vBox.setPadding(new Insets(50, 50, 0, 0));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        return newData;
    }

    static String gender = "";

    public void addNewdata(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New " + message);
        window.setMinHeight(500);
        window.setMinWidth(400);

        // box filed
        VBox vBox = new VBox();

        HBox nameBox = new HBox();
        HBox balanceBox = new HBox();
        HBox passwordBox = new HBox();
        HBox idBox = new HBox();
        HBox buttonBox = new HBox();
        HBox addresBox = new HBox();
        HBox emailBox = new HBox();
        HBox sexBox = new HBox();

        // input fild
        Label idLabel = new Label("Enter ID: ");
        TextField idField = new TextField();

        Label nameLabel = new Label("Enter Name: ");
        TextField nameTextField = new TextField();

        Label passwordLabel = new Label("Enter Password: ");
        TextField passwordTextField = new TextField();

        Label addresLabel = new Label("Enter Address: ");
        TextField addresField = new TextField();

        Label balanceLabel = new Label("Enter Initial Balance: ");
        TextField balanceTextField = new TextField();

        Label emaiLabel = new Label("Enter Email: ");
        TextField emailField = new TextField();

        // control fild
        Button sumiButton = new Button("Semmit");
        Button clearButton = new Button("Clear");

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.setPromptText("What is your Sex");
        genderComboBox.getItems().addAll("M", "F");

        // Handle ComboBox selection changes
        genderComboBox.setOnAction(e -> {
            gender = genderComboBox.getValue();
        });

        // call addcustomer method
        if (message.equals("Customer")) {
            balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
            balanceBox.setAlignment(Pos.CENTER_RIGHT);
            sumiButton.setOnAction(e -> {
                int id = Integer.parseInt(idField.getText());
                String name = nameTextField.getText();
                String password = passwordTextField.getText();
                String address = addresField.getText();
                double balance = Double.parseDouble(balanceTextField.getText());
                String email = emailField.getText();

                customer.addCustomer(id, name, password, address, balance, gender, email);
                window.close();
            });

            vBox.getChildren().addAll(idBox, nameBox, passwordBox, addresBox, emailBox, balanceBox, sexBox, buttonBox);
        }

        else {
            // call addEmployee method
            sumiButton.setOnAction(e -> {
                int id = Integer.parseInt(idField.getText());
                String name = nameTextField.getText();
                String password = passwordTextField.getText();
                String address = addresField.getText();
                String email = emailField.getText();

                employee.addEmployee(id, name, password, address, gender, email);
                window.close();
            });

            vBox.getChildren().addAll(idBox, nameBox, passwordBox, addresBox, emailBox, sexBox, buttonBox);
        }

        //clear data
        clearButton.setOnAction(e -> {
            idField.setText("");
            nameTextField.setText("");
            passwordTextField.setText("");
            addresField.setText("");
            balanceTextField.setText("");
            emailField.setText("");
        });

        // set input fild
        idBox.getChildren().addAll(idLabel, idField);
        idBox.setAlignment(Pos.CENTER_RIGHT);

        nameBox.getChildren().addAll(nameLabel, nameTextField);
        nameBox.setAlignment(Pos.CENTER_RIGHT);

        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setAlignment(Pos.CENTER_RIGHT);

        addresBox.getChildren().addAll(addresLabel, addresField);
        addresBox.setAlignment(Pos.CENTER_RIGHT);

        emailBox.getChildren().addAll(emaiLabel, emailField);
        emailBox.setAlignment(Pos.CENTER_RIGHT);

        sexBox.getChildren().addAll(genderComboBox);
        sexBox.setPadding(new Insets(15));
        sexBox.setAlignment(Pos.CENTER_RIGHT);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setSpacing(15);
        buttonBox.setPadding(new Insets(15, 0, 0, 120));

        sumiButton.setStyle("-fx-background-color: blue;");
        sumiButton.setTextFill(Color.WHITE);

        // box conteiner

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: lightblue;");
        vBox.setPadding(new Insets(10, 50, 10, 10));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}
