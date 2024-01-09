import java.sql.*;
import java.sql.Date;

public class Customer {

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

    // login customer page
    public int loginCustomer(int accNo, String passcode) {
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

                if (accNo == resultSet.getInt("account_no") && password.equals(resultSet.getString("password"))) {
                    found++;
                }
            }
            if (found == 0) {
                String alert = "Acount Number not found";
                alertbox.alertError(alert);
            }

            else {
                String alert = "Login successfully";
                alertbox.alertConfirm(alert);
            }

        } catch (Exception e) {
            e.toString();
        }

        return accNo;
    }

    public String seeCustomerProfile(int accNo) {
        String profile = "";

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
                    profile = "\n----------------------------------\n" +
                            "\tID: " + resultSet.getInt("id") + "\n\tName: " + resultSet.getString("name") +
                            "\n\tAddress: " + resultSet.getString("address") + "\n\tAvaliable Balance: "
                            + resultSet.getDouble("balance") +
                            "\n\tAccount Number: " + resultSet.getInt("account_no") + "\n\tSex: "
                            + resultSet.getString("sex") +
                            "\n\tJoin Date: " + resultSet.getDate("join_date") + "\n\tContact Email"
                            + resultSet.getString("email") +
                            "\n---------------------------------------\n";
                    found++;
                }
            }
            if (found == 0) {
                String alert = "Acount Number not found";
                alertbox.alertError(alert);
            }

        } catch (Exception e) {
            e.toString();
        }

        return profile;
    }

    public void editCustomerProfile(int accNo,String newData) {
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
            while (resultSet.next()) {
                if (accNo == resultSet.getInt("account_no")) {
                    String choice = "";// call choice button?

                    // name update
                    if (choice.equals("Name")) {

                        String newName = newData;

                        // Create and execute the update statement
                        String updateQuery = "UPDATE customer SET name=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newName);
                            preparedStatement.setInt(2, accNo);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
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
                        break;
                    }

                    // password update
                    else if (choice.equals("Password")) {

                        String newPassword = newData; 

                        // Create and execute the update statement
                        String updateQuery = "UPDATE customer SET password=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newPassword);
                            preparedStatement.setInt(2, accNo);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
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
                        break;
                    }

                    // update sex
                    else if (choice.equals("Sex")) {

                        String newSex =newData;

                        // Create and execute the update statement
                        String updateQuery = "UPDATE customer SET sex=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newSex);
                            preparedStatement.setInt(2, accNo);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
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
                        break;
                    }

                    else if (choice.equals("Email")) {

                        String newEmail = newData; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE customer SET name=? WHERE account_no=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newEmail);
                            preparedStatement.setInt(2, accNo);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
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
                        break;
                    }

                    else {
                        String alert = "Incorrect Choice";
                        alertbox.alertWarnning(alert);
                    }
                }
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    // CHECK BALANCE
    public String checkBalance(int accNo) {
        String balance = "";

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
                    balance = "Avaliable Balance: " + resultSet.getDouble("balance");
                    found++;
                }
            }

            // check get or not
            if (found == 0) {
                String alert = "Acount Number not found";
                alertbox.alertError(alert);
            }
        } catch (Exception e) {
            e.toString();
        }

        return balance;
    }

    // ADD CUSTOMER
    public void addCustomer(int ID, String NAME, String PASSWORD, String ADDRESS, double BALANCE, String SEX,
            String EMAIL) {

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Define the INSERT SQL statement with placeholders (?)
            String insertSQL = "INSERT INTO customer (id,name,password, address, balance, sex,join_date, email) VALUES (?, ?, ?, ?,?,?,?,?)";

            // Create a PreparedStatement to avoid SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set values for the placeholders
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, NAME);
            preparedStatement.setString(3, PASSWORD);
            preparedStatement.setString(4, ADDRESS);
            preparedStatement.setDouble(5, BALANCE);
            preparedStatement.setString(6, SEX);
            preparedStatement.setDate(7, new Date(System.currentTimeMillis()));
            preparedStatement.setString(4, EMAIL);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Display the result
            if (rowsAffected > 0) {
                String alert = "Data updated successfully.";
                alertbox.alertConfirm(alert);
            } else {
                String alert = "No data updated. Account Number not found.";
                alertbox.alertError(alert);
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LOGOUT METHOED
    public void logoutCustomer(int account_no) {

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
                if (account_no == resultSet.getInt("account_no")) {
                    // Define the DELETE SQL statement with a condition
                    String deleteSQL = "DELETE FROM customer WHERE account_no = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        // Set the value for the placeholder
                        preparedStatement.setInt(1, account_no);

                        found++;

                        // Close resources
                        preparedStatement.close();
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // check get or not
                if (found == 0) {
                String alert = "Acount Number not found";
                alertbox.alertError(alert);
            }
            }
        } catch (Exception e) {
            e.toString();
        }
    }
}
