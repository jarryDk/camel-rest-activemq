package dk.jarry.wildfly.camel.activemq.todo.control;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.Uri;

import dk.jarry.wildfly.camel.activemq.todo.entity.ToDo;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ToDo"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class ToDoMDB implements MessageListener {

	@Inject
	@Uri("direct:jmsIn")
	private ProducerTemplate template;

	@Override
	public void onMessage(Message message) {
		
		try {
			String messagePayload = "";
			if (message instanceof TextMessage) {
				messagePayload = ((TextMessage) message).getText();
			} else {
				messagePayload = message.toString();
			}
			
			ToDo todo = ToDo.fromJson(messagePayload);
			
			template.sendBody(todo);
		} catch (JMSException e) {
			throw new IllegalStateException("Error processing JMS message", e);
		}
	}
}