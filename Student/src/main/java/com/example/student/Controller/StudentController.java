package com.example.student.Controller;


import com.example.student.Api.Api;
import com.example.student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/student")
public class StudentController {

    private ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/add")
    public Api addStudent(@RequestBody Student student) {
        students.add(student);
        return new Api("Student added successfully");
    }

    @GetMapping("/get")
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    @PutMapping("/update/{id}")
    public Api updateStudent(@PathVariable String id, @RequestBody Student s1) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(s1.getName());
                student.setAge(s1.getAge());
                student.setDegree(s1.getDegree());
                student.setGpa(s1.getGpa());
                return new Api("Student updated successfully");

            }
        }
        return new Api("Student not found");

    }

    @DeleteMapping("/delet/{id}")
    public Api deleteStudent(@PathVariable String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return new Api("Student deleted successfully");
            }
        }
        return new Api("Student not found");
    }
    @GetMapping("/classifyStudents")
    public ArrayList<String> classifyStudents() {
        ArrayList<String> classify = new ArrayList<>();
        for (Student student : students) {
            String category;
            double gpa = student.getGpa();

            if (gpa >= 4.5) {
                category = "High Honors";
            } else if (gpa >= 4.0) {
                category = "Honors";
            } else if (gpa >= 3.5) {
                category = "Merit";
            } else {
                category = "normal";
            }
            classify.add(student.getName() + " = " + category);
        }
        return classify;
    }


    @GetMapping("/average")
    public ArrayList<Student> getAverage() {
        ArrayList<Student> average = new ArrayList<>();
        if (students.isEmpty()) return average;

        double total = 0;
        for (Student student : students) {
            total += student.getGpa();
        }
        double avg = total / students.size();

        for (Student student : students) {
            if (student.getGpa() > avg) {
                average.add(student);

            }

        }
        return average;

    }

}

