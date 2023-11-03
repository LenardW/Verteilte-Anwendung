package de.leuphana.article.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Queue;

@Service
public class ArticleJmsMessageSender {

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${jms.queue}")
	String jmsQueue;

	public void simpleSend() {

		jmsTemplate.send(jmsQueue, s -> s.createTextMessage("hello queue world"));
	}

	public void sendMessage(String message) {
		jmsTemplate.convertAndSend(jmsQueue, message);

//		        Map<String, Object> map = new HashMap<>(); 
//		        map.put("id", message.getArticleId()); map.put("reason", message.getReason()); 
//		        jmsTemplate.convertAndSend(queue, map); 
	}
}