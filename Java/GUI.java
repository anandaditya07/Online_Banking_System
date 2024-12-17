public class GUI {
    // Source code is decompiled from a .class file using FernFlower decompiler.

    class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;

        /**
         * Constructor to initialize a new BankAccount.
         *
         * @param accountNumber     the account number
         * @param accountHolderName the name of the account holder
         * @param initialBalance    the initial balance of the account
         */
        public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return this.accountNumber;
        }

        public String getAccountHolderName() {
            return this.accountHolderName;
        }

        public double getBalance() {
            return this.balance;
        }

        /**
         * Deposits an amount into the account.
         *
         * @param amount the amount to deposit
         */
        public void deposit(double amount) {
            if (amount > 0.0) {
                this.balance += amount;
                System.out.println("Deposit of Rs." + amount + " successful. New balance: Rs." + this.balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        /**
         * Withdraws an amount from the account.
         *
         * @param amount the amount to withdraw
         */
        public void withdraw(double amount) {
            if (amount > 0.0 && amount <= this.balance) {
                this.balance -= amount;
                System.out.println("Withdrawal of Rs." + amount + " successful. New balance: Rs." + this.balance);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        }

        /**
         * Displays the account details.
         */
        public void displayAccountDetails() {
            System.out.println("Account Number: " + this.accountNumber);
            System.out.println("Account Holder Name: " + this.accountHolderName);
            System.out.println("Current Balance: Rs." + this.balance);
        }
    }
}