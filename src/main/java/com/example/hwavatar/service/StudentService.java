package com.example.hwavatar.service;


import com.example.hwavatar.model.Student;
import com.example.hwavatar.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    public List<String> getNamesStartWithA() {
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(toList());
    }

    public double getAvgAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }

    public void taskThread() {
        List<Student> students = studentRepository.findAll();

        printStudent(students.get(0));
        printStudent(students.get(1));

        new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        }).start();

        new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        }).start();
    }

    private void printStudent(Student student) {
        try {
            Thread.sleep(1000);
            logger.info(student.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void taskThreadSync() {
        List<Student> students = studentRepository.findAll();
        logger.info(students.toString());

        printStudentSync(students.get(0));
        printStudentSync(students.get(1));

        new Thread(() -> {
            printStudentSync(students.get(2));
            printStudentSync(students.get(3));
        }).start();

        new Thread(() -> {
            printStudentSync(students.get(4));
            printStudentSync(students.get(5));
        }).start();
    }

    private synchronized void printStudentSync(Student student) {
        printStudent(student);
    }
}
