package com.project.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.json.JSONObject;

@WebServlet("/UserBalanceServlet")
public class UserBalanceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static double balance = 50000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        JSONObject res = new JSONObject();
        res.put("balance", balance);

        response.getWriter().write(res.toString());
    }
}
