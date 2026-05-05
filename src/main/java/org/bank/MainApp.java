package org.bank;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        CustomerDAO dao = new CustomerDAO();

        while (true) {
            System.out.println("\n1.Create/Add Account 2.Deposit 3.Withdraw 4.Check Balance 5.Delete 6.Exit");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Enter AccNo:");
                    int id = sc.nextInt(); sc.nextLine();

                    System.out.println("Enter Name:");
                    String name = sc.nextLine();

                    System.out.println("Enter Address:");
                    String address = sc.nextLine();

                    System.out.println("Enter Pincode:");
                    int pincode = sc.nextInt(); sc.nextLine();

                    System.out.println("Enter Phone:");
                    String phone = sc.nextLine();

                    System.out.println("Enter Deposit:");
                    double deposit = sc.nextDouble();

                    Customer c = new Customer(id, name, address, pincode, phone, deposit);
                    dao.insert(c);
                    break;

    case 2:
        System.out.println("Enter AccNo:");
        int did = sc.nextInt();
        System.out.println("Enter deposit amount:");
        double damt = sc.nextDouble();
        dao.deposit(did, damt);
        break;

    case 3:
        System.out.println("Enter AccNo:");
        int wid = sc.nextInt();
        System.out.println("Enter withdraw amount:");
        double wamt = sc.nextDouble();
        dao.withdraw(wid, wamt);
        break;

    case 4:
        System.out.println("Enter AccNo:");
        int cid = sc.nextInt();
        dao.checkBalance(cid);
        break;

    case 5:
        System.out.println("Enter AccNo you want to delete:");
        dao.delete(sc.nextInt());
        break;

    case 6:
        System.exit(0);
            }
        }
    }
}