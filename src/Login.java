
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    // control fild
    Button sumiButton = new Button();
    Button clearButton = new Button();
    Label accountLabel = new Label();
    TextField accounTextField = new TextField();
    Label nameLabel = new Label();
    TextField nameTextField = new TextField();
    Label balanceLabel = new Label();
    TextField balanceTextField = new TextField();
    Label passwordLabel = new Label();
    TextField passwordTextField = new TextField();
    Label recivLabel = new Label();
    TextField recivField = new TextField();

    //class
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();

    public void textwithdraw() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

        accountLabel.setText("Enter Account Number: ");
        balanceLabel.setText("Enter Amoun:  ");
        passwordLabel.setText("Enter Your Password: ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        balanceBox.getChildren().addAll(balanceLabel, balanceTextField);
        balanceBox.setSpacing(10);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setSpacing(10);

        // buttun
        sumiButton.setText("Summit");
        sumiButton.setPadding(new Insets(15, 15, 15, 15));

        clearButton.setText("Clear");
        clearButton.setPadding(new Insets(15, 15, 15, 15));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, balanceBox, passwordBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: grey;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());
            String passcode = passwordTextField.getText();

            //call withdraw method
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
        layout.setStyle("-fx-background-color: lightbrown;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void textTransfer() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

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
        vBox.setStyle("-fx-background-color: grey;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());
            String passcode = passwordTextField.getText();
            int reciverNO = Integer.parseInt(recivField.getText());

            //call withdraw method
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
        layout.setStyle("-fx-background-color: lightbrown;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, 450);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public void textDeposit() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

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
        vBox.setStyle("-fx-background-color: grey;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        sumiButton.setOnAction(e -> {
            int accNo = Integer.parseInt(accounTextField.getText());
            double bala = Double.parseDouble(balanceTextField.getText());

            //call withdraw method
            transaction.dipositMoney(accNo, bala);
            window.close();
        });

        clearButton.setOnAction(e -> {
            accounTextField.setText("");
            balanceTextField.setText("");
        });

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: lightbrown;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, 450);
        window.setScene(scene);
        window.showAndWait();
    }
    
    static int account_no = 0;
    public int customerLogin() {
        Stage window = new Stage();
        

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

        accountLabel.setText("Enter Account Number: ");
        passwordLabel.setText("Enter Password:  ");

        // set box
        acountBox.getChildren().addAll(accountLabel, accounTextField);
        acountBox.setSpacing(10);
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setSpacing(10);

        // buttun
        sumiButton.setText("Summit");
        sumiButton.setPadding(new Insets(15, 15, 15, 15));

        clearButton.setText("Clear");
        clearButton.setPadding(new Insets(15, 15, 15, 15));

        buttonBox.getChildren().addAll(sumiButton, clearButton);

        // box conteiner
        vBox.getChildren().addAll(acountBox, passwordBox, buttonBox);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: grey;");
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
        layout.setStyle("-fx-background-color: lightbrown;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

        return account_no;
    }

    static String newData;
    public String updateButtone() {
        Stage window = new Stage();
        

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Login");

        // buttun
        Button nameButton = new Button("Update Name");
        Button passwordButton = new Button("Update Password");
        Button sexButton = new Button("Update Sex");
        Button emailButton = new Button("Update Email");
        sumiButton.setText("OK");

        nameButton.setOnAction(e -> {
            newData = "Name";
        });

        passwordButton.setOnAction(e -> {
            newData = "Password";
        });

        sexButton.setOnAction(e -> {
            newData = "Sex";
        });

        emailButton.setOnAction(e -> {
            newData = "Email";
        });

        //submmit button
        sumiButton.setOnAction(e -> {
            window.close();
        });

        // box conteiner
        vBox.getChildren().addAll(nameButton, passwordButton, sexButton,emailButton,sumiButton);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-background-color: grey;");
        vBox.setPadding(new Insets(10, 10, 10, 10));

        
        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);
        layout.setStyle("-fx-background-color: lightbrown;");
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.showAndWait();

        return newData;
    }
}
