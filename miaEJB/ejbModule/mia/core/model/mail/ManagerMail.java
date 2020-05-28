package mia.core.model.mail;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import mia.core.model.util.ModelUtil;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ManagerMail {
	@PersistenceContext(unitName = "miaDS")
	private EntityManager em;
    public ManagerMail() {

    }
    @SuppressWarnings("unused")
    
	public boolean enviarMensajesElectronicos(String titulo,String asunto,String destinatario) throws Exception {
    	if(ModelUtil.isEmpty(titulo)) {
    		throw new Exception("Tìtulo del mensaje vacío");
    	}
    	if(ModelUtil.isEmpty(asunto)) {
    		throw new Exception("Asunto del mensaje vacío");
    	}
    	if(ModelUtil.isEmpty(destinatario)) {
    		throw new Exception("Destinatario del mensaje vacío");
    	}
    	
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("miainstituteacademic@gmail.com", "dosmenosuno0");
          }
          });
        try {
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("miainstituteacademic@gmail.com"));
          message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(destinatario));
          message.setSubject(titulo);
          message.setText(asunto);
          Transport.send(message);
        } catch (MessagingException e) {
         System.out.println(e.getMessage());  
        }
      
    	
    	return true;
    }

}
