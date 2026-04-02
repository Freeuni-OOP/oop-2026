package service;

import dao.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import static util.Converter.toStudentDto;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public StudentDTO getStudentById(int id) {
        Student student = studentDAO.getStudentById(id);
        return toStudentDto(student);
    }

}