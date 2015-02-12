package br.ifes.leds.config.datasource;

import br.ifes.leds.annotation.Prod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Prod
@Configuration
@PropertySource("classpath:sincap.properties")
public class PgDataSource {

    @Bean
    public String dialect() {
        return "org.hibernate.dialect.PostgreSQL82Dialect";
    }

    @Bean
    public DataSource dataSource(@Value("${dataSource.driver_class}") String driver,
            @Value("${dataSource.url}") String url,
            @Value("${dataSource.username}") String username,
            @Value("${dataSource.password}") String password) {

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
