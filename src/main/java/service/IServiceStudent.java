package service;

import model.Student;

import java.util.List;

public interface IServiceStudent {
    List<Student>findAll();
    Student save(Student student);
    boolean edit(Student student);
    boolean delete(int id);
    Student findById(int id);
    List<Student>findbyName(String name);

}
