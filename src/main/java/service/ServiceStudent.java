package service;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceStudent implements IServiceStudent{
    Connection connection;
    Connection getConnection(){
        if (connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student","root","123456");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("da ket noi");

        }
        return connection;
    }
    @Override
    public List<Student> findAll() {
        List<Student>students=new ArrayList<>();
        try {
            connection=getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("select * from sudent;");
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int age= resultSet.getInt("age");
                String address=resultSet.getString("address");
                Student student= new Student(id,name,age,address);
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    @Override
    public Student save(Student student) {
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement("insert into sudent (name,age,address) value (?,?,?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean edit(Student student) {
        boolean check =false;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("update sudent set name =? ,age=?,address=? where id=?");
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setInt(4,student.getId());
            check=preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check=false;
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("delete from sudent where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            check=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public Student findById(int id) {
        Student student=null;
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement("select * from sudent where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                String name= resultSet.getString("name");
                int age=resultSet.getInt("age");
                String address=resultSet.getString("address");
                student= new Student(name,age,address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return student;
    }

    @Override
    public List<Student> findbyName(String name) {
        List<Student>students=new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("select * from sudent where name like ?" );
            preparedStatement.setString(1,"%"+name+"%");
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                String name1=resultSet.getString("name");
                int age= resultSet.getInt("age");
                String address= resultSet.getString("address");
                students.add(new Student(name,age,address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }
}
