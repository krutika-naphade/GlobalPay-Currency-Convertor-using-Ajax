package com.project.servlets;

import java.util.HashMap;
import java.util.Map;

public class MockDB {

    // Stores user balances
    public static Map<String, Double> users = new HashMap<>();

    // Static block runs once when app starts
    static {
        users.put("user123", 50000.0); // initial balance
    }

    // Method to get balance
    public static double getBalance(String userId) {
        return users.getOrDefault(userId, 0.0);
    }

    // Method to update balance
    public static void updateBalance(String userId, double newBalance) {
        users.put(userId, newBalance);
    }
}
