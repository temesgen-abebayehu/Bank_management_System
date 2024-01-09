
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login {

    // box filed
    VBox vBox = new VBox();

    HBox acountBox = new HBox();
    HBox nameBox = new HBox();
    HBox balanceBox = new HBox();
    HBox passwordBox = new HBox();
    HBox idBox = new HBox();
    HBox buttonBox = new HBox();
    HBox recivBox = new HBox();
    HBox addresBox = new HBox();
    HBox emailBox = new HBox();
    HBox sexBox = new HBox();

    // control fild
    Button sumiButton = new Button("Semmit");
    Button clearButton = new Button("Clear");

    Label idLabel = new Label();
    TextField idField = new TextField();
    Label nameLabel = new Label();
    TextField nameTextField = new TextField();
    Label passwordLabel = new Label();
    TextField passwordTextField = new TextField();
    Label addresLabel = new Label();
    TextField addresField = new TextField();
    Label accountLabel = new Label();
    TextField accounTextField = new TextField();
    Label balanceLabel = new Label();
    TextField balanceTextField = new TextField();
    Label emaiLabel = new Label();
    TextField emailField = new TextField();

    Label recivLabel = new Label();
    TextField recivField = new TextField();

    // class
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();

    public void textwithdraw() {

        Stage window = new Stage();

        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Withdraw Page");
        window.setMinHeight(400);
        window.setMinWidth(400);

        accountLabel.setText("Enter Account Number: ");
        balanceLabel.setText("Enter Amoun:   ");
        passwordLabel.setText("Enter Your Password: ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setSpacing(10);

        // buttun
        sumiButton.setPadding(new Insets(15, 15, 15, 15));
        sumiButton.setStyle("-fx-background-color: YELLOW;");

        clearButton.setPadding(new Insets(15, 15, 15, 15));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, balanceBox, passwordBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: GRAY;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());
            String passcode = passwordTextField.getText();

            // call withdraw method
            transaction.withdrawMoney(accNo, bala, passcode);
            window.close();
        });

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            passwordTextField.setText("");
            balanceTextField.setText("");
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: LIGHTGRAY;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void textTransfer() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Transfer Page");
        window.setMinHeight(400);
        window.setMinWidth(400);

        accountLabel.setText("Enter Account Number: ");
        balanceLabel.setText("Enter Amoun:  ");
        passwordLabel.setText("Enter Your Password: ");
        recivLabel.setText("Enter Reciver Number: ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setSpacing(10);
        recivBox.getChildren().addAll(recivLabel, recivField);

        // buttun
        sumiButton.setText("Summit");
        sumiButton.setPadding(new Insets(15, 15, 15, 15));

        clearButton.setText("Clear");
        clearButton.setPadding(new Insets(15, 15, 15, 15));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, balanceBox, passwordBox, recivBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: GRAY;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());
            String passcode = passwordTextField.getText();
            int reciverNO = Integer.parseInt(recivField.getText());

            // call withdraw method
            transaction.transferMoney(accNo, bala, passcode, reciverNO);
            window.close();
        });

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            passwordTextField.setText("");
            balanceTextField.setText("");
            recivField.setText("");
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: LIGHTGRAY;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void textDeposit() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Deposit Page");
        window.setMinHeight(400);
        window.setMinWidth(400);

        accountLabel.setText("Enter Account Number: ");
        balanceLabel.setText("Enter Amoun:  ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);

        // buttun
        sumiButton.setText("Summit");
        sumiButton.setPadding(new Insets(15, 15, 15, 15));

        clearButton.setText("Clear");
        clearButton.setPadding(new Insets(15, 15, 15, 15));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, balanceBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: BROWN;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());

            // call diposit method
            transaction.dipositMoney(accNo, bala);
            window.close();
        });

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            balanceTextField.setText("");
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: MAGENTA;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    static int account_no = 0;

    public int customerLogin() {
        Stage window = new Stage();

        window.initModality(Modality.WINDOW_MODAL);
        window.setTitle("Login");
        window.setMinHeight(400);
        window.setMinWidth(400);

        accountLabel.setText("Enter Account Number: ");
        passwordLabel.setText("Enter Password:  ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setSpacing(10);

        // buttun
        sumiButton.setText("Summit");
        sumiButton.setPadding(new Insets(10, 10, 10, 10));
        sumiButton.setStyle("-fx-background-color: yellow;");

        clearButton.setText("Clear");
        clearButton.setPadding(new Insets(10, 10, 10, 10));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, passwordBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: GRAY;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            String passcode = passwordTextField.getText();
            account_no = customer.loginCustomer(accNo, passcode);
            window.close();
        });

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            balanceTextField.setText("");
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: rgb(128, 200, 255);");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 700, 500);
        window.setScene(scene);
        window.showAndWait();

        return account_no;
    }

    static String updateChoice;
     public String updateButton() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Update Page");
        window.setMinHeight(400);
        window.setMinWidth(400);

        // button
        Button nameButton = new Button("Update Name");
        Button passwordButton = new Button("Update Password");
        Button sexButton = new Button("Update Sex");
        Button emailButton = new Button("Update Email");

        nameButton.setOnAction(e -> {
            updateChoice = "Name";
            window.close();
        });
        passwordButton.setOnAction(e -> {
            updateChoice = "Password";
            window.close();
        });
        sexButton.setOnAction(e -> {
            updateChoice = "Sex";
            window.close();
        });
        emailButton.setOnAction(e -> {
            updateChoice = "Email";
            window.close();
        });


        // box container
        VBox vBox = new VBox(10, nameButton, passwordButton, sexButton, emailButton);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: GRAY;");
        vBox.setPadding(new Insets(10));

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: CYAN;");
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return updateChoice;
    }

    static String newData;

    public String acceptNewdata(String choice) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Input Data Page");
        window.setMinHeight(300);
        window.setMinWidth(400);

        nameLabel.setText("Enter " + choice + " : ");
        sumiButton.setText("Summit");
        clearButton.setText("Clear");

        // summit and reset
        sumiButton.setOnAction(e -> {
            newData = nameTextField.getText();
            window.close();
        });

        clearButton.setOnAction(e -> {
            nameTextField.setText("");
        });

        nameBox.getChildren().addAll(nameLabel, nameTextField);

        buttonBox.getChildren().addAll(sumiButton, clearButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        // box conteiner
        vBox.getChildren().addAll(nameBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setStyle("-fx-background-color: GRAY;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: CYAN;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return newData;
    }

    static String gender = "";

    public void writeCustomer() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Custemer");
        window.setMinHeight(500);
        window.setMinWidth(400);

        // input fild
        idLabel.setText("Enter ID: ");
        nameLabel.setText("Enter Name: ");
        passwordLabel.setText("Enter Password: ");
        addresLabel.setText("Enter Address: ");
        balanceLabel.setText("Enter Initial Balance: ");
        emaiLabel.setText("Enter Email: ");

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("M", "F", "Other");

        // Handle ComboBox selection changes
        genderComboBox.setOnAction(e -> {
            gender = genderComboBox.getValue();
        });

        // call addcustomer method
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

        clearButton.setOnAction(e -> {
            idField.setText("");
            nameTextField.setText("");
            passwordTextField.setText("");
            addresLabel.setText("");
            balanceTextField.setText("");
            emailField.setText("");
        });

        // set input fild
        idBox.getChildren().addAll(idLabel, idField);
        nameBox.getChildren().addAll(nameLabel, nameTextField);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        addresBox.getChildren().addAll(addresLabel, addresField);
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        emailBox.getChildren().addAll(emaiLabel, emailField);
        sexBox.getChildren().addAll(genderComboBox);
        sexBox.setPadding(new Insets(15));
        sexBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(idBox, nameBox, passwordBox, addresBox, balanceBox, sexBox, emailBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: PURPLE;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }

    static String role;

    public void writeEmployee() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Employee");
        window.setMinHeight(400);
        window.setMinWidth(400);

        // input fild
        idLabel.setText("Enter ID: ");
        nameLabel.setText("Enter Name: ");
        passwordLabel.setText("Enter Password: ");
        addresLabel.setText("Enter Address: ");
        emaiLabel.setText("Enter Email: ");

        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("WORKER", "MANAGER", "ADMINISTRATOR");

        // Handle ComboBox selection changes
        genderComboBox.setOnAction(e -> {
            gender = genderComboBox.getValue();
        });

        roleComboBox.setOnAction(e -> {
            role = genderComboBox.getValue();
        });

        // call addcustomer method
        sumiButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameTextField.getText();
            String password = passwordTextField.getText();
            String address = addresField.getText();
            String email = emailField.getText();

            employee.addEmployee(id, name, password, address, role, gender, email);
            window.close();
        });

        clearButton.setOnAction(e -> {
            idField.setText("");
            nameTextField.setText("");
            passwordTextField.setText("");
            addresLabel.setText("");
            emailField.setText("");
        });

        // set input fild
        idBox.getChildren().addAll(idLabel, idField);
        nameBox.getChildren().addAll(nameLabel, nameTextField);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        addresBox.getChildren().addAll(addresLabel, addresField);
        balanceBox.getChildren().addAll(roleComboBox);
        emailBox.getChildren().addAll(emaiLabel, emailField);
        sexBox.getChildren().addAll(genderComboBox);
        sexBox.setPadding(new Insets(15));
        sexBox.setAlignment(Pos.CENTER);

        // box conteiner
        vBox.getChildren().addAll(idBox, nameBox, passwordBox, addresBox, balanceBox, sexBox, emailBox);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setStyle("-fx-background-color: PURPLE;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: PINK;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
