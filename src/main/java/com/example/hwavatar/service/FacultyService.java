package com.example.hwavatar.service;

import com.example.hwavatar.model.Faculty;
import com.example.hwavatar.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(String name, String color) {
        Faculty newFaculty = new Faculty(name, color);
        newFaculty = facultyRepository.save(newFaculty);
        return newFaculty;
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll()
                .stream()
                .sorted((x, y) -> x.getColor().compareTo(y.getColor()))
                .collect(Collectors.toList());
    }

    public Faculty update(long id, String name, String color) {
        Optional<Faculty> facultyForUpdateOpt = facultyRepository.findById(id);

        if (facultyForUpdateOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }

        Faculty facultyForUpdate = facultyForUpdateOpt.get();
        facultyForUpdate.setName(name);
        facultyForUpdate.setColor(color);

        facultyRepository.save(facultyForUpdate);
        return facultyForUpdate;
    }

    public Faculty delete(long id) {
        Optional<Faculty> facultyForDeleteOpt = facultyRepository.findById(id);

        if (facultyForDeleteOpt.isEmpty()) {
            throw new RuntimeException("Факультет c id " + id + " не найден");
        }

        Faculty facultyForDelete = facultyForDeleteOpt.get();

        facultyRepository.delete(facultyForDelete);
        return facultyForDelete;
    }

    public String getLongestName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .get();
    }

    public Integer sum() {
        long start = System.currentTimeMillis();
        int res = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long finish = System.currentTimeMillis();
        long dif = finish - start;
        System.out.println("simple: " + dif);
        return res;
    }

    public Integer sumImpr() {
        long start = System.currentTimeMillis();
        int res = Stream.iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long finish = System.currentTimeMillis();
        long dif = finish - start;
        System.out.println("impr: " + dif);
        return res;
    }
}
