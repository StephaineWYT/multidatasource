package wen.multidatasource.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBook",
        transactionManagerRef = "transactionManagerBook",
        basePackages = {"wen.multidatasource.dao.bookjpa"})
public class BookDataSourceConfig {

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    @Qualifier("bookDataSource")
    private DataSource bookDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "entityManagerFactoryBook")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBook(EntityManagerFactoryBuilder builder) {

        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(bookDataSource)
                .properties(properties)
                .packages(new String[]{"wen.multidatasource.domain"})
                .persistenceUnit("bookPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerBook")
    public PlatformTransactionManager transactionManagerBook(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBook(builder).getObject());
    }

    @Primary
    @Bean(name = "entityManagerBook")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryBook(builder).getObject().createEntityManager();
    }
}
