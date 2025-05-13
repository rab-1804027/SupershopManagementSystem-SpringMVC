//package com.bappi.supershopmanagementsystem.config;
//
//import jakarta.activation.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = { "classpath:application.properties"})
//public class HibernateConfig {
//
//    private final Environment environment;
//
//    public HibernateConfig(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(environment.getRequiredProperty("db.mysql.driver"));
//        ds.setUrl(environment.getRequiredProperty("db.mysql.url"));
//        ds.setUsername(environment.getRequiredProperty("db.mysql.username"));
//        ds.setPassword(environment.getRequiredProperty("db.mysql.password"));
//        return ds;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan("com.bappi.supershopmanagementsystem.model");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties props = new Properties();
//        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        props.put("hibernate.hbm2ddl.auto", "update");
//        props.put("hibernate.show_sql", "false");
//        emf.setJpaProperties(props);
//
//        return emf;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return txManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//}
//
