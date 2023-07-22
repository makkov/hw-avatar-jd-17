package com.example.hwavatar.repository;

import com.example.hwavatar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    в учебных целях использовали разные способы выборки данных
    count - использовали стандарнтый из CrudRepository
    getAverageAge - jpql
    getLastFiveStudentsById - native sql
* */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select avg(s.age) " +
            "from Student s")
    double getAverageAge();

    @Query(value = "SELECT * " +
            "FROM student " +
            "ORDER BY id DESC " +
            "LIMIT 5",
            nativeQuery = true)
    List<Student> getLastFiveStudentsById();
}
