package de.leuphana.order.connector;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

public class JmsMessageConverter implements MessageConverter {

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

}
