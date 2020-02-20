package com.samirovic.restfulwebservices;

import com.samirovic.restfulwebservices.model.User;
import com.samirovic.restfulwebservices.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDate;
import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication implements CommandLineRunner {

    @Autowired
    UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Override
    public void run(String... args) throws Exception {

        // userDao.save(new User( "Samir", LocalDate.of(2010, 4,26)));
        // userDao.save(new User( "Amine", LocalDate.of(2010, 6,14)));
        // userDao.save(new User( "Zineb", LocalDate.of(2010, 9,2)));
    }
}
