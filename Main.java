import java.util.*;

    

class BankAccount {
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

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of Rs." + amount + " successful. New balance: Rs." + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal of Rs." + amount + " successful. New balance: Rs." + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Current Balance: Rs." + balance);
    }
}

class BankManagementSystem {
    private List<BankAccount> accounts;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
    }

    public void createAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    public void deleteAccount(String accountNumber) {
        BankAccount accountToDelete = null;
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accountToDelete = account;
                break;
            }
        }

        if (accountToDelete != null) {
            accounts.remove(accountToDelete);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void searchAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.displayAccountDetails();
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public void displayAllAccounts() {
        for (BankAccount account : accounts) {
            account.displayAccountDetails();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManagementSystem bank = new BankManagementSystem();

        while (true) {
            System.out.println("\nBank Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Search Account");
            System.out.println("4. Display All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(new BankAccount(accountNumber, accountHolderName, balance));
                    break;
                case 2:
                    System.out.print("Enter account number to delete: ");
                    accountNumber = scanner.next();
                    bank.deleteAccount(accountNumber);
                    break;
                case 3:
                    System.out.print("Enter account number to search: ");
                    accountNumber = scanner.next();
                    bank.searchAccount(accountNumber);
                    break;
                case 4:
                    bank.displayAllAccounts();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

