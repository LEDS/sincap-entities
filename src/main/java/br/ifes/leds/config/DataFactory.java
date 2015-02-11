package br.ifes.leds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFactory {
    
    @Bean
    public org.fluttercode.datafactory.impl.DataFactory dataFactoryOriginal() {
        return new org.fluttercode.datafactory.impl.DataFactory();
    }
}
