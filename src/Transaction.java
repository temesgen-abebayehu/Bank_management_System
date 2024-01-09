import java.sql.*;

public class Transaction {
    // Database connection parameters
    String url = "jdbc:mysql://localhost:4306/bank_system";
    String username = "root";
    String password = "root";

    // JDBC objects
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    //class
    Alertbox alertbox = new Alertbox();

    public void dipositMoney(int accNo, double bala) {

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM customer");

            // check the account number and password
            int found = 0;
            while (resultSet.next()) {

                if (accNo == resultSet.getInt("account_no")) {
                    String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        bala += resultSet.getDouble("balance");
                        preparedStatement.setDouble(1, bala);
                        preparedStatement.setInt(2, accNo);

                        found++;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertWarnning(alert);
            }

            else {
                String alert = "Diposit Money successfully.";
                alertbox.alertConfirm(alert);
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    public void withdrawMoney(int accNo, double bala, String passcode) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM customer");

            // check the account number and password
            int found = 0;
            while (resultSet.next()) {

                if (accNo == resultSet.getInt("account_no") && passcode == resultSet.getString("password")) {
                    if (resultSet.getDouble("balance") > bala) {
                        String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            bala = resultSet.getDouble("balance") - bala;
                            preparedStatement.setDouble(1, bala);
                            preparedStatement.setInt(2, accNo);

                            found++;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String alert = "Your balance is low";
                        alertbox.alertWarnning(alert);
                        return;
                    }
                }
            }
            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }

            else {
                String alert = "Withdraw Money successfully.";
                alertbox.alertConfirm(alert);
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    public void transferMoney(int accNo, double bala, String passcode, int reciverNO) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM customer");

            // check the account number and password
            int found = 0;
            boolean getAccount = false;
            while (resultSet.next()) {

                if (accNo == resultSet.getInt("account_no") && passcode == resultSet.getString("password")) {
                    if (resultSet.getDouble("balance") > bala) {
                        while (resultSet.next()) {
                            if (reciverNO == resultSet.getDouble("account_no")) {
                                getAccount = true;
                                break;
                            }
                        }
                        if (getAccount == true) {
                            String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                double senderBala = resultSet.getDouble("balance") - bala;
                                preparedStatement.setDouble(1, senderBala);
                                preparedStatement.setInt(2, accNo);

                                found++;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        else {
                            String alert = "Reciver Account number not found";
                            alertbox.alertWarnning(alert);
                            return;
                        }
                    } else {
                        String alert = "Your balance is low";
                        alertbox.alertWarnning(alert);
                        return;
                    }
                }
            }
            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }

            else {
                while (resultSet.next()) {
                    String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                double recinerBal  = resultSet.getDouble("balance") + bala;
                                preparedStatement.setDouble(1, recinerBal);
                                preparedStatement.setInt(2, reciverNO);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                }
                String alert = "Transfer successfully";
                alertbox.alertConfirm(alert);
            }

        } catch (Exception e) {
            e.toString();
        }
    }
}