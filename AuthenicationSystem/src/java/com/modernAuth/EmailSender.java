package com.modernAuth;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

class EmailSender {

    public boolean sendEmail(String to , String subject , String text){

        boolean flag = false;

        // Logic 
        Properties properties = new Properties();
        properties.put("mail.smtp.auth" , true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        // Now we have to get the session
        
        final String from = "YOUR SENDING EMAIL";
        final String username = "YOUR SENDING EMAIL USERNAME";
        final String password = "APP PASSWORD OF THE EMAIL";

        Session session = Session.getInstance(properties , new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(username , password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            flag = true;
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return flag;

    }
}
