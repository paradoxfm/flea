package ru.megazlo.flea.controllers;

import ru.megazlo.flea.services.ICardService;
import ru.megazlo.flea.services.IEmailService;
import ru.megazlo.flea.services.IUserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author paradoxfm - 17.02.2016
 */
@Configuration
@EnableWebMvc
@ComponentScan({"ru.megazlo.flea.controllers"})
public class SpringTestContext extends WebMvcConfigurerAdapter {

    private static final int MESSAGE_CACHE_TIME = 31_536_000;

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messages = new ResourceBundleMessageSource();
        messages.setBasenames("i18n/messages", "i18n/validation");
        messages.setCacheSeconds(MESSAGE_CACHE_TIME);
        messages.setDefaultEncoding("UTF-8");
        return messages;
    }

    @Bean(name = "oauthProp")
    public PropertiesFactoryBean getProperties() {
        PropertiesFactoryBean rez = new PropertiesFactoryBean();
        rez.setLocation(new ClassPathResource("/oauth.properties"));
        return rez;
    }


    @Bean
    public LocalValidatorFactoryBean getLocalValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

    @Bean
    public ViewResolver getViewResolver() {//делаем заглушку для тестов
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public Validator getValidator() {
        return getLocalValidator(messageSource());
    }

    @Bean
    public ICardService getCardService() {
        return Mockito.mock(ICardService.class);
    }

    @Bean
    public IUserService getUserService() {
        return Mockito.mock(IUserService.class);
    }

    @Bean
    public IEmailService getEmailService() {
        return Mockito.mock(IEmailService.class);
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return Mockito.mock(UserDetailsService.class);
    }
}
