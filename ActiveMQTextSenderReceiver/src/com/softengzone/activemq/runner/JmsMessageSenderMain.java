package com.softengzone.activemq.runner;

import javax.jms.JMSException;

import com.softengzone.activemq.JmsMessageSender;

public class JmsMessageSenderMain {

	public static void main(String[] args) {

		String brokerUrl = "tcp://192.168.1.8:61616";		
		try {
			JmsMessageSender sender = new JmsMessageSender(brokerUrl);	
			for (int i=0; i < 20; i++) {
				sender.send(SampleData.getQueue(), SampleData.getMessage(i));
			}
	
		} catch (JMSException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
}
