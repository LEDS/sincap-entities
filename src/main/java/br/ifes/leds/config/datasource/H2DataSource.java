package br.ifes.leds.config.datasource;

import br.ifes.leds.annotation.Dev;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@Dev
public class H2DataSource {

    @Bean
    public String dialect() {
        return "org.hibernate.dialect.H2Dialect";
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder.setType(H2).build();
    }
}
