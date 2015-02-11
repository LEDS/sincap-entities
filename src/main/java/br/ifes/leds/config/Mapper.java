package br.ifes.leds.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Mapper {
    
    @Bean
    public org.dozer.Mapper mapperOriginal() {
        List<String> files = new ArrayList<>();
        files.add("dozerBeanMapping.xml");
        
        return new DozerBeanMapper(files);
    }
}
