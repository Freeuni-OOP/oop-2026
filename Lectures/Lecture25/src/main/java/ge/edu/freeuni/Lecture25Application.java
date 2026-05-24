package ge.edu.freeuni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * FEATURE: @SpringBootApplication
 * Combines three annotations:
 *   @Configuration         – marks this as a configuration class
 *   @EnableAutoConfiguration – auto-configures Spring based on classpath
 *   @ComponentScan         – scans this package for @Component, @Service, etc.
 *
 * FEATURE: @ConfigurationPropertiesScan
 * Activates all @ConfigurationProperties beans (AppProperties).
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class Lecture25Application {

    public static void main(String[] args) {
        SpringApplication.run(Lecture25Application.class, args);
    }
}
