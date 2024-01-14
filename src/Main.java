
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    Button button8 = new Button();
    Label choice = new Label();

    // Class
    Login login = new Login();
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();
    Alertbox alertbox = new Alertbox();

    String title;

    public void start(Stage primaryStage) {
        // Actionlesiner

        userMenu.setOnAction(e -> {
            int identifire = -1;
            identifire = login.loginForm("Account Number: ", "User");
            if (identifire != -1) {
                userActiin("User", identifire);
            }
        });

        workerButton.setOnAction(e -> {
            int identifire = -1;
            identifire = login.loginForm("ID Number: ", "Worker");

            if (identifire != -1) {
                userActiin("Worker", identifire);
            }
        });

        adminButton.setOnAction(e -> {
            int identifire = -1;
            identifire = login.loginForm("ID Number: ", "Admin");

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

            alertbox.dispalyInfo(helpMessage);
        });

        // background image
        Image backgroudImage = new Image("/image/home.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(700, 650, false, false, false, false));

        // top menu bar and all operation perform on it
        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(userMenu, workerButton, adminButton, aboutMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: rgb(100,100,100);");
        menuBar.setSpacing(15);
        menuBar.setPadding(new Insets(10));

        // Wellcome message
        Label wellcome = new Label("\t\tWELLCOME To \nBank Management System");
        wellcome.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        wellcome.setTextFill(Color.GOLD);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(wellcome);
        root.setBackground(new Background(background));

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
        window.setMinHeight(450);
        window.setMinWidth(450);

        VBox vBox = new VBox();
        // background image
        Image backgroudImage = new Image("/image/photoadmin.jpeg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(450, 450 , false, false, false, false));

        Label choice = new Label("Select From The List");
        choice.setFont(Font.font("Times", FontWeight.BOLD, 20));
        choice.setPadding(new Insets(10, 10, 20, 10));
        choice.setTextFill(Color.GOLD);

        //Exit button
        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> window.close());
        exitButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 16));
        exitButton.setStyle("-fx-background-color: lightblue;");
        HBox exitBox = new HBox();
        exitBox.getChildren().addAll(exitButton);
        exitBox.setPadding(new Insets(20,10,10,200));

        if (measege.equals("User")) {
            // button list
            button1.setText("Withdraw Money");
            button2.setText("Transfer Money");
            button3.setText("View Profile");
            button4.setText("Check Balance");
            button5.setText("Update Profile");
            button6.setText("Logout");

            vBox = new VBox();
            vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6,exitBox);

            // Action listner
            // Withdraw money
            button1.setOnAction(ex -> login.inputTransaction("Withdraw", id));

            // Transfer money
            button2.setOnAction(ex -> login.inputTransaction("Transfer", id));

            // view Profile
            button3.setOnAction(ex -> {
                String profile = customer.seeCustomerProfile(id);
                alertbox.dispalyInfo(profile);
            });

            // check balance
            button4.setOnAction(ex -> {
                String balanceInfo = customer.checkBalance(id);
                alertbox.dispalyInfo(balanceInfo);
            });

            // update profile
            button5.setOnAction(ex -> updateProfile(measege, id));

            // logout
            button6.setOnAction(ex -> {
                alertbox.alertWarnning("It may delete all your information!!!");
                customer.logoutCustomer(id);
            });

            // background image
            backgroudImage = new Image("/image/photouser.jpg");
            background = new BackgroundImage(
                    backgroudImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(400, 400, false, false, false, false));
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
            vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6,exitBox);

            // Action listner
            // Deposit money
            button1.setOnAction(ex -> {
                login.inputTransaction("Deposit", id);
                // window.close();
            });

            // Add customer
            button2.setOnAction(ex -> login.addNewdata("Customer"));

            // delete customer
            button3.setOnAction(ex -> {
                alertbox.alertWarnning("It may delete all information!!!");
                int accNo;
                String inputLable = "Account Number ";
                String accountString = login.acceptNewdata(inputLable);
                if (accountString.equals("")) {
                    accountString = "0";
                }
                accNo = Integer.parseInt(accountString);
                customer.logoutCustomer(accNo);
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
                alertbox.dispalyInfo(inputLable);
            });

            // update profile
            button5.setOnAction(ex -> {
                updateProfile(measege, id);
            });

            // view profile
            button6.setOnAction(ex -> {
                String profile = employee.seeEmployeeProfile(id);
                alertbox.dispalyInfo(profile);
            });

            // background image
            backgroudImage = new Image("/image/photoworker.jpg");
            background = new BackgroundImage(
                    backgroudImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(400,400, false, false, false, false));
        }

        else {
            String roles = employee.getRoles(id);
            if (roles.equals("ADMINISTRATOR")) {
                // button list
                button1.setText("Search Worker");
                button2.setText("Add Worker");
                button3.setText("Delete Worker");
                button4.setText("Search Customer");
                button5.setText("No of Worker");
                button6.setText("No of Customer");
                button7.setText("View Profile");
                button8.setText("See Bank Capital");

                vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6, button7,button8,exitBox);

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
                    alertbox.dispalyInfo(profile);
                });

                // Add worker
                button2.setOnAction(ex -> login.addNewdata("Worker"));

                // delete worker
                button3.setOnAction(ex -> {
                    alertbox.alertWarnning("It may delete all information!!!");
                    int Id;
                    String inputLable = "ID Number ";
                    String idInput = login.acceptNewdata(inputLable);
                    if (idInput.equals(""))
                        idInput = "0";
                    Id = Integer.parseInt(idInput);
                    employee.deleteEmployee(Id);
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
                    alertbox.dispalyInfo(inputLable);
                });

                // Number of Worker
                 button5.setOnAction(ex -> {
                    int numberOfWorkers = employee.getNumerOfEmployee();
                    String displayMessage = "********************************************\n\n" +
                            "CURRENTLY THE BANK HAS _ " + numberOfWorkers + " _ WORKERS\n\n" +
                                            "********************************************\n";
                    alertbox.dispalyInfo(displayMessage);
                 });
                
                 //number of Customer
                 button6.setOnAction(e -> {
                     int numberOfWorkers = customer.getNoOfCustomer();
                    String displayMessage = "********************************************\n\n" +
                            "CURRENTLY THE BANK HAS _ " + numberOfWorkers + " _ CUSTOMERS\n\n" +
                            "********************************************\n";
                    alertbox.dispalyInfo(displayMessage);
                 });

                // view profile
                button7.setOnAction(ex -> {
                    String profile = employee.seeEmployeeProfile(id);
                    alertbox.dispalyInfo(profile);
                });

                //Bank capital
                button8.setOnAction(ex -> {
                    double capital = customer.getBankCapital();
                    String displayCapital = "**************************************\n" +
                            "\nCONGRAGULATION\n" +
                            "\nThe Capital Reachs: " + capital + "  Birr" +
                            "\n************************************\n";
                    alertbox.dispalyInfo(displayCapital);
                });
                
            }

            else if (roles.equals("WORKER")) {
                vBox = new VBox();
                alertbox.alertWarnning("Your data are not found in ADMINISTRATOR list");
                window.close();
            }
        }

        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0, 10, 10, 100));
        vBox.setBackground(new Background(background));

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }

    public void updateProfile(String message, int id) {
        Stage window = new Stage();
        window.setTitle("Update Profile");
        window.setMinHeight(250);
        window.setMinWidth(400);

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

        HBox hBox = new HBox();
        hBox.getChildren().addAll(newLabel, newField);
        hBox.setPadding(new Insets(15,40,15,40));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(choice, updateComboBox, hBox, submitButton);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: lightblue;");
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}