package de.leuphana.shop.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import de.leuphana.shop.component.behaviour.ArticleComplaintMessageConverter;
import jakarta.jms.Message;

@Service
public class ArticleComplaintJmsMessageReceiver {
	@Autowired
	ArticleComplaintMessageConverter converter;

	static int nrMessagesReceived = 0;
	Logger log = LoggerFactory.getLogger(ArticleComplaintJmsMessageReceiver.class);

	@JmsListener(destination = "${jms.queue}")
	public void receiveMessage(String message) {
		log.info("Received message " + message);
		nrMessagesReceived++;
	}
}
