package org.example.upskillu.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
        00:05:37, 5
        01:08:54.961333, 68
        00:00:00, 0
        """)
    void durationInMinutes(String input, long expected) {
        PluralsightCourse course = new PluralsightCourse("id", "Test course", input, "url", false);
        assertEquals(expected, course.durationInMinutes());
    }

    @Test
    void durationOverHour() {
        PluralsightCourse course = new PluralsightCourse("id", "Test course", "01:05:37", "url", false);
        assertTrue(course.durationInMinutes() > 60);
        assertEquals(65, course.durationInMinutes());
    }


}