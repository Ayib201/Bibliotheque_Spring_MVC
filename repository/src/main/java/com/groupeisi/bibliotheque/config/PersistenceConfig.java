package com.groupeisi.bibliotheque.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration Spring de la couche de persistance : DataSource, SessionFactory Hibernate
 * et TransactionManager. Extraite de l'ancienne "SpringWebConfig" (qui mélangeait
 * persistance ET web) pour vivre ici, dans le module repository.
 * <p>
 * Pourquoi ce découpage ? Séparation des responsabilités (Single Responsibility) : ce
 * module est le seul à savoir COMMENT on se connecte à la base et comment Hibernate est
 * configuré. Le module ihm-jsp (étape 5) n'aura plus qu'à afficher les pages web, sans se
 * soucier de la persistance. Cette classe est un simple bean Spring ({@code @Configuration}
 * hérite de {@code @Component}) : elle sera détectée automatiquement par le
 * {@code @ComponentScan("com.groupeisi.bibliotheque")} déclaré dans ihm-jsp, tant que ce
 * module se trouve dans son classpath (ce qui sera le cas via metier -> repository).
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PersistenceConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.groupeisi.bibliotheque.entities");

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");

        factory.setHibernateProperties(properties);
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
