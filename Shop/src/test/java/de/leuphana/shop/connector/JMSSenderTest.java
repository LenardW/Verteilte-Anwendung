package de.leuphana.shop.connector;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.leuphana.shop.component.structure.sales.ArticleComplaintRequest;

@SpringBootTest
class JMSSenderTest {

	@Autowired
	ArticleComplaintJmsMessageSender sender; 
	
	@Autowired
	ArticleComplaintJmsMessageReceiver receiver; 
	
	
	@Test
	void test() {
		ArticleComplaintRequest message = new ArticleComplaintRequest(); 
    	message.setArticleId(1);
    	message.setOrderId(2); 
    	message.setReason("Defect"); 
        sender.sendMessage(message);
		assertThat(receiver.nrMessagesReceived).isEqualTo(0);
		
	}

}
