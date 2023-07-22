package com.example.hwavatar.service;

import com.example.hwavatar.model.Student;
import com.example.hwavatar.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getAverageAge_success() {
        //Подготовка входных данных
        double avgAge = 45.5;

        //Подготовка ожидаемого результата
        when(studentRepository.getAverageAge()).thenReturn(avgAge);

        //Начало теста
        double actualAvgAge = studentService.getAverageAge();
        assertEquals(avgAge, actualAvgAge);
        verify(studentRepository).getAverageAge();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void getLastFiveStudent_success() {
        //Подготовка входных данных
        Student firstStudent = new Student("Ivan", 35);
        Student secondStudent = new Student("Petr", 29);

        List<Student> students = List.of(firstStudent, secondStudent);

        //Подготовка ожидаемого результата
        when(studentRepository.getLastFiveStudentsById()).thenReturn(students);

        //Начало теста
        List<Student> actualStudents = studentService.getLastFiveStudent();
        assertEquals(students, actualStudents);
        verify(studentRepository).getLastFiveStudentsById();
        verifyNoMoreInteractions(studentRepository);
    }
}
