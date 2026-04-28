package com.project.servlets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CurrencyServlet")
public class CurrencyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 🔥 Cache variables
    private static String cachedResponse = null;
    private static long lastFetchTime = 0;
    private static final long CACHE_DURATION = 60 * 60 * 1000; // 1 hour

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");

        long currentTime = System.currentTimeMillis();

        // ✅ Use cache if within 1 hour
        if (cachedResponse != null && (currentTime - lastFetchTime) < CACHE_DURATION) {
            response.setContentType("application/json");
            response.getWriter().write(cachedResponse);
            System.out.println("Serving from CACHE");
            return;
        }

        // 🔥 API URL (free, no key required)
        String apiUrl = "https://api.exchangerate-api.com/v4/latest/USD";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();

        // ✅ Save to cache
        cachedResponse = result.toString();
        lastFetchTime = currentTime;

        response.setContentType("application/json");
        response.getWriter().write(cachedResponse);

        System.out.println("Fetched from API");
    }
}
