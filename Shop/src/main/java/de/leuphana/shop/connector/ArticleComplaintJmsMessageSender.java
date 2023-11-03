package de.leuphana.shop.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Queue;

@Service
public class ArticleComplaintJmsMessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${jms.queue}")
	String jmsQueue;

	public void simpleSend() {

		jmsTemplate.send(jmsQueue, s -> s.createTextMessage("hello queue world"));
	}

	public void sendMessage(Object message) {
		jmsTemplate.convertAndSend(jmsQueue, message);
	}
}