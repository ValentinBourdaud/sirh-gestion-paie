package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
@Import(JpaConfig.class)
public class ServicesConfig {

}
