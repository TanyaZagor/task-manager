package ru.zagorodnikova.tm.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("ru.zagorodnikova.tm")
@EnableWebMvc
public class WebAppConfig {

}
