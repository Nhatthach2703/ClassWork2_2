/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import View.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Xuan Vinh
 */
public class StudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private List<Student> listStudent = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        // lấy data form
        String choice = request.getParameter("action");
        if (choice.equalsIgnoreCase("Generate")) {
            listStudent.clear();
            if (listStudent == null) {
                listStudent = new ArrayList<>();
            }

            int number = Integer.parseInt(request.getParameter("number"));

            for (int i = 1; i <= number; i++) {
                Student student = generateStudent(i);
                listStudent.add(student);
            }

        } else {
            int id = Integer.parseInt(request.getParameter("updateId"));
            Student student = generateStudent(id);
            // set vị trí thứ id-1 của id 
            listStudent.set((id - 1), student);
        }

        // Gửi listStudent sang file student.jsp
        request.setAttribute("students", listStudent);
        RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
        rd.forward(request, response);
    }
    
    private Student generateStudent(int id) {
        Random random = new Random();
        String name = generateName();
        Date dob = generateDOB();
        boolean gender = random.nextBoolean();
        return new Student(id, name, gender, dob);
    }

//-------------- random Date Of Birth ---------------------
    private Date generateDOB() {
        Random random = new Random();

        // Sinh ngày ngẫu nhiên trong khoảng từ 2003 đến nay
        int year = 2003 + random.nextInt(2025 - 2003); // Năm từ 1970 đến 2024
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(31);

        // Tạo đối tượng Calendar và thiết lập ngày tháng năm
        Calendar calendar = new GregorianCalendar(year, month - 1, day);

        // Lấy đối tượng Date từ đối tượng Calendar
        Date dob = calendar.getTime();
        return dob;
    }
//-------------- random Name ---------------------

    private String generateName() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int nameLength = 8;
        Random random = new Random();

        // Tạo một StringBuilder để xây dựng tên
        StringBuilder randomName = new StringBuilder(nameLength);

        // Tạo tên ngẫu nhiên bằng cách chọn ngẫu nhiên từ chuỗi ký tự
        for (int i = 0; i < nameLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomName.append(randomChar);
        }
        return randomName.toString();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
