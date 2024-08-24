package org.example.upskillu.cli.service;

import org.example.upskillu.domain.Course;
import org.example.upskillu.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private final CourseRepository courseRepository;
    private static final String PLURALSIGHT_BASE_URL = "https://app.pluralsight.com";

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> pluralsightCourses) {
     for (PluralsightCourse pluralsightCourse: pluralsightCourses) {
         Course course = new Course(pluralsightCourse.id(),
                 pluralsightCourse.title(),
                 pluralsightCourse.durationInMinutes(),
                 PLURALSIGHT_BASE_URL + pluralsightCourse.contentUrl(), Optional.empty());
         courseRepository.saveCourse(course);
     }
    }
}
