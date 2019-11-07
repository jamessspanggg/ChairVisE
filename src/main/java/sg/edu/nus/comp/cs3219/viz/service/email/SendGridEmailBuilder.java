package sg.edu.nus.comp.cs3219.viz.service.email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.util.HashMap;


public class SendGridEmailBuilder implements EmailBuilder {

    private Email fromEmail;
    private Email toEmail;
    private Personalization personalization;
    private String templateId;

    public SendGridEmailBuilder(String templateId) {
        this.fromEmail = new Email();
        this.toEmail = new Email();
        this.personalization = new Personalization();
        this.templateId = templateId;
    }

    @Override
    public EmailBuilder from(String fromEmailAddress) {
        if (!fromEmailAddress.isEmpty()) {
            fromEmail.setEmail(fromEmailAddress);
        }
        return this;
    }

    @Override
    public EmailBuilder replyTo(String fromName) {
        if (!fromName.isEmpty()) {
            fromEmail.setName(fromName);
        }
        return this;
    }

    @Override
    public EmailBuilder to(String toEmailAddress) {
        if (!toEmailAddress.isEmpty()) {
            toEmail.setEmail(toEmailAddress);
            personalization.addTo(toEmail);
        }
        return this;
    }

    @Override
    public EmailBuilder addPersonalization(HashMap<String, String> dynamicTemplateData) {
        if (!dynamicTemplateData.isEmpty()) {
            dynamicTemplateData.forEach(personalization::addDynamicTemplateData);
        }
        return this;
    }

    @Override
    public Mail build() {
        Mail mail = new Mail();
        mail.setFrom(fromEmail);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);
        return mail;
    }
}
