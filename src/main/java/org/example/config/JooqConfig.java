package org.example.config;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    private final DataSource dataSource;

    public JooqConfig(@Qualifier("databaseDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public TransactionAwareDataSourceProxy ccDatabaseTransactionAwareDataSource() {
        return new TransactionAwareDataSourceProxy(dataSource);
    }

    @Bean
    public DataSourceTransactionManager ccDatabaseTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceConnectionProvider ccDatabaseConnectionProvider() {
        return new DataSourceConnectionProvider(ccDatabaseTransactionAwareDataSource());
    }

    @Bean
    public DefaultDSLContext ccDatabaseDSL() {
        return new DefaultDSLContext(ccDatabaseConfiguration());
    }

    @Bean
    public DefaultConfiguration ccDatabaseConfiguration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(ccDatabaseConnectionProvider());
        jooqConfiguration.set(SQLDialect.POSTGRES);
        jooqConfiguration.settings().setExecuteLogging(false);

        return jooqConfiguration;
    }
}

