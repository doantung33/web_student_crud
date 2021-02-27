package controller;

import model.Student;
import service.IServiceStudent;
import service.ServiceStudent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet(name = "ServletStudent", value = "/students")
public class ServletStudent extends HttpServlet {
    IServiceStudent serviceStudent=new ServiceStudent();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createForm(request,response);
                break;
            case "edit":
                editForm(request,response);
                break;
            case "delete":
                deleteForm(request,response);
                break;
            case "search":
                searchStudent(request,response);
            default:
                listStudent(request,response);
                break;
        }
    }

    private void searchStudent(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("name");
        List<Student>students=serviceStudent.findbyName(name);
        request.setAttribute("student",students);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("student/list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) {
        List<Student>students=serviceStudent.findAll();
        request.setAttribute("student",students);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("student/list.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Student student= serviceStudent.findById(id);
        request.setAttribute("s",student);
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("student/delete.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        Student student=serviceStudent.findById(id);
        request.setAttribute("s",student);
        RequestDispatcher requestDispatcher =request.getRequestDispatcher("student/edit.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("student/create.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action= request.getParameter("action");
        switch (action){
            case "create":
                createStudent(request,response);
                break;
            case "edit":
                editStudent(request,response);
                break;
            case "delete":
                deleteStudent(request,response);
                break;
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        serviceStudent.delete(id);
        try {
            response.sendRedirect("student/delete.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
        String address= request.getParameter("address");
        serviceStudent.edit(new Student(id,name,age,address));
        try {
            response.sendRedirect("student/edit.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("name");
        int age= Integer.parseInt(request.getParameter("age"));
        String address =request.getParameter("address");
        serviceStudent.save(new Student(name,age,address));
        try {
            response.sendRedirect("student/create.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
