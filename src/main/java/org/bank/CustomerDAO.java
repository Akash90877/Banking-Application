package org.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    public void insert(Customer c) throws SQLException {
        String sql = "INSERT INTO customer_details " +
                     "(customer_id, name, address, Pincode, PhoneNo, Initial_deposit) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getCustomerId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setInt(4, c.getPincode());
            ps.setString(5, c.getPhoneNo());
            ps.setDouble(6, c.getInitialDeposit());
            ps.executeUpdate();
            System.out.println("Customer inserted successfully");
        }
    }
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM customer_details";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("customer_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("address") + " | " +
                    rs.getInt("Pincode") + " | " +
                    rs.getString("PhoneNo") + " | " +
                    rs.getDouble("Initial_deposit")
                );
            }
        }
    }
    public void update(int id, String address, double deposit) throws SQLException {
        String sql = "UPDATE customer_details SET address=?, Initial_deposit=? WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, address);
            ps.setDouble(2, deposit);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM customer_details WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);
        }
    }
    public void deposit(int id, double amount) throws SQLException {
    String sql = "UPDATE customer_details SET Initial_deposit = Initial_deposit + ? WHERE customer_id=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        int rows = ps.executeUpdate();
        System.out.println(rows > 0 ? "Deposit successful" : "Account not found");
    }
}
public void withdraw(int id, double amount) throws SQLException {
    String checkSql = "SELECT Initial_deposit FROM customer_details WHERE customer_id=?";
    String updateSql = "UPDATE customer_details SET Initial_deposit=? WHERE customer_id=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement check = con.prepareStatement(checkSql)) {
        check.setInt(1, id);
        ResultSet rs = check.executeQuery();
        if (rs.next()) {
            double balance = rs.getDouble("Initial_deposit");
            if (balance >= amount) {
                double newBalance = balance - amount;
                PreparedStatement update = con.prepareStatement(updateSql);
                update.setDouble(1, newBalance);
                update.setInt(2, id);
                update.executeUpdate();
                System.out.println("Withdrawal successful. Remaining balance: " + newBalance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Account not found");
        }
    }
}
public void checkBalance(int id) throws SQLException {
    String sql = "SELECT Initial_deposit FROM customer_details WHERE customer_id=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Current Balance: " + rs.getDouble("Initial_deposit"));
        } else {
            System.out.println("Account not found");
        }
    }
}
    }