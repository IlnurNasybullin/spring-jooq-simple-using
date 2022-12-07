package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "hikaricp.database.config")
public class DataSourceConfig extends HikariConfig {
    @Bean
    @Primary
    public DataSource databaseDataSource() {
        return new HikariDataSource(this);
    }
}
