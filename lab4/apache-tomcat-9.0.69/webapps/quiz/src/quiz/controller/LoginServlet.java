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

@WebServlet("/")
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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
    
        UserDTO user = authenticateOrCreate(request, email, password);
    
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            // If authentication fails, set the error message and forward back to the login page.
            request.setAttribute("loginError", "Invalid password. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private UserDTO authenticateOrCreate(HttpServletRequest request, String email, String password) {
        String dbUrl = "jdbc:derby://localhost:1527/quizDB";
        String dbUser = "test";
        String dbPassword = "test";
        
        UserDTO user = null;
        String selectSql = "SELECT * FROM TEST.USERS WHERE USERNAME = ?";
        String insertSql = "INSERT INTO TEST.USERS (USERNAME, PASSWORD) VALUES (?, ?)";
    
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("PASSWORD");
                        if (storedPassword != null && storedPassword.equals(password)) {
                            user = new UserDTO(rs.getInt("ID"), rs.getString("USERNAME"));
                        } else {
                            // Set error message attribute here, the actual forwarding happens in doPost
                            request.setAttribute("errorMessage", "Incorrect password, please try again.");
                            return null;
                        }
                    }
                }
            }
    
            // User does not exist, create a new user
            if (user == null) {
                try (PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password);
                    
                    int affectedRows = stmt.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet rs = stmt.getGeneratedKeys()) {
                            if (rs.next()) {
                                int userId = rs.getInt(1);
                                user = new UserDTO(userId, email);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred. Please try again.");
        }
        
        return user;
    }

}