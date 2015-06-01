/*
 * Copyright 2010,2014 EUROPEAN UNION
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by
 * the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * Date: --/--/2014
 * Authors: European Commission, Joint Research Centre
 * Emanuela Epure
 * inspire-registry-info@jrc.ec.europa.eu
 *
 * This work was supported by the EU  Interoperability Solutions for
 * European Public Administrations Programme (http://ec.europa.eu/isa)
 * through Action 1.17: Re-usable INSPIRE Reference Platform 
 */
package eu.europa.ec.util;

import eu.europa.ec.aqrmodeluser.Users;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

    public Mail() {
    }

    public void sendbyMail(String subject, String body, Users user) throws ServletException, IOException {

        try {
            //Mail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "mrelayv.jrc.it");
            properties.put("mail.smtp.port", "25");
            properties.put("mail.smtp.auth", "false");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.user", "");
            properties.put("mail.password", "");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("", "");
                }
            };
            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("aq-dev@jrc.ec.europa.eu"));
            
            String to = (user.getEmail() == null || user.getEmail().length() <= 0) ? "emanuela.epure@ext.jrc.ec.europa.eu" : user.getEmail();
            InternetAddress[] toAddresses = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            String title = "Mail title";
            String footer = "AQR";
            messageBodyPart.setContent(title + body + footer, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // sets the multi-part as e-mail's content
            msg.setContent(multipart);

            // sends the e-mail
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
