package de.leuphana.order.component.behaviour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.order.component.structure.ArticleComplaintRequest;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

@Component
public class ArticleComplaintMessageConverter implements MessageConverter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleComplaintMessageConverter.class);

	ObjectMapper mapper;

	public ArticleComplaintMessageConverter() {
		mapper = new ObjectMapper();
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException {
		String payload = null;
		try {
			payload = mapper.writeValueAsString(object);
			LOGGER.info("outbound json='{}'", payload);
		} catch (JsonProcessingException e) {
			LOGGER.error("error converting form articleComplaint", e);
		}

		TextMessage message = session.createTextMessage();
		message.setText(payload);

		return message;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException {
		TextMessage textMessage = (TextMessage) message;
		String payload = textMessage.getText();
		LOGGER.info("inbound json='{}'", payload);

		ArticleComplaintRequest articleComplaint = null;
		try {
			articleComplaint = mapper.readValue(payload, ArticleComplaintRequest.class);
		} catch (Exception e) {
			LOGGER.error("error converting to articleComplaint", e);
		}

		return articleComplaint;
	}
}
