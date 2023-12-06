package quiz.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quiz.dto.UserDTO;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("Derby driver not found", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the login JSP page to show the login form
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDTO user = authenticateOrCreate(request, response, email, password); // Implement this method to check credentials

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Store the user object in the session

            response.sendRedirect("home");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private UserDTO authenticateOrCreate(HttpServletRequest request, HttpServletResponse response, String email, String password) {
        String dbUrl = "jdbc:derby://localhost:1527/quizDB";
        String dbUser = "test";
        String dbPassword = "test";

        UserDTO user = null;
        String selectSql = "SELECT * FROM TEST.USERS WHERE USERNAME = ?";
        String insertSql = "INSERT INTO TEST.USERS (USERNAME, PASSWORD) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // First, try to find the user
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("PASSWORD");
                        if (storedPassword != null && storedPassword.equals(password)) {
                            user = new UserDTO(rs.getInt("ID"), rs.getString("USERNAME"));
                        } else {
                            request.setAttribute("loginError", "Wrong password, try again.");
                            try{
                                request.getRequestDispatcher("/login.jsp").forward(request, response);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            return null;
                        }
                    }
                }
            }

            // If user was not found, create a new user
            if (user == null) {
                try (PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password); // In a real application, you should hash and salt the password

                    int affectedRows = stmt.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet rs = stmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                int userId = rs.getInt(1);
                                user = new UserDTO(userId, email); // Create a new User object with the new ID
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In production, use proper logging
        }

        return user;
    }

}