import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AcceptNewData {
    // class    
    Customer customer = new Customer();
    Employee employee = new Employee();

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
