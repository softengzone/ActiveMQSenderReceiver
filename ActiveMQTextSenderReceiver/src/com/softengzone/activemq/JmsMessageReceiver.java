package com.softengzone.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class JmsMessageReceiver {
	
	private String brokerUrl;
	
	public JmsMessageReceiver(String brokerUrl) {
		this.brokerUrl = brokerUrl;	
	}
	
	private Connection getConnection() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		return connectionFactory.createConnection();
	}

    public String receive(String queueName) throws JMSException {
    	String receivedMessage = "";
    	
    	// Get JMS connection and start it
		Connection conn = null;
		try {
			conn = getConnection();		
			conn.start();
		
			// Create a session to receive messages
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(queueName);
		
	    	MessageConsumer consumer = session.createConsumer(destination);
	    	Message message = consumer.receive();
	    	
	    	if (message instanceof ActiveMQTextMessage) {
				ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
				try {
					receivedMessage = msg.getText();
				} catch (JMSException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
			
		} finally {
			conn.close();
		}
		return receivedMessage;
		
    }    

}
