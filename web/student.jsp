<%-- 
    Document   : student
    Created on : Jan 23, 2024, 7:02:15 AM
    Author     : Xuan Vinh
--%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="View.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="StudentServlet" method="post">
            <table>
                <tr>
                    <td>Number of Students: </td><td><input type="text" name="number"/></td><td><input type="submit" name="action" value="Generate"/></td>
                </tr>
            </table>
        </form>
        <table border="1" width="50%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // lấy dữ liệu từ servlet 
                    List<Student> list = (List<Student>) request.getAttribute("students");
                    if (list != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        // Display generated students
                        for (Student student : list) {
                %>
                <tr>
                    <td><%= student.getId()%></td>
                    <td><%= student.getName()%></td>
                    <td>
                        <% if (student.isGender()) {
                        %>
                        <input type="checkbox" checked/>
                        <%
                        } else {
                        %>
                        <input type="checkbox"/>
                        <%
                            }
                        %>
                    </td>
                    <td><%= dateFormat.format(student.getDob())%></td>
                    <td>
                        <form id="updateForm" action="StudentServlet" method="post">
                            <input type="submit" name="action" value="update"/>
                            <input style="display: none" name="updateId" value=<%= student.getId()%>>
                        </form>
                    </td>

                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
