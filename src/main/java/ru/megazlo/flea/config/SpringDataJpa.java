package ru.megazlo.flea.config;

import ru.megazlo.flea.utils.GlobalUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author paradoxfm - 21.01.2016
 */
@Configuration
@EnableJpaRepositories("ru.megazlo.flea.repositories")
@EnableJpaAuditing
@EnableTransactionManagement
@ComponentScan({"ru.megazlo.flea.entity"})
@Slf4j
public class SpringDataJpa {

    @Value("${application.use.embedded}")
    private boolean useEmbedded;

    @Bean
    public DataSource dataSource() {
        if (useEmbedded) {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
        }
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");//jndi в tomcat_home/conf/context.xml
            return  (DataSource) envCtx.lookup("jdbc/mycards");
        } catch (NamingException ignored) {
        }
        return null;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("ru.megazlo.flea.entity");
        entityManagerFactoryBean.setJpaProperties(getProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        if (GlobalUtil.isDebug()) {
            log.debug("hibernate in debug mode properties configure");
            properties.put("hibernate.show_sql", "true");
            //properties.put("hibernate.generate_statistics", "true");
        }
        return properties;
    }
}
