
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // mene list
    Button userMenu = new Button("_User");
    Button workerButton = new Button("_Worker");
    Button adminButton = new Button("_Admin");
    Button aboutMenu = new Button("_About Us");
    Button helpMenu = new Button("_Help");

    // control fild
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();
    Button button6 = new Button();
    Button button7 = new Button();
    Label choice = new Label();

    // Class
    Login login = new Login();
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();
    Alertbox alertbox = new Alertbox();

    String title;
    static int identifire;

    public void start(Stage primaryStage) {
        // Actionlesiner

        userMenu.setOnAction(e -> {
            identifire = -1;
            identifire = login.loginForm("Enter Account Number: ", "User");
            if (identifire != -1) {
                userActiin("User", identifire);
            }
        });

        workerButton.setOnAction(e -> {
            identifire = -1;
            identifire = login.loginForm("Enter ID Number: ", "Worker");

            if (identifire != -1) {
                userActiin("Worker", identifire);
            }
        });

        adminButton.setOnAction(e -> {
            identifire = -1;
            identifire = login.loginForm("Enter ID Number: ", "Admin");

            if (identifire != -1) {
                userActiin("Admin", identifire);
            }
        });

        aboutMenu.setOnAction(e -> {
            alertbox.alertAbout();
        });

        helpMenu.setOnAction(e -> {
            String helpMessage = "********************************\n" +
                    "\tHELP CENTER\n" + "\n________________________________" +
                    "\nCall Us 24/7: \t911" +
                    "\nEmail Us:\tbanksystem@gmail.com" +
                    "\nMain Ofice:\tAddis Ababa, Ethiopia" +
                    "\n*********************************";

            alertbox.viewProfile(helpMessage);
        });

        // top menu bar and all operation perform on it
        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(userMenu, workerButton, adminButton, aboutMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: rgb(100,100,100);");
        menuBar.setSpacing(15);
        menuBar.setPadding(new Insets(10));

        // Wellcome message
        Label wellcome = new Label("\t\tWELLCOME To \nBank Management System");
        wellcome.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        wellcome.setTextFill(Color.BLUE);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(wellcome);
        root.setStyle("-fx-background-color: BROWN;");

        Scene scene = new Scene(root, 700, 650);
        primaryStage.setTitle("Bank System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static String updateChoice = "";

    public void userActiin(String measege, int id) {

        Stage window = new Stage();
        title = measege + " Page";
        window.setTitle(title);
        window.setMinHeight(400);
        window.setMinWidth(350);

        VBox vBox = new VBox();

        Label choice = new Label("Select From The List");
        choice.setFont(Font.font("Times", FontWeight.BLACK, 20));
        choice.setPadding(new Insets(15, 10, 20, 10));
        choice.setTextFill(Color.GOLD);

        if (measege.equals("User")) {
            // button list
            button1.setText("Withdraw Money");
            button2.setText("Transfer Money");
            button3.setText("View Profile");
            button4.setText("Check Balance");
            button5.setText("Update Profile");
            button6.setText("Logout");

            vBox = new VBox();
            vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6);

            // Action listner
            // Withdraw money
            button1.setOnAction(ex -> {
                login.inputTransaction("Withdraw", id);
                // window.close();
            });

            // Transfer money
            button2.setOnAction(ex -> {
                login.inputTransaction("Transfer", id);
                // window.close();
            });

            // view Profile
            button3.setOnAction(ex -> {
                String profile = customer.seeCustomerProfile(id);
                alertbox.viewProfile(profile);
                // window.close();
            });

            // check balance
            button4.setOnAction(ex -> {
                String balanceInfo = customer.checkBalance(id);
                alertbox.viewProfile(balanceInfo);
                // window.close();
            });

            // update profile
            button5.setOnAction(ex -> {
                updateProfile(measege, id);
                // window.close();
            });

            // logout
            button6.setOnAction(ex -> {
                alertbox.alertWarnning("It may delete all your information!!!");
                customer.logoutCustomer(id);
                // window.close();
            });
        }

        else if (measege.equals("Worker")) {
            // button list
            button1.setText("Diposit Customer Money");
            button2.setText("Add Customer");
            button3.setText("Delete Customer");
            button4.setText("Search Customer");
            button5.setText("Update Profile");
            button6.setText("View Profile");

            vBox = new VBox();
            vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6);

            // Action listner
            // Deposit money
            button1.setOnAction(ex -> {
                login.inputTransaction("Deposit", id);
                // window.close();
            });

            // Add customer
            button2.setOnAction(ex -> {
                login.addNewdata("Customer");
                // window.close();
            });

            // delete customer
            button3.setOnAction(ex -> {
                int accNo;
                String inputLable = "Account Number ";
                String accountString = login.acceptNewdata(inputLable);
                if (accountString.equals("")) {
                    accountString = "0";
                }
                accNo = Integer.parseInt(accountString);
                customer.logoutCustomer(accNo);
                // window.close();
            });

            // searchCustomer
            button4.setOnAction(ex -> {
                int accNo;
                String inputLable = "Account Number ";
                inputLable = login.acceptNewdata(inputLable);
                if (inputLable.equals(""))
                    inputLable = "0";
                accNo = Integer.parseInt(inputLable);
                inputLable = customer.seeCustomerProfile(accNo);
                alertbox.viewProfile(inputLable);
                // window.close();
            });

            // update profile
            button5.setOnAction(ex -> {
                updateProfile(measege, id);
                // window.close();
            });

            // view profile
            button6.setOnAction(ex -> {
                String profile = employee.seeEmployeeProfile(id);
                alertbox.viewProfile(profile);
                // window.close();
            });
        }

        else if (measege.equals("Admin")) {
            String roles = employee.getRoles(id);
            if (roles.equals("ADMINISTRATOR")) {
                // button list
                button1.setText("Search Worker");
                button2.setText("Add Worker");
                button3.setText("Delete Worker");
                button4.setText("Search Customer");
                button5.setText("Update Profile");
                button6.setText("View Profile");
                button7.setText("See Bank Capital");

                vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6, button7);

                // Action listner
                // Search Employee
                button1.setOnAction(ex -> {
                    int Id;
                    String inputLable = "ID Number ";
                    String idInput = login.acceptNewdata(inputLable);
                    if (idInput.equals(""))
                        idInput = "0";
                    Id = Integer.parseInt(idInput);
                    String profile = employee.seeEmployeeProfile(Id);
                    alertbox.viewProfile(profile);
                    // window.close();
                });

                // Add worker
                button2.setOnAction(ex -> {
                    login.addNewdata("Worker");
                    // window.close();
                });

                // delete worker
                button3.setOnAction(ex -> {
                    int Id;
                    String inputLable = "ID Number ";
                    String idInput = login.acceptNewdata(inputLable);
                    if (idInput.equals(""))
                        idInput = "0";
                    Id = Integer.parseInt(idInput);
                    employee.deleteEmployee(Id);
                    // window.close();
                });

                // searchCustomer
                button4.setOnAction(ex -> {
                    int accNo;
                    String inputLable = "Account Number ";
                    inputLable = login.acceptNewdata(inputLable);
                    if (inputLable.equals(""))
                        inputLable = "0";
                    accNo = Integer.parseInt(inputLable);
                    inputLable = customer.seeCustomerProfile(accNo);
                    alertbox.viewProfile(inputLable);
                    // window.close();
                });

                // update profile
                button5.setOnAction(ex -> {
                    updateProfile(measege, id);
                    // window.close();
                });

                // view profile
                button6.setOnAction(ex -> {
                    String profile = employee.seeEmployeeProfile(id);
                    alertbox.viewProfile(profile);
                    // window.close();
                });

                button7.setOnAction(ex -> {
                    double capital = customer.capital();
                    String displayCapital = "**************************************\n" +
                            "\nCONGRAGULATION\n" +
                            "\nThe Capital Reachs: " + capital + "  Birr" +
                            "\n************************************\n";
                    alertbox.viewProfile(displayCapital);
                    // window.close();
                });
            }

            else if (roles.equals("WORKER")) {
                alertbox.alertWarnning("You data not found in ADMINISTRATOR list");
                window.close();
            }
        }

        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0, 10, 10, 100));
        vBox.setStyle("-fx-background-color: rgb(100,100,100);");

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }

    public void updateProfile(String message, int id) {
        Stage window = new Stage();
        window.setTitle("Update Profile");
        window.setMinHeight(400);
        window.setMinWidth(350);

        Label choice = new Label("This Will Change Your Information!!! ");
        choice.setFont(Font.font("Times", FontWeight.BLACK, 17));
        choice.setTextFill(Color.MEDIUMVIOLETRED);

        Label newLabel = new Label("Enter New Data: ");
        TextField newField = new TextField();
        Button submitButton = new Button("Submit");
        ComboBox<String> updateComboBox = new ComboBox<>();
        updateComboBox.setPromptText("Which one you want to update?");
        updateComboBox.getItems().addAll("Name", "Sex", "Password", "Email");
        updateComboBox.setOnAction(e -> {
            updateChoice = updateComboBox.getValue();
        });

        submitButton.setOnAction(e -> {
            String newdata = newField.getText();
            if (message.equals("User")) {
                customer.editCustomerProfile(id, newdata, updateChoice);
            } else {
                employee.editEmployeeProfile(id, newdata, updateChoice);
            }
            window.close();
        });

        submitButton.setStyle("-fx-background-color: blue;");
        submitButton.setTextFill(Color.WHITE);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(choice, updateComboBox, newLabel, newField, submitButton);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-background-color: lightblue;");

        BorderPane layout = new BorderPane();
        layout.setCenter(vBox);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}