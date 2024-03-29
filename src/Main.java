
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
    Button userMenu = new Button("_Customer");
    Button workerButton = new Button("_Worker");
    Button adminButton = new Button("_Admin");
    Button aboutMenu = new Button("About _Us");
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

    // Class
    Login login = new Login();
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();
    Alertbox alertbox = new Alertbox();
    InputTransaction inputTransaction = new InputTransaction();
    AcceptNewData acceptNewData = new AcceptNewData();

    String title;

    public void start(Stage primaryStage) {
        // Actionlesiner

        userMenu.setOnAction(e -> {
            int identifire = -1;
            identifire = login.loginForm("Account Number: ", "User");
            if (identifire != -1) {
                userActiin("Customer", identifire);
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
            // founder name
            String message = "\n***********************************************\n" +
                    "\tMembers Name\t\tID Number\n" +
                    "1. Temesgen Abebayehu\tETS 1534/14\n" +
                    "2. Tesfamicael Almaw\t\tETS 1544/14\n" +
                    "3. Tewuhbo Mihret\t\tETS 1554/14\n" +
                    "4. Tinsae Daniel\t\t\tETS 1560/14\n" +
                    "5. Tsion Kassahun\t\tETS 1585/14\n" +
                    "6. Tsiyon Gashaw\t\t\tETS 1588/14\n" +

                    "\n\n\tSubmitted to: Mr. Fuad Yimer" +
                    "\n\tSubmission date: Feb 3/2024" +
                    "\n***********************************************\n";
            alertbox.dispalyInfo(message);
        });

        helpMenu.setOnAction(e -> {
            String helpMessage = "********************************\n" +
                    "\tHELP CENTER\n" + "\n________________________________\n" +
                    "\nCall Us 24/7: \t\t911" +
                    "\nEmail Us:\t\tbanksystem@gmail.com" +
                    "\nMain Ofice:\t\tAddis Ababa, Ethiopia" +
                    "\n*********************************";

            alertbox.dispalyInfo(helpMessage);
        });

        // mouse effect
        userMenu.setOnMouseEntered(e -> {
            userMenu.setStyle("-fx-background-color: blue;");
            userMenu.setCursor(Cursor.HAND);
        });

        userMenu.setOnMouseExited(e -> {
            userMenu.setStyle("-fx-background-color: lightgray;");
            userMenu.setCursor(Cursor.DEFAULT);
        });

        workerButton.setOnMouseEntered(e -> {
            workerButton.setStyle("-fx-background-color: blue;");
            workerButton.setCursor(Cursor.HAND);
        });

        workerButton.setOnMouseExited(e -> {
            workerButton.setStyle("-fx-background-color: lightgray;");
            workerButton.setCursor(Cursor.DEFAULT);
        });

        adminButton.setOnMouseEntered(e -> {
            adminButton.setStyle("-fx-background-color: blue;");
            adminButton.setCursor(Cursor.HAND);
        });

        adminButton.setOnMouseExited(e -> {
            adminButton.setStyle("-fx-background-color: lightgray;");
            adminButton.setCursor(Cursor.DEFAULT);
        });

        aboutMenu.setOnMouseEntered(e -> {
            aboutMenu.setStyle("-fx-background-color: blue;");
            aboutMenu.setCursor(Cursor.HAND);
        });

        aboutMenu.setOnMouseExited(e -> {
            aboutMenu.setStyle("-fx-background-color: lightgray;");
            aboutMenu.setCursor(Cursor.DEFAULT);
        });

        helpMenu.setOnMouseEntered(e -> {
            helpMenu.setStyle("-fx-background-color: blue;");
            helpMenu.setCursor(Cursor.HAND);
        });

        helpMenu.setOnMouseExited(e -> {
            helpMenu.setStyle("-fx-background-color: lightgray;");
            helpMenu.setCursor(Cursor.DEFAULT);
        });

        // background image
        Image backgroudImage = new Image("/image/home.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        // top menu bar and all operation perform on it
        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(userMenu, workerButton, adminButton, aboutMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: rgb(100,100,100);");
        menuBar.setSpacing(15);
        menuBar.setPadding(new Insets(10));

        // Wellcome message
        Label wellcome = new Label("\t\tWELLCOME To \nBank Management System");
        wellcome.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        wellcome.setTextFill(Color.GOLD);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(wellcome);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, 900, 750);
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
        window.setResizable(false);

        // main box
        VBox vBox = new VBox();

        // background image
        Image backgroudImage = new Image("/image/photoadmin.jpeg");

        Label choice = new Label("Select From The List");
        choice.setFont(Font.font("Times", FontWeight.BOLD, 20));
        choice.setPadding(new Insets(10, 10, 20, 10));
        choice.setTextFill(Color.GOLD);

        // Exit button
        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e -> window.close());
        exitButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 16));
        exitButton.setStyle("-fx-background-color: lightblue;");
        HBox exitBox = new HBox();
        exitBox.getChildren().addAll(exitButton);
        exitBox.setPadding(new Insets(20, 10, 10, 200));

        if (measege.equals("Customer")) {
            // button list
            button1.setText("Transfer Money");
            button2.setText("View Profile");
            button3.setText("Check Balance");
            button4.setText("Update Profile");
            button5.setText("Delete Account");

            vBox = new VBox();
            vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, exitBox);

            // Action listner
            // Transfer money
            button1.setOnAction(ex -> inputTransaction.inputTransaction("Transfer", id));

            // view Profile
            button2.setOnAction(ex -> {
                String profile = customer.seeCustomerProfile(id);
                alertbox.dispalyInfo(profile);
            });

            // check balance
            button3.setOnAction(ex -> {
                String balanceInfo = customer.checkBalance(id);
                alertbox.dispalyInfo(balanceInfo);
            });

            // update profile
            button4.setOnAction(ex -> updateProfile(measege, id));

            // logout
            button5.setOnAction(ex -> {
                alertbox.alertWarnning("It may delete all your information!!!");
                updateProfile("Delete", id);
            });

            // background image
            backgroudImage = new Image("/image/photouser.jpg");
        }

        else {
            String roles = employee.getRoles(id);

            // admin operation
            if (roles.equals("ADMINISTRATOR") && measege.equals("Admin")) {
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
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6, button7,
                        button8, exitBox);

                // Action listner
                // Search Employee
                button1.setOnAction(ex -> {
                    int Id;
                    String inputLable = "ID Number ";
                    String idInput = acceptNewData.acceptNewdata(inputLable);
                    if (idInput.equals(""))
                        idInput = "0";
                    else {
                        Id = Integer.parseInt(idInput);
                        String profile = employee.seeEmployeeProfile(Id);
                        alertbox.dispalyInfo(profile);
                    }
                });

                // Add worker
                button2.setOnAction(ex -> acceptNewData.addNewdata("Worker"));

                // delete worker
                button3.setOnAction(ex -> {
                    alertbox.alertWarnning("It may delete all information!!!");
                    int Id;
                    String inputLable = "ID Number ";
                    String idInput = acceptNewData.acceptNewdata(inputLable);
                    if (idInput.equals(""))
                        idInput = "0";
                    else {
                        Id = Integer.parseInt(idInput);
                        employee.deleteEmployee(Id);
                    }
                });

                // searchCustomer
                button4.setOnAction(ex -> {
                    int accNo;
                    String inputLable = "Account Number ";
                    inputLable = acceptNewData.acceptNewdata(inputLable);
                    if (inputLable.equals(""))
                        inputLable = "0";
                    else {
                        accNo = Integer.parseInt(inputLable);
                        inputLable = customer.seeCustomerProfile(accNo);
                        alertbox.dispalyInfo(inputLable);
                    }
                });

                // Number of Worker
                button5.setOnAction(ex -> {
                    int numberOfWorkers = employee.getNumerOfEmployee();
                    String displayMessage = "********************************************\n\n" +
                            "CURRENTLY THE BANK HAS _ " + numberOfWorkers + " _ WORKERS\n\n" +
                            "********************************************\n";
                    alertbox.dispalyInfo(displayMessage);
                });

                // number of Customer
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

                // Bank capital
                button8.setOnAction(ex -> {
                    double capital = customer.getBankCapital();
                    String displayCapital = "**************************************\n" +
                            "\nCONGRAGULATION\n" +
                            "\nThe Capital Reachs: " + capital + "  Birr" +
                            "\n************************************\n";
                    alertbox.dispalyInfo(displayCapital);
                });

                // background image
                backgroudImage = new Image("/image/photoadmin.jpeg");
            }

            // worker operation
            else if (measege.equals("Worker") && roles.equals("WORKER")) {
                // button list
                button1.setText("Diposit Customer Money");
                button2.setText("Withdraw Customer Money");
                button3.setText("Create New Account");
                button4.setText("Search Customer");
                button5.setText("Update Profile");
                button6.setText("View Profile");

                vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6, exitBox);

                // Action listner
                // Deposit money
                button1.setOnAction(ex -> inputTransaction.inputTransaction("Deposit", id));

                // withdraw customer money
                button2.setOnAction(ex -> inputTransaction.inputTransaction("Withdraw", id));

                // Add customer
                button3.setOnAction(ex -> acceptNewData.addNewdata("Customer"));

                // searchCustomer
                button4.setOnAction(ex -> {
                    int accNo;
                    String inputLable = "Account Number ";
                    inputLable = acceptNewData.acceptNewdata(inputLable);
                    if (inputLable.equals(""))
                        inputLable = "0";
                    else {
                        accNo = Integer.parseInt(inputLable);
                        inputLable = customer.seeCustomerProfile(accNo);
                        alertbox.dispalyInfo(inputLable);
                    }

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

            }

            // unauthorized access
            else {
                if (measege.equals("Admin"))
                    alertbox.alertWarnning("Your data not found in ADMINISTRATOR list!!!");
                else
                    alertbox.alertWarnning("Your data not found in WORKER list!!!");
                window.close();
                return;
            }

        }

        // background control
        BackgroundImage background = new BackgroundImage(
                backgroudImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(450, 450, false, false, false, false));

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
        window.setMinHeight(400);
        window.setMinWidth(400);

        // reset choice string
        updateChoice = "";

        Label clue = new Label();
        clue.setFont(Font.font("Times", FontWeight.BLACK, 17));
        clue.setTextFill(Color.MEDIUMVIOLETRED);

        Label newLabel = new Label("Enter New Data: ");
        TextField newField = new TextField();
        Button submitButton = new Button("Submit");
        ComboBox<String> updateComboBox = new ComboBox<>();

        submitButton.setStyle("-fx-background-color: blue;");
        submitButton.setTextFill(Color.WHITE);

        // background imagi
        Image backImage = new Image("/image/updateback.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                backImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(400, 160, false, false, false, false));

        HBox choiceBox = new HBox();
        choiceBox.getChildren().addAll(clue);
        choiceBox.setBackground(new Background(backgroundImage));
        choiceBox.setPadding(new Insets(60, 0, 60, 0));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(newLabel, newField);
        hBox.setPadding(new Insets(15, 40, 15, 40));

        VBox vBox = new VBox();

        // confirm to delete customer info
        if (message.equals("Delete")) {
            clue.setText("This will be remove all your information");

            updateComboBox.setPromptText("Do you want to delete your account?");
            updateComboBox.getItems().addAll("YES", "NO");
            updateComboBox.setOnAction(e -> {
                updateChoice = updateComboBox.getValue();
            });
            submitButton.setOnAction(e -> {
                if (updateChoice.equals("YES")) {
                    customer.logoutCustomer(id);
                }
                window.close();
            });

            // delete box
            vBox.getChildren().addAll(choiceBox, updateComboBox, submitButton);
        }

        // this perforn update operation
        else {
            clue.setText("This Will Change Your Information!!! ");

            updateComboBox.setPromptText("Which one you want to update?");
            updateComboBox.getItems().addAll("Name", "Sex", "Password", "Email");
            updateComboBox.setOnAction(e -> {
                updateChoice = updateComboBox.getValue();
            });

            submitButton.setOnAction(e -> {
                String newdata = newField.getText();
                if (message.equals("Customer")) {
                    customer.editCustomerProfile(id, newdata, updateChoice);
                } else {
                    employee.editEmployeeProfile(id, newdata, updateChoice);
                }
                window.close();
            });

            // update box
            vBox.getChildren().addAll(choiceBox, updateComboBox, hBox, submitButton);
        }

        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: lightblue;");
        vBox.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}