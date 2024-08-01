 package org.example.upskillu.cli;

 import org.example.upskillu.cli.service.CourseRetrievalService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        LOG.info("CourseRetriever is starting!");
        if(args.length == 0) {
            LOG.warn("usage: author name");
            return;
        }
        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }

    }


    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for '{}'", authorId);
        var courseRetievalService = new CourseRetrievalService();
        String coursesToStore = courseRetievalService.getCoursesFor(authorId);
        LOG.info("Retrieved the following courses {}, ", coursesToStore);
    }
}
