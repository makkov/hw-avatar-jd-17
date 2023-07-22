package com.example.hwavatar.controller;

import com.example.hwavatar.model.Faculty;
import com.example.hwavatar.model.FacultyCreationRequest;
import com.example.hwavatar.model.FacultyUpdatingRequest;
import com.example.hwavatar.service.FacultyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty add(@RequestBody FacultyCreationRequest request) {
        return facultyService.add(request.getName(), request.getColor());
    }

    @GetMapping("/getAll")
    public List<Faculty> getAll() {
        return facultyService.getAll();
    }

    @PutMapping
    public Faculty update(@RequestBody FacultyUpdatingRequest request) {
        return facultyService.update(request.getId(), request.getName(), request.getColor());
    }

    @DeleteMapping
    public Faculty delete(long id) {
        return facultyService.delete(id);
    }
}
