package com.platifon.mycards.services.impl;

import com.platifon.mycards.entity.User;
import com.platifon.mycards.model.RestorePasswordRequest;
import com.platifon.mycards.repositories.UserRepository;
import com.platifon.mycards.services.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * @author paradoxfm - 01.02.2016
 */
@Service
@Slf4j
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendRestoreLink(RestorePasswordRequest req) {
        User user = userRepository.findUserByEmail(req.getEmail());
        if (user == null) {
            throw new RuntimeException("user with email " + req.getEmail() + " not found");
        }
        Locale locale = LocaleContextHolder.getLocale();
        final Context ctx = new Context(locale);
        ctx.setVariable("urlRestore", "");
        ctx.setVariable("userName", user.getName());

        sendNoReplyMessage("mail/restorePassword", user.getEmail(), "Info service", ctx);
    }

    private void sendNoReplyMessage(String viewName, String toAddress, String subject, Context ctx) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setFrom("abc@gmail.com");

            final String htmlContent = templateEngine.process(viewName, ctx);
            mimeMessage.setContent(htmlContent, "text/html");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error create helper for MimeMessage", e);
        }
    }
}
