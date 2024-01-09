import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // mene list
    Menu userMenu = new Menu("_User");
    Menu employeeMenu = new Menu("_Employee");
    Menu aboutMenu = new Menu("_About Us");
    Menu helpMenu = new Menu("_Help");

    MenuItem userItem = new MenuItem("user");
    MenuItem employItem = new MenuItem("Employee");
    MenuItem aboutItem = new MenuItem("About Us");
    MenuItem helpItem = new MenuItem("Help");
    

    public void start(Stage primaryStage) {

        // Actionlesiner
        UserLisner lisner = new UserLisner();
        userMenu.setOnAction(lisner);
        employeeMenu.setOnAction(lisner);
        aboutMenu.setOnAction(lisner);
        helpMenu.setOnAction(lisner);
        
        //Create a menu item
        userMenu.getItems().add(userItem);
        employeeMenu.getItems().add(employItem);
        aboutMenu.getItems().add(aboutItem);
        helpMenu.getItems().add(helpItem);

        // top menu bar and all operation perform on it
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(userMenu, employeeMenu, aboutMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: lightblue;");

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

    class UserLisner implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button button1 = new Button();
            Button button2 = new Button();
            Button button3 = new Button();
            Button button4 = new Button();
            Button button5 = new Button();
            Button button6 = new Button();
            Label choice = new Label();

            //Class
            Login login = new Login();
            Transaction transaction = new Transaction();
            Customer customer = new Customer();
            Employee employee = new Employee();

            String title;

            if (e.getSource() == userItem) {
                Stage window = new Stage();
                title = "Customer page";

                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle(title);

                choice.setText("Select From The List");
                choice.setFont(Font.font("Times", FontWeight.BLACK, 20));

            
                //button list
                button1.setText("Withdraw Money");
                button2.setText("Transfer Money");
                button3.setText("View Profile");
                button4.setText("Check Balance");
                button5.setText("Update Profile");
                button6.setText("Logout");

                //Action listner
                //Withdraw money
                button1.setOnAction(ex -> {
                    login.textwithdraw();
                    window.close();
                });

                //Transfer money
                button2.setOnAction(ex -> {
                    login.textTransfer();
                    window.close();
                });

                //view Profile
                button3.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    customer.seeCustomerProfile(accNo);
                });

                //check balance
                button4.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    customer.checkBalance(accNo);
                });

                //update profile
                button5.setOnAction(ex -> {
                    int accNo;
                    accNo = login.customerLogin();
                    String choiceButtone = login.updateButtone();
                    customer.editCustomerProfile(accNo, choiceButtone);
                });

                //logout
                button6.setOnAction(ex-> {
                    int accNo;
                    accNo = login.customerLogin();
                    customer.logoutCustomer(accNo);
                });

                VBox vBox = new VBox();
                vBox.getChildren().addAll(button1, button2, button3,button4,button5,button6);
                vBox.setAlignment(Pos.CENTER);
                vBox.setStyle("-fx-background-color: lightgrey;");

                BorderPane layout = new BorderPane();
                layout.setTop(vBox);

                Scene scene = new Scene(layout,450,450);
                window.setScene(scene);
                window.showAndWait();
            }
        }
    }
}
