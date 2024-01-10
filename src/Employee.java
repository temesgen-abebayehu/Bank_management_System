import java.sql.*;

public class Employee {

    private String url = "jdbc:mysql://localhost:4306/bank_system";
    private String username = "root";
    private String password = "root";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    // class
    Alertbox alertbox = new Alertbox();

    public int loginEmployee(int ID, String passcode) {
        try {
            connectToDatabase();
            resultSet = statement.executeQuery("SELECT * FROM employee");

            int found = 0;
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id") && passcode.equals(resultSet.getString("password"))) {
                    found++;
                }
            }

            if (found == 0) {
                ID = -1;
                String alert = "Account Number not found";
                alertbox.alertError(alert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return ID;
    }

    public String seeEmployeeProfile(int ID) {
        String profile = "";

        try {
            connectToDatabase();

            resultSet = statement.executeQuery("SELECT * FROM employee");

            int found = 0;
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    profile = "\n----------------------------------\n" +
                            "\tID: " + resultSet.getInt("id") + "\n\tName: " + resultSet.getString("name") +
                            "\n\tAddress: " + resultSet.getString("address") + "\n\tRoles: " +
                            resultSet.getString("roles") + "\n\tSex: " + resultSet.getString("sex") +
                            "\n\tJoin Date: " + resultSet.getDate("join_date") + "\n\tContact Email: " +
                            resultSet.getString("email") +
                            "\n-------------------------------------\n";
                    found++;
                }
            }

            if (found == 0) {
                String alert = "Id not found";
                alertbox.alertError(alert);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return profile;
    }

    public void editEmployeeProfile(int ID, String newValue, String choice) {
        try {
            String updateQuery;
            connectToDatabase();

            resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    switch (choice) {
                        case "Name":
                            updateQuery = "UPDATE employee SET name=? WHERE id=?";
                        case "Password":
                            updateQuery = "UPDATE employee SET password=? WHERE id=?";
                        case "Sex":
                            updateQuery = "UPDATE employee SET sex=? WHERE id=?";
                        case "Email":
                            updateQuery = "UPDATE employee SET email=? WHERE id=?";
                        default:
                            updateQuery = "UPDATE employee SET name=?, password=?, sex=?, email=? WHERE id=?";
                    }

                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        preparedStatement.setString(1, newValue);
                        preparedStatement.setInt(2, ID);

                        // Execute the INSERT statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        // Display the result
                        if (rowsAffected > 0) {
                            String alert = choice + " updated successfully.";
                            alertbox.alertError(alert);
                        } else {
                            String alert = "No " + choice + " updated. ID Number not found.";
                            alertbox.alertError(alert);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // ADD Employee
    public void addEmployee(int ID, String NAME, String PASSWORD, String ADDRESS, String ROLE, String SEX,
            String EMAIL) {
        try {
            connectToDatabase();

            // Define the INSERT SQL statement with placeholders (?)
            String insertSQL = "INSERT INTO employee (id,name,password, address, roles, sex,join_date, email) VALUES (?, ?, ?, ?,?,?,?,?)";

            // Create a PreparedStatement to avoid SQL injection
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                // Set values for the placeholders
                preparedStatement.setInt(1, ID);
                preparedStatement.setString(2, NAME);
                preparedStatement.setString(3, PASSWORD);
                preparedStatement.setString(4, ADDRESS);
                preparedStatement.setString(5, ROLE);
                preparedStatement.setString(6, SEX);
                preparedStatement.setDate(7, new Date(System.currentTimeMillis()));
                preparedStatement.setString(8, EMAIL);

                // Execute the INSERT statement
                int rowsAffected = preparedStatement.executeUpdate();

                // Display the result
                if (rowsAffected > 0) {
                    String alert = "Data added successfully.";
                    alertbox.alertConfirm(alert);
                } else {
                    String alert = "Failed to add data.";
                    alertbox.alertError(alert);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Delete Employee
    public void deleteEmployee(int ID) {
        try {
            connectToDatabase();

            // Define the DELETE SQL statement with a condition
            String deleteSQL = "DELETE FROM employee WHERE id = ?";

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM employee");

            // check the account number and password
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {

                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        // Set the value for the placeholder
                        preparedStatement.setInt(1, ID);

                        // Execute the DELETE statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        // Display the result
                        if (rowsAffected > 0) {
                            String alert = "Data deleted successfully.";
                            alertbox.alertConfirm(alert);
                        } else {
                            String alert = "No data deleted. Employee ID not found.";
                            alertbox.alertError(alert);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void connectToDatabase() throws ClassNotFoundException, SQLException {
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
