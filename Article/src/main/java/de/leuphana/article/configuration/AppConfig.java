package de.leuphana.article.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import jakarta.jms.ConnectionFactory;

@Configuration
public class AppConfig {
}
//	@Bean
//	public SampleListener messageListener() {
//		return new SampleListener();
//	}
//
//	@Bean
//	public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		return factory;
//	}
//
//	@Bean
//	public DefaultMessageListenerContainer jmsContainer() {
//		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory());
//		container.setDestinationName("IN_QUEUE");
//		container.setMessageListener(messageListener());
//		return container;
//	}
//	
//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory);
//        jmsTemplate.setDefaultDestinationName("IN_QUEUE"); // specify the default destination name
//        return jmsTemplate;
//    }
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		return null;
//	}
//
//	}

//	@Bean
//	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		return factory;
//	}
