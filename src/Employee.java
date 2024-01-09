import java.sql.*;

public class Employee {
    // Database connection parameters
    String url = "jdbc:mysql://localhost:4306/bank_system";
    String username = "root";
    String password = "root";

    // JDBC objects
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // See Employee
    public String seeEmployeeProfile(int ID) {
        String profile = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM employee");

            // check the account number and password
            int a = 0;
            while (resultSet.next()) {

                if (ID == resultSet.getInt("id")) {
                    profile = "\n----------------------------------\n" +
                            "\tID: " + resultSet.getInt("id") + "\n\tName: " + resultSet.getString("name") +
                            "\n\tAddress: " + resultSet.getString("address") + "\n\tRoles: " +
                            resultSet.getString("role") + "\n\tSex: " + resultSet.getString("sex") +
                            "\n\tJoin Date: " + resultSet.getDate("join_date") + "\n\tContact Email: " +
                            resultSet.getString("email") +
                            "\n---------------------------------------\n";
                    a++;
                }
            }
            if (a == 0) {
                String alert = "Id not found";
            }

        } catch (Exception e) {
            e.toString();
        }

        return profile;
    }

    // Edit profile
    public void editCustomerProfile(int ID) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM employee");

            // check the account number and password
            int found = 0;
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    String choice = "";// call choice button?

                    // name update
                    if (choice.equals("Name")) {

                        String newName = "NewName"; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE employee SET name=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newName);
                            preparedStatement.setInt(2, ID);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
                            if (rowsAffected > 0) {
                                System.out.println("Name updated successfully.");
                            } else {
                                System.out.println("No Name updated. ID Number not found.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    // password update
                    else if (choice.equals("password")) {

                        String newPassword = "password"; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE employee SET password=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newPassword);
                            preparedStatement.setInt(2, ID);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
                            if (rowsAffected > 0) {
                                System.out.println("Password updated successfully.");// call alert box?
                            } else {
                                System.out.println("No pasword updated. id Number not found.");// call alert box?
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    // update sex
                    else if (choice.equals("sex")) {

                        String newSex = "sex"; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE employee SET sex=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newSex);
                            preparedStatement.setInt(2, ID);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
                            if (rowsAffected > 0) {
                                System.out.println("Sex updated successfully.");// call alert?
                            } else {
                                System.out.println("No sex updated. ID Number not found.");// call alert?
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    else if (choice.equals("email")) {

                        String newEmail = "email"; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE employee SET email=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newEmail);
                            preparedStatement.setInt(2, ID);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
                            if (rowsAffected > 0) {
                                System.out.println("Email updated successfully.");// call alert?
                            } else {
                                System.out.println("No Email updated. ID Number not found.");// call alert?
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    // update all
                    else if (choice.equals("All")) {

                        String newName = "name"; // call textfild?
                        String newPassword = "password"; // call textfild?
                        String newSex = ""; // call textfild?
                        String newEmail = "email"; // call textfild?

                        // Create and execute the update statement
                        String updateQuery = "UPDATE employee SET name=?,password=?,sex=?,email=? WHERE id=?";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                            preparedStatement.setString(1, newName);
                            preparedStatement.setString(2, newPassword);
                            preparedStatement.setString(3, newSex);
                            preparedStatement.setString(4, newEmail);
                            preparedStatement.setInt(5, ID);

                            // Execute the UPDATE statement
                            int rowsAffected = preparedStatement.executeUpdate();

                            // Display the result
                            if (rowsAffected > 0) {
                                System.out.println("All data updated successfully.");// call alert?
                            } else {
                                System.out.println("No data updated. id Number not found.");// call alert?
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    else {
                        String alert = "Incorrect password";
                        String type = "INFORMATION";
                        // call alert?
                    }

                    found++;
                }
            }

            if (found == 0) {
                String alert = "Id not found";
            }

        } catch (Exception e) {
            e.toString();
        }
    }

    // ADD Employee
    public void addCustomer(int ID, String NAME, String PASSWORD, String ADDRESS, String ROLE, String SEX,
            String EMAIL) {

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Define the INSERT SQL statement with placeholders (?)
            String insertSQL = "INSERT INTO employee (id,name,password, address, role, sex,join_date, email) VALUES (?, ?, ?, ?,?,?,?,?)";

            // Create a PreparedStatement to avoid SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set values for the placeholders
            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, NAME);
            preparedStatement.setString(3, PASSWORD);
            preparedStatement.setString(4, ADDRESS);
            preparedStatement.setString(5, ROLE);
            preparedStatement.setString(6, SEX);
            preparedStatement.setDate(7, new Date(System.currentTimeMillis()));
            preparedStatement.setString(4, EMAIL);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Display the result
            if (rowsAffected > 0) {
                System.out.println("Data added successfully.");
            } else {
                System.out.println("Failed to add data.");
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public void logoutCustomer(int ID) {

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement for executing SQL queries
            statement = connection.createStatement();

            // Execute a SQL query and get the result set
            resultSet = statement.executeQuery("SELECT * FROM employee");

            // check the account number and password
            int found = 0;
            while (resultSet.next()) {
                if (ID == resultSet.getInt("id")) {
                    // Define the DELETE SQL statement with a condition
                    String deleteSQL = "DELETE FROM employee WHERE id = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                        // Set the value for the placeholder
                        preparedStatement.setInt(1, ID);

                        // Execute the DELETE statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        // Display the result
                        if (rowsAffected > 0) {
                            System.out.println("Data deleted successfully.");
                        } else {
                            System.out.println("No data deleted. Customer ID not found.");
                        }
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
                    String alert = "Incorect ID Number or Password";
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }
}
