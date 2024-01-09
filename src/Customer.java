
import java.sql.*;
import java.sql.Date;

public class Customer {

    private String url = "jdbc:mysql://localhost:4306/bank_system";
    private String username = "root";
    private String password = "root";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private Alertbox alertbox = new Alertbox();

    //search customer account number
    public int getAccountNo(int accNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM customer");

            int found = 0;
            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no")) {
                    found++;
                }
            }

            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return accNo;
    }

    public int loginCustomer(int accNo, String passcode) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM customer");

            int found = 0;
            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no") && passcode.equals(resultSet.getString("password"))) {
                    found++;
                }
            }

            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return accNo;
    }

    public String seeCustomerProfile(int accNo) {
        String profile = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM customer");

            int found = 0;
            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no")) {
                    profile = "\n----------------------------------\n" +
                            "\tID: " + resultSet.getInt("id") + "\n\tName: " + resultSet.getString("name") +
                            "\n\tAddress: " + resultSet.getString("address") + "\n\tAvailable Balance: "
                            + resultSet.getDouble("balance") +
                            "\n\tAccount Number: " + resultSet.getInt("account_no") + "\n\tSex: "
                            + resultSet.getString("sex") +
                            "\n\tJoin Date: " + resultSet.getDate("join_date") + "\n\tContact Email: "
                            + resultSet.getString("email") +
                            "\n---------------------------------------\n";
                    found++;
                }
            }

            if (found == 0) {
                String alert = "Account Number not found";
                System.out.println(alert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return profile;
    }

    public void editCustomerProfile(int accNo, String newData, String choice) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no")) {

                    if ("Name".equals(choice) || "Password".equals(choice) || "Sex".equals(choice) || "Email".equals(choice)) {

                        String updateQuery = "UPDATE customer SET " + choice.toLowerCase() + "=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newData);
                            preparedStatement.setInt(2, accNo);

                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                String alert = "Data updated successfully.";
                                alertbox.alertConfirm(alert);
                            } else {
                                String alert = "No data updated. Account Number not found.";
                                alertbox.alertError(alert);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String alert = "Incorrect Choice";
                        alertbox.alertWarnning(alert);
                    }
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public String checkBalance(int accNo) {
        String balance = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM customer");

            int found = 0;
            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no")) {
                    balance = "Available Balance: " + resultSet.getDouble("balance");
                    found++;
                }
            }

            if (found == 0) {
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return balance;
    }

    public void addCustomer(int ID, String NAME, String PASSWORD, String ADDRESS, double BALANCE, String SEX,
                            String EMAIL) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String insertSQL = "INSERT INTO customer (id, name, password, address, balance, sex, join_date, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                preparedStatement.setInt(1, ID);
                preparedStatement.setString(2, NAME);
                preparedStatement.setString(3, PASSWORD);
                preparedStatement.setString(4, ADDRESS);
                preparedStatement.setDouble(5, BALANCE);
                preparedStatement.setString(6, SEX);
                preparedStatement.setDate(7, new Date(System.currentTimeMillis()));
                preparedStatement.setString(8, EMAIL);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    String alert = "Data updated successfully.";
                    alertbox.alertConfirm(alert);
                } else {
                    String alert = "No data updated. Account Number not found.";
                    alertbox.alertError(alert);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutCustomer(int account_no) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM customer");

            int found = 0;
            while (resultSet.next()) {
                if (account_no == resultSet.getInt("account_no")) {
                    String deleteSQL = "DELETE FROM customer WHERE account_no = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        preparedStatement.setInt(1, account_no);

                        found++;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (found == 0) {
                    String alert = "Account Number not found";
                    alertbox.alertError(alert);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}