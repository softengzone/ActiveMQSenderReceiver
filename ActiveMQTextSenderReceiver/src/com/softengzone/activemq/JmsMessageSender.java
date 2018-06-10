package com.softengzone.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsMessageSender {

	private String brokerUrl;
	
	public JmsMessageSender(String brokerUrl) throws JMSException {
		this.brokerUrl = brokerUrl;	
	}
	
	private Connection getConnection() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
		return connectionFactory.createConnection();
	}
	
	public void send(String queueName, String message) throws JMSException {
		// Get JMS connection and start it
		Connection conn = null;
		try {
			conn = getConnection();		
			conn.start();
		
			// Create a session to send and receive messages
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// If the queueName doesn't exist then it's created on the server
			Destination destination = session.createQueue(queueName);
			
			MessageProducer producer = session.createProducer(destination);
			// Prepare and send a text message
			TextMessage txtMessage = session.createTextMessage(message);		
			producer.send(txtMessage);
		
		} finally {
			conn.close();
		}
	}
	
}
