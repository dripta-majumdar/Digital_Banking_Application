//********* Cash withdrawal program using Java *********

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Bank extends Application {
    private BankAccount bankAccount;

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label nameLabel = new Label("Enter Customer Name:");
        TextField nameField = new TextField();

        Label accountLabel = new Label("Enter Account Number:");
        TextField accountField = new TextField();

        Label depositLabel = new Label("Enter Deposit Amount:");
        TextField depositField = new TextField();

        Label withdrawLabel = new Label("Enter Withdrawal Amount:");
        TextField withdrawField = new TextField();

        Button checkBalanceButton = new Button("Check Balance");
        Button depositButton = new Button("Deposit Amount");
        Button withdrawButton = new Button("Withdraw Amount");
        Button previousTransactionButton = new Button("Previous Transaction");
        Button exitButton = new Button("Exit");
        Button createAccountButton = new Button("Create Account");

        Label messageLabel = new Label();

        checkBalanceButton.setOnAction(e -> {
            if (bankAccount != null) {
                double balance = bankAccount.getBalance();
                messageLabel.setText("Balance = " + balance);
                System.out.println("Checked balance: " + balance);
            }
        });

        depositButton.setOnAction(e -> {
            if (bankAccount != null) {
                String amountText = depositField.getText();
                double amount = Double.parseDouble(amountText);
                bankAccount.deposit(amount);
                messageLabel.setText("Deposited: " + amount);
                System.out.println("Deposited: " + amount);
            }
        });

        withdrawButton.setOnAction(e -> {
            if (bankAccount != null) {
                String amountText = withdrawField.getText();
                double amount = Double.parseDouble(amountText);
                bankAccount.withdraw(amount);
                messageLabel.setText("Withdrawn: " + amount);
                System.out.println("Withdrawn: " + amount);
            }
        });

        previousTransactionButton.setOnAction(e -> {
            if (bankAccount != null) {
                double prevTrans = bankAccount.getPreviousTrans();
                messageLabel.setText("Previous Transaction: " + prevTrans);
                System.out.println("Previous Transaction: " + prevTrans);
            }
        });

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        createAccountButton.setOnAction(e -> {
            String name = nameField.getText();
            String accountNumber = accountField.getText();
            if (accountNumber.length() > 10) {
                messageLabel.setText("Account number cannot exceed 10 digits");
                System.out.println("Account number cannot exceed 10 digits");
            } else {
                bankAccount = new BankAccount(name, accountNumber);
                messageLabel.setText("Account created for " + name);
                System.out.println("Account created for " + name);
            }
        });

        vbox.getChildren().addAll(nameLabel, nameField, accountLabel, accountField, depositLabel, depositField, withdrawLabel, withdrawField, checkBalanceButton, depositButton,
                withdrawButton,
                previousTransactionButton, exitButton, createAccountButton, messageLabel);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class BankAccount {
    private double bal;
    private double prevTrans;
    private String customerName;
    private String accountNo;

    BankAccount(String customerName, String accountNo) {
        this.customerName = customerName;
        this.accountNo = accountNo;
        System.out.println("Account created for " + customerName + " with account number " + accountNo);
    }

    void deposit(double amount) {
        if (amount != 0) {
            bal += amount;
            prevTrans = amount;
            System.out.println("Deposited: " + amount);
        }
    }

    void withdraw(double amt) {
        if (amt != 0 && bal >= amt) {
            bal -= amt;
            prevTrans = -amt;
            System.out.println("Withdrawn: " + amt);
        } else if (bal < amt) {
            System.out.println("Bank balance insufficient!!!");
        }
    }

    double getBalance() {
        System.out.println("Balance: " + bal);
        return bal;
    }

    double getPreviousTrans() {
        System.out.println("Previous Transaction: " + prevTrans);
        return prevTrans;
    }
}
