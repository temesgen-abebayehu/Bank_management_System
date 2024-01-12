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
                String alert = "IInccorect ID or Password.";
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
            connectToDatabase();

            resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    if ("Name".equals(choice) || "Password".equals(choice) || "Sex".equals(choice)
                            || "Email".equals(choice)) {

                        String updateQuery = "UPDATE employee SET " + choice.toLowerCase() + "=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newValue);
                            preparedStatement.setInt(2, ID);

                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                String alert = "Data updated successfully.";
                                alertbox.alertConfirm(alert);
                            } else {
                                String alert = "No data updated. ID Number not found.";
                                alertbox.alertError(alert);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
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
    public void addEmployee(int ID, String NAME, String PASSWORD, String ADDRESS, String SEX,
            String EMAIL) {
        try {
            connectToDatabase();

            String insertSQL = "INSERT INTO employee (id,name,password, address, sex,join_date, email) VALUES (?, ?, ?, ?,?,?,?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                // Set values for the placeholders
                preparedStatement.setInt(1, ID);
                preparedStatement.setString(2, NAME);
                preparedStatement.setString(3, PASSWORD);
                preparedStatement.setString(4, ADDRESS);
                preparedStatement.setString(5, SEX);
                preparedStatement.setDate(6, new Date(System.currentTimeMillis()));
                preparedStatement.setString(7, EMAIL);

                // Execute the INSERT statement
                int rowsAffected = preparedStatement.executeUpdate();

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
            String deleteSQL = "DELETE FROM employee WHERE id = ?";

            resultSet = statement.executeQuery("SELECT * FROM employee");

            // check the account number and password
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {

                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        preparedStatement.setInt(1, ID);
                        int rowsAffected = preparedStatement.executeUpdate();

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

    public String getRoles(int ID) {
        String role = "";
        try {
            connectToDatabase();
            resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    role = resultSet.getString("roles");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return role;
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
