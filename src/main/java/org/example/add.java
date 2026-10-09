package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

@WebServlet("/add")
public class add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lakshman",
                    "root",
                    "LAKSHMAN113"
            );

            // Create statement
            Statement st = con.createStatement();

            // Get data from stu1 table
            ResultSet rs = st.executeQuery("SELECT name, branch FROM stu1");

            // HTML output
            out.println("<html>");
            out.println("<body style='text-align:center; margin-top:100px;'>");

            out.println("<h1>Student Details</h1>");

            out.println("<table border='1' style='margin:auto;'>");

            // Table headings
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>Branch</th>");
            out.println("</tr>");

            // Display database records
            while (rs.next()) {

                out.println("<tr>");

                out.println("<td>" + rs.getString("name") + "</td>");

                out.println("<td>" + rs.getString("branch") + "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

            // Close resources
            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            throw new ServletException(e);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}