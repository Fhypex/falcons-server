package gtu.cse.se.altefdirt.aymoose.core;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@ComponentScan("gtu.cse.se.altefdirt")
@EnableJpaRepositories(basePackages = "gtu.cse.se.altefdirt")
@EntityScan(basePackages = "gtu.cse.se.altefdirt")
@ConfigurationPropertiesScan("gtu.cse.se.altefdirt")
@RequiredArgsConstructor
public class AymooseApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(AymooseApplication.class);

		// This provides fully qualified class names as bean names, including packages so that we can have 2 beans with the same name in different packages
		// ex: gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.UserOperationsAdapter 
		// ex: gtu.cse.se.altefdirt.aymoose.court.internal.application.port.UserOperationsPort
		app.setBeanNameGenerator(new AnnotationBeanNameGenerator() {
            @Override
            protected String buildDefaultBeanName(BeanDefinition definition) {
                String beanClassName = definition.getBeanClassName();
                Assert.state(beanClassName != null, "No bean class name set");
                return beanClassName;
            }
        });
		app.run(args);
	}
}

