package com.vaccnow.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public void sendMail(String receiver) {
        String sender = "Enter Sender's mail here";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(sender));
            email.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            email.setSubject("Vaccination slot booking confirmation");
            email.setText("Hello, Your vaccination slot is booked, " +
                    "please arrive 10 minutes prior. Thank you.");
            Transport.send(email);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
