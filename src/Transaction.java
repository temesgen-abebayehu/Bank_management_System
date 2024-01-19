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

    // class
    Alertbox alertbox = new Alertbox();

    public void dipositMoney(int accNo, double bala) {

        try {
            establishConnection();

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
                        preparedStatement.executeUpdate();

                        found++;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (found == 0) {
                alertbox.alertError("Account Number not found");
            }

            else {
                alertbox.alertConfirm("Diposit Money successfully.");
            }

        } catch (Exception e) {
            e.toString();
        } finally {
            closeResources();
        }
    }

    public void withdrawMoney(int accNo,int ID, double bala) {
        try {
            establishConnection();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM customer");

            // check the account number and password
            int found = 0;
            while (resultSet.next()) {

                if (accNo == resultSet.getInt("account_no")&&ID==resultSet.getInt("id")) {
                    if (resultSet.getDouble("balance") > bala) {
                        String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            bala = resultSet.getDouble("balance") - bala;
                            preparedStatement.setDouble(1, bala);
                            preparedStatement.setInt(2, accNo);
                            preparedStatement.executeUpdate();

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
                alertbox.alertError( "Account Number not found");
            }

            else {
                alertbox.alertConfirm("Withdraw Money successfully.");
            }

        } catch (Exception e) {
            e.toString();
        } finally {
            closeResources();
        }
    }

    public void transferMoney(int senderAccountNo, double amount, int receiverAccountNo) {
        try {
            establishConnection();

            // Check sender's balance and ID
            double senderBalance = 0;
            int senderID = -1;
            resultSet = statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {
                if (senderAccountNo == resultSet.getInt("account_no")) {
                    senderID = resultSet.getInt("id");
                    senderBalance = resultSet.getDouble("balance");
                    break;
                }
            }

            if (senderID == -1 || senderBalance < amount) {
                String alert = (senderID == -1) ? "Sender Account Number not found" : "Insufficient balance";
                alertbox.alertWarnning(alert);
                return;
            }

            // Check if receiver exists
            resultSet = statement.executeQuery("SELECT * FROM customer");
            int receiverID = -1;
            double receiverBalance = 0;

            while (resultSet.next()) {
                if (receiverAccountNo == resultSet.getInt("account_no")) {
                    receiverID = resultSet.getInt("id");
                    receiverBalance = resultSet.getDouble("balance");
                    break;
                }
            }

            if (receiverID == -1) {
                alertbox.alertError("Receiver Account Number not found");
                return;
            }

            // Update sender's balance
            updateBalance(senderBalance - amount, senderAccountNo);

            // Update receiver's balance
            updateBalance( receiverBalance + amount, receiverAccountNo);

            alertbox.alertConfirm("Transfer successful");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void updateBalance( double newBalance, int accountNo) throws SQLException {
        String updateQuery = "UPDATE customer SET balance=? WHERE account_no=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setInt(2, accountNo);
            preparedStatement.executeUpdate();
        }
    }

    private void establishConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    private void closeResources() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}