package sg.edu.nus.comp.cs3219.viz.service.email;

import com.sendgrid.helpers.mail.Mail;

import java.util.HashMap;

public interface EmailBuilder {

    public EmailBuilder from(String fromEmailAddress);
    public EmailBuilder replyTo(String fromName);
    public EmailBuilder to(String toEmailAddress);
    public EmailBuilder addPersonalization(HashMap<String,String> dynamicTemplateData);
    public Mail build();
}
