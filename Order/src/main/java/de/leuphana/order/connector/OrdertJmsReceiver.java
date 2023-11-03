package de.leuphana.order.connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import de.leuphana.order.component.behaviour.ArticleComplaintMessageConverter;
import de.leuphana.order.component.behaviour.OrderComponentService;
import de.leuphana.order.component.structure.ArticleComplaintRequest;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Service
public class OrdertJmsReceiver {
	
	@Autowired
	ArticleComplaintMessageConverter converter; 
	
	@Autowired
	OrderComponentService service; 
	
	Logger log = LoggerFactory.getLogger(OrdertJmsReceiver.class);

	@JmsListener(destination = "${jms.queue}")
	public void receiveMessage(Message message) throws JMSException {
		log.info("Received message " + message);
		ArticleComplaintRequest complaint = (ArticleComplaintRequest) converter.fromMessage(message); 
		log.info("Add complaint " + complaint.getReason());
		service.addComplaint(complaint); 
	}
}