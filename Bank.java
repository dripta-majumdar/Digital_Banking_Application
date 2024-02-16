//********* Cash withdrawal program using Java *********

import java.util.*;

public class Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer name: ");
        String name = sc.nextLine();
        while (!name.matches("[a-zA-Z\\.\\s]+")) {
            System.out.println("Please enter a valid Customer name!!!");
            name = sc.nextLine();
        }
        System.out.println("Enter your Account Number:");
        String AccountNo = sc.nextLine();
        while (!AccountNo.matches("^[0-9]{10}$")) {
            System.out.println("Please enter a valid Account Number!!!");
            AccountNo = sc.nextLine();
        }
        BankAccount obj1 = new BankAccount(name, AccountNo);
        obj1.menu();
    }
}

class BankAccount {
    double bal;
    double prevTrans;
    String customerName;
    String AccountNo;

    BankAccount(String customerName, String AccountNo) {
        this.customerName = customerName;
        this.AccountNo = AccountNo;
    }

    void deposit(double amount) {
        if (amount != 0) {
            bal += amount;
            prevTrans = amount;
        }
    }

    void withdraw(double amt) {
        if (amt != 0 && bal >= amt) {
            bal -= amt;
            prevTrans = -amt;
        } else if (bal < amt) {
            System.out.println("Bank balance insufficient!!!");
        }
    }

    void getPreviousTrans() {
        if (prevTrans > 0) {
        } else if (prevTrans < 0) {
            System.out.println("Withdrawn: " + Math.abs(prevTrans));
        } else {
            System.out.println("Deposited: " + prevTrans);
            System.out.println("No transaction occurred");
        }
    }

    void menu() {
        char option;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");

        System.out.println("Welcome " + customerName);
        System.out.println("Your Bank Account No:" + AccountNo);
        System.out.println("\n");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Withdraw Amount");
        System.out.println("4. Previous Transaction");
        System.out.println("5. Exit");

        do {
            System.out.println("\n");
            System.out.println("Choose an option");
            option = sc.next().charAt(0);

            switch (option) {
                case '1':
                    System.out.println("......................");
                    System.out.println("Balance =" + bal);
                    System.out.println("......................");
                    break;
                case '2':
                    System.out.println("......................");
                    System.out.println("Enter a amount to deposit :");
                    double amt = sc.nextDouble();
                    deposit(amt);
                    System.out.println("......................");
                    break;
                case '3':
                    System.out.println("......................");
                    System.out.println("Enter a amount to Withdraw :");
                    double amtW = sc.nextDouble();
                    withdraw(amtW);
                    System.out.println("......................");
                    break;
                case '4':
                    System.out.println("......................");
                    System.out.println("Previous Transaction:");
                    getPreviousTrans();
                    System.out.println("......................");
                    break;
                case '5':
                    System.out.println("......................");
                    break;
                default:
                    System.out.println("Choose a correct option to proceed");
                    break;
            }

        } while (option != '5');

        System.out.println("Thank you for using our banking service");
        System.out.println("Have a good day");
        System.out.println("......................");
    }
}
