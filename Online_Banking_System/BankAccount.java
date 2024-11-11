package Online_Banking_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bank_management_system";
    private static final String USER = "root"; // Change this to your MySQL username
    private static final String PASSWORD = "your_password"; // Change this to your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

class BankManagementGUI extends JFrame {
    private JTextField accountNumberField;
    private JTextField accountHolderNameField;
    private JTextField balanceField;
    private JTextArea outputArea;

    public BankManagementGUI() {
        setTitle("Bank Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create UI components
        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField(15);
        
        JLabel accountHolderNameLabel = new JLabel("Account Holder Name:");
        accountHolderNameField = new JTextField(15);
        
        JLabel balanceLabel = new JLabel("Initial Balance:");
        balanceField = new JTextField(15);
        
        JButton createButton = new JButton("Create Account");
        JButton deleteButton = new JButton("Delete Account");
        JButton searchButton = new JButton("Search Account");
        JButton displayButton = new JButton("Display All Accounts");
        
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        
        // Add components to the frame
        add(accountNumberLabel);
        add(accountNumberField);
        add(accountHolderNameLabel);
        add(accountHolderNameField);
        add(balanceLabel);
        add(balanceField);
        add(createButton);
        add(deleteButton);
        add(searchButton);
        add(displayButton);
        add(new JScrollPane(outputArea));

        // Add action listeners
        createButton.addActionListener(new CreateAccountAction());
        deleteButton.addActionListener(new DeleteAccountAction());
        searchButton.addActionListener(new SearchAccountAction());
        displayButton.addActionListener(new DisplayAllAccountsAction());
    }

    // Action classes for buttons
    private class CreateAccountAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            String accountHolderName = accountHolderNameField.getText();
            double balance;
            try {
                balance = Double.parseDouble(balanceField.getText());
                createAccount(new BankAccount(accountNumber, accountHolderName, balance));
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid balance amount.");
            }
        }
    }

    private class DeleteAccountAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            deleteAccount(accountNumber);
        }
    }

    private class SearchAccountAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accountNumber = accountNumberField.getText();
            searchAccount(accountNumber);
        }
    }

    private class DisplayAllAccountsAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayAllAccounts();
        }
    }

    // Database methods
    private void createAccount(BankAccount account) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO bank_accounts (account_number, account_holder_name, balance) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getAccountNumber());
 statement.setString(2, account.getAccountHolderName());
            statement.setDouble(3, account.getBalance());
            statement.executeUpdate();
            outputArea.setText("Account created successfully.");
        } catch (SQLException e) {
            outputArea.setText("Error creating account: " + e.getMessage());
        }
    }

    private void deleteAccount(String accountNumber) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM bank_accounts WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                outputArea.setText("Account deleted successfully.");
            } else {
                outputArea.setText("Account not found.");
            }
        } catch (SQLException e) {
            outputArea.setText("Error deleting account: " + e.getMessage());
        }
    }

    private void searchAccount(String accountNumber) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM bank_accounts WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String accountHolderName = resultSet.getString("account_holder_name");
                double balance = resultSet.getDouble("balance");
                outputArea.setText("Account Holder: " + accountHolderName + "\nBalance: " + balance);
            } else {
                outputArea.setText("Account not found.");
            }
        } catch (SQLException e) {
            outputArea.setText("Error searching account: " + e.getMessage());
        }
    }

    private void displayAllAccounts() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM bank_accounts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            StringBuilder output = new StringBuilder();
            while (resultSet.next()) {
                String accountNumber = resultSet.getString("account_number");
                String accountHolderName = resultSet.getString("account_holder_name");
                double balance = resultSet.getDouble("balance");
                output.append("Account Number: ").append(accountNumber)
                      .append(", Account Holder: ").append(accountHolderName)
                      .append(", Balance: ").append(balance).append("\n");
            }
            outputArea.setText(output.toString());
        } catch (SQLException e) {
            outputArea.setText("Error displaying accounts: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankManagementGUI gui = new BankManagementGUI();
            gui.setVisible(true);
        });
    }
}

