package sg.edu.nus.comp.cs3219.viz.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sg.edu.nus.comp.cs3219.viz.common.datatransfer.AccessLevel;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    SendGrid sendGrid;

    @Value("${app.sendgrid.templateId}")
    private String templateId;

    public void sendEmail(String toEmailAddress, String fromEmailAddress,
                          String fromName, String shareableUrl, AccessLevel accessLevel, String presentationName) {

        try {
            Mail mail = prepareEmail(toEmailAddress, fromEmailAddress, fromName, shareableUrl, accessLevel, presentationName);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Mail prepareEmail(String toEmailAddress, String fromEmailAddress,
                              String fromName, String shareableUrl, AccessLevel accessLevel, String presentationName) {
        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail(fromEmailAddress);
        fromEmail.setName(fromName);

        Email toEmail = new Email();
        toEmail.setEmail(toEmailAddress);

        Personalization personalization = new Personalization();
        personalization.addTo(toEmail);
        personalization.addDynamicTemplateData("email", fromEmailAddress);
        personalization.addDynamicTemplateData("url", shareableUrl);
        personalization.addDynamicTemplateData("access_level", renameAccessLevel(accessLevel));
        personalization.addDynamicTemplateData("presentation_name", presentationName);

        mail.setFrom(fromEmail);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);
        return mail;
    }

    private String renameAccessLevel(AccessLevel accessLevel) {
        switch (accessLevel) {
            case CAN_READ:
                return "view";

            case CAN_WRITE:
                return "edit";

            default:
                return "view";

        }
    }
}
