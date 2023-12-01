package org.example;

import javax.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/guess")
public class Lab4Servlet extends HttpServlet{
    private PrintWriter out;
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        try {
            out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Hello, Guessing game!</h1>");
            out.println("</body></html>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
