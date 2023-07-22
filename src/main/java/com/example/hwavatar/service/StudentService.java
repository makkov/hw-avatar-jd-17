package com.example.hwavatar.service;


import com.example.hwavatar.model.Student;
import com.example.hwavatar.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, int age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student update(long id, String name, int age) {
        Optional<Student> studentForUpdateOpt = studentRepository.findById(id);

        if (studentForUpdateOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }

        Student studentForUpdate = studentForUpdateOpt.get();
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        studentRepository.save(studentForUpdate);
        return studentForUpdate;
    }

    public Student delete(long id) {
        Optional<Student> studentForDeleteOpt = studentRepository.findById(id);

        if (studentForDeleteOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }

        Student studentForDelete = studentForDeleteOpt.get();

        studentRepository.delete(studentForDelete);
        return studentForDelete;
    }

    public long getCount() {
        logger.info("Was invoked method StudentService::getCount");
        return studentRepository.count();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudent() {
        return studentRepository.getLastFiveStudentsById();
    }
}
