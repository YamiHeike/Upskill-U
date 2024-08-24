package com.pluralsight.upskillu.server;

import org.example.upskillu.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {
    private static final String configFile = "/server.properties";

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    private static final String BASE_URI = loadBaseUri();

    public static void main(String... args) {
        String databaseFilename = loadDatabaseFilename();
        LOG.info("Starting Upskillu HTTP server with database {}", databaseFilename);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(databaseFilename);
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    private static String loadDatabaseFilename() {
        try (InputStream propertiesStream = CourseServer.class.getResourceAsStream(configFile)) {
            Properties props = new Properties();
            props.load(propertiesStream);
            return props.getProperty("upskillu.database");
        } catch(IOException e) {
            throw new IllegalStateException("Failed to load properties file", e);
        }
    }

    private static String loadBaseUri() {
        try(InputStream propertiesStream = CourseServer.class.getResourceAsStream(configFile)) {
            Properties props = new Properties();
            props.load(propertiesStream);
            return props.getProperty("upskillu.base-uri");
        } catch(IOException e) {
            throw new IllegalStateException("Failed to load properties file", e);
        }
    }
}
