/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailMaster;

import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bryan e Isaac
 */
@Named
@RequestScoped
public class Mail {

    private String to;
    private String from;
    private String subject;
    private String descrp;
    private String username;
    private String password;
    private String smtp;
    private int port;

    public Mail() {
        this.to = "";
        this.from = "YammieAlimentos@gmail.com";
        this.subject = "";
        this.descrp = "";
        this.username = "YammieAlimentos@gmail.com";
        this.password = "yammieSIYF";
        this.smtp = "smtp.gmail.com";
        this.port = 587;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void sendEmail() throws Exception {
        Properties prop = null;
        Session session = null;
        MimeMessage message = null;

        prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", smtp);
        prop.put("mail.smtp.host", smtp);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.clave", password);

        session = Session.getInstance(prop);

        message = new MimeMessage(session);
        try {
            message.setSubject(subject);
            message.setText(descrp);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp, this.port, this.username, this.password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new Exception("Al parecer hubo un error al enviar el correo.");
        }
    }

}
