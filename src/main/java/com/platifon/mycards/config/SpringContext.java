package com.platifon.mycards.config;

import com.platifon.mycards.utils.GlobalUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * @author paradoxfm - 21.01.2016
 */
@Configuration
@EnableAsync
@ComponentScan({"com.platifon.mycards.services", "com.platifon.mycards.repositories",
        "com.platifon.mycards.controllers", "com.platifon.mycards.components"})
@PropertySource("classpath:application.properties")
public class SpringContext {

    private static final int MESSAGE_CACHE_TIME_YEAR = 31_536_000;

    @Value("${application.mail.host}")
    private String mailHost;
    @Value("${application.mail.port}")
    private int mailPort;
    @Value("${application.mail.user}")
    private String mailUser;
    @Value("${application.mail.password}")
    private String mailPassword;

    @Bean(name = "oauthProp")
    public PropertiesFactoryBean getProperties() {
        PropertiesFactoryBean rez = new PropertiesFactoryBean();
        rez.setLocation(new ClassPathResource("/oauth.properties"));
        return rez;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "localValidator")
    public Validator getLocalValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messages = new ResourceBundleMessageSource();
        messages.setBasenames("i18n/messages", "i18n/validation", "i18n/mail");
        messages.setCacheSeconds(GlobalUtil.isDebug() ? 5 : MESSAGE_CACHE_TIME_YEAR);
        messages.setDefaultEncoding("UTF-8");
        return messages;
    }

    @Bean
    public JavaMailSenderImpl getMailSender() {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");//jndi в tomcat_home/conf/context.xml
            JavaMailSenderImpl re1 = new JavaMailSenderImpl();
            javax.mail.Session session = (javax.mail.Session) envCtx.lookup("mail/Session");
            re1.setSession(session);
            return re1;//(JavaMailSenderImpl) envCtx.lookup("mail/Session");
        } catch (NamingException ignored) {
        }

        JavaMailSenderImpl rez = new JavaMailSenderImpl();
        rez.setHost(mailHost);
        rez.setPort(mailPort);
        rez.setUsername(mailUser);
        rez.setPassword(mailPassword);
        rez.setJavaMailProperties(getMailProperties());
        return rez;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        if (GlobalUtil.isDebug()) {
            properties.put("mail.debug", "true");
        }
        return properties;
    }
}
