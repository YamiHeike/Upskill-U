package org.example.upskillu.repository;

import org.example.upskillu.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);
    List<Course> getAllCourses();
}
