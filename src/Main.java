
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    Button employeeMenu = new Button("_Employee");
    Button aboutMenu = new Button("_About Us");
    Button helpMenu = new Button("_Help");

    public void start(Stage primaryStage) {

        // Actionlesiner
        UserLisner lisner = new UserLisner();
        userMenu.setOnAction(lisner);
        employeeMenu.setOnAction(lisner);
        aboutMenu.setOnAction(lisner);
        helpMenu.setOnAction(lisner);

        // top menu bar and all operation perform on it
        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(userMenu, employeeMenu, aboutMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: lightblue;");
        menuBar.setSpacing(15);

        // Wellcome message
        Label wellcome = new Label("\t\tWELLCOME To \nBank Management System");
        wellcome.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        wellcome.setTextFill(Color.BLUE);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(wellcome);
        root.setStyle("-fx-background-color: lightgreen;");

        Scene scene = new Scene(root, 700, 650);
        primaryStage.setTitle("Bank System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // control fild
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();
    Button button6 = new Button();
    Label choice = new Label();

    // Class
    Login login = new Login();
    Transaction transaction = new Transaction();
    Customer customer = new Customer();
    Employee employee = new Employee();
    Alertbox alertbox = new Alertbox();

    String title;

    class UserLisner implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {

            if (e.getSource() == userMenu) {
                Stage window = new Stage();
                title = "Customer page";
                window.setTitle(title);
                window.setMinHeight(400);
                window.setMinWidth(400);

                choice.setText("Select From The List");
                choice.setFont(Font.font("Times", FontWeight.BLACK, 20));
                choice.setPadding(new Insets(15, 10, 20, 10));

                // button list
                button1.setText("Withdraw Money");
                button2.setText("Transfer Money");
                button3.setText("View Profile");
                button4.setText("Check Balance");
                button5.setText("Update Profile");
                button6.setText("Logout");

                // Action listner
                // Withdraw money
                button1.setOnAction(ex -> {
                    login.textwithdraw();
                    window.close();
                });

                // Transfer money
                button2.setOnAction(ex -> {
                    login.textTransfer();
                    window.close();
                });

                // view Profile
                button3.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    String profile = customer.seeCustomerProfile(accNo);
                    alertbox.viewProfile(profile);
                    window.close();
                });

                // check balance
                button4.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    customer.checkBalance(accNo);
                    window.close();
                });

                // update profile
                button5.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    String choiceButtone = login.updateButtone();
                    String choice = " New " + choiceButtone;
                    String newData = login.acceptNewdata(choice);
                    customer.editCustomerProfile(accNo, newData, choiceButtone);
                    window.close();
                });

                // logout
                button6.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    customer.logoutCustomer(accNo);
                    window.close();
                });

                VBox vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6);
                vBox.setAlignment(Pos.CENTER_LEFT);
                vBox.setSpacing(10);

                BorderPane layout = new BorderPane();
                layout.setTop(vBox);

                Scene scene = new Scene(layout);
                window.setScene(scene);
                window.showAndWait();
            }

            // EMPLOYEE LISTNER
            else if (e.getSource() == employeeMenu) {
                Stage window = new Stage();
                title = "Employee page";
                window.setTitle(title);
                window.setMinHeight(400);
                window.setMinWidth(400);

                choice.setText("Select From The List");
                choice.setFont(Font.font("Times", FontWeight.BLACK, 20));
                choice.setPadding(new Insets(15, 10, 20, 10));

                // button list
                button1.setText("WORKER");
                button2.setText("MANAGER");
                button3.setText("ADMINISTRATER");

                // Action listner
                EmployeeLisner employeeLisner = new EmployeeLisner();

                button1.setOnAction(employeeLisner);
                button2.setOnAction(employeeLisner);
                button3.setOnAction(employeeLisner);

                VBox vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3);
                vBox.setAlignment(Pos.CENTER_LEFT);
                vBox.setSpacing(10);

                BorderPane layout = new BorderPane();
                layout.setTop(vBox);

                Scene scene = new Scene(layout);
                window.setScene(scene);
                window.showAndWait();
            }
        }
    }

    class EmployeeLisner implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (e.getSource() == button1) {
                Stage window = new Stage();
                title = "Worker Page";
                window.setTitle(title);
                window.setMinHeight(400);
                window.setMinWidth(400);

                choice.setText("Select From The List");
                choice.setFont(Font.font("Times", FontWeight.BLACK, 20));
                choice.setPadding(new Insets(15, 10, 20, 10));

                // button list
                button1.setText("Diposit Customer Money");
                button2.setText("Add Customer");
                button3.setText("Delete Customer");
                button4.setText("Search Customer");
                button5.setText("Update Profile");
                button6.setText("View Profile");

                // Action listner
                // Withdraw money
                button1.setOnAction(ex -> {
                    login.textDeposit();
                    window.close();
                });

                //Add customer
                button2.setOnAction(ex -> {
                    login.writeCustomer();
                    window.close();
                });

                // delete customer
                button3.setOnAction(ex -> {
                    int accNo;
                    String inputLable = "Account Number ";
                    inputLable = login.acceptNewdata(inputLable);
                    accNo = Integer.parseInt(inputLable);
                    customer.logoutCustomer(accNo);
                    window.close();
                });

                // searchCustomer
                button4.setOnAction(ex -> {
                    int accNo;
                    String inputLable = "Account Number ";
                    inputLable = login.acceptNewdata(inputLable);
                    accNo = Integer.parseInt(inputLable);
                    inputLable = customer.seeCustomerProfile(accNo);
                    alertbox.viewProfile(inputLable);
                    window.close();
                });

                // update profile
                button5.setOnAction(ex -> {
                    int id;
                    String inputLable = "ID Number ";
                    String idInput = login.acceptNewdata(inputLable);
                    id = Integer.parseInt(idInput); //change string to int 
                    //display update choice
                    String choiceButtone = login.updateButtone();

                    //accept new data
                    inputLable = "New " + choiceButtone;
                    String newData = login.acceptNewdata(inputLable);
                    employee.editEmployeeProfile(id, newData, choiceButtone);
                    window.close();
                });

                // view profile
                button6.setOnAction(ex -> {
                    int id;
                    String inputLable = "ID Number ";
                    String idInput = login.acceptNewdata(inputLable);
                    id = Integer.parseInt(idInput);
                    String profile = employee.seeEmployeeProfile(id);
                    alertbox.viewProfile(profile);
                    window.close();
                });

                VBox vBox = new VBox();
                vBox.getChildren().addAll(choice, button1, button2, button3, button4, button5, button6);
                vBox.setAlignment(Pos.CENTER_LEFT);
                vBox.setSpacing(10);

                BorderPane layout = new BorderPane();
                layout.setTop(vBox);

                Scene scene = new Scene(layout);
                window.setScene(scene);
                window.showAndWait();
            }
        }
    }
}
