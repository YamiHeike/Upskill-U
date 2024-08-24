import org.example.upskillu.domain.Course;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    @ParameterizedTest
    @CsvSource(textBlock = """
                23,,
                41, Test2,,
                ,Test3,TestUrl
            """)
    public void lackingData(String id, String name, String url) {
        assertThrows(IllegalArgumentException.class, () ->
            new Course(id, name, 2,url, Optional.empty())
        );
    }
}
