package com.pluralsight.upskillu.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.upskillu.domain.Course;
import org.example.upskillu.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    public String getCourses() {
        List<Course> courses = courseRepository.getAllCourses();
        LOG.info("Found {} courses", courses.size());
        LOG.info("Found courses " + courses);
        return courseRepository
                .getAllCourses()
                .stream()
                .map(Course::toString)
                .collect(Collectors.joining(", "));
    }

}
