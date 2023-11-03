package de.leuphana.article.connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import de.leuphana.article.component.behaviour.ArticleComplaintMessageConverter;
import de.leuphana.article.component.behaviour.ArticleComponentService;
import de.leuphana.article.component.structure.ArticleComplaintRequest;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Service
public class ArticleJmsReceiver {
	@Autowired
	ArticleComplaintMessageConverter converter; 
	
	@Autowired
	ArticleComponentService service; 
	
	Logger log = LoggerFactory.getLogger(ArticleJmsReceiver.class);

	@JmsListener(destination = "${jms.queue}")
	public void receiveMessage(Message message) throws JMSException {
		log.info("Received message " + message);
		ArticleComplaintRequest request = (ArticleComplaintRequest) converter.fromMessage(message); 
		service.addComplaint(request); 
	}
}