package com.project.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.json.JSONObject;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static double balance = 50000;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	

        double amount = Double.parseDouble(request.getParameter("amount"));
        String from = request.getParameter("from");
        String to = request.getParameter("to");

        JSONObject res = new JSONObject();

        if (amount > balance) {
            res.put("status", "INSUFFICIENT_FUNDS");
        } else {

            double rate = getRate(from, to);
            double converted = amount * rate;

            double tax = converted * getTaxRate(to);
            double fee = Math.min(50, converted * 0.05); // FIXED

            double finalAmount = converted - tax - fee;

            balance -= amount;

            res.put("status", "SUCCESS");
            res.put("convertedAmount", converted);
            res.put("tax", tax);
            res.put("fee", fee);
            res.put("finalAmount", finalAmount);
            res.put("remainingBalance", balance);
        }

        response.getWriter().write(res.toString());
    }

    private double getRate(String from, String to) {
        if (from.equals("INR") && to.equals("USD")) return 0.012;
        if (from.equals("USD") && to.equals("INR")) return 83;
        if (from.equals("INR") && to.equals("EUR")) return 0.011;
        if (from.equals("EUR") && to.equals("INR")) return 90;
        return 1;
    }

    private double getTaxRate(String currency) {
        switch (currency) {
            case "INR": return 0.02;
            case "USD": return 0.015;
            case "EUR": return 0.018;
            default: return 0.01;
        }
    }
}
