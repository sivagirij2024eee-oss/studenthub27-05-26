package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    CourseService courseService;

    @Autowired
    StudentService studentService;

    List<Course> courseList = new ArrayList<>();


    public CourseController(CourseService courseService) {

        this.courseService = courseService;

        courseList.add(new Course("EE101", "Java", 4));
        courseList.add(new Course("EE102", "M&I", 3));
        courseList.add(new Course("EE103", "DBMS", 4));
        courseList.add(new Course("EE104", "ADT", 5));
        courseList.add(new Course("EE105", "MPMC", 3));
        courseList.add(new Course("EE106", "OOPS", 3));
    }

    @GetMapping("get-course")
    public String getCourse() {

        return courseService.getCourse();
    }


    @GetMapping("get-component")
    public String getComponent() {

        return studentService.getComponent();
    }


    @GetMapping("courses")
    public ResponseEntity<List<Course>> getAllCourses() {

        return ResponseEntity.ok(courseList);
    }
@GetMapping ("courses/{courseCode}")
    public ResponseEntity<Course> getCourseByCode(
            @PathVariable String courseCode) {

        for (Course course : courseList) {

            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return ResponseEntity.ok(course);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("course")
    public ResponseEntity<Course> getCourseRequestParam(
            @RequestParam String courseCode) {

        for (Course course : courseList) {

            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return ResponseEntity.ok(course);
            }
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("create-course")
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course) {

        courseList.add(course);

        return ResponseEntity.ok(course);
    }


    @PutMapping("update-course/{courseCode}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String courseCode,
            @RequestBody Course updatedCourse) {

        for (Course course : courseList) {

            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {

                course.setSubjectName(updatedCourse.getSubjectName());
                course.setCredits(updatedCourse.getCredits());

                return ResponseEntity.ok(course);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete-course/{courseCode}")
    public ResponseEntity<String> deleteCourse(
            @PathVariable String courseCode) {

        for (Course course : courseList) {

            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {

                courseList.remove(course);

                return ResponseEntity.ok("Course Deleted Successfully");
            }
        }

        return ResponseEntity.notFound().build();
    }
}