package by.avectis.contracts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.avectis.contracts")
public class TestControllerConfiguration extends WebMvcConfigurerAdapter {

   }
