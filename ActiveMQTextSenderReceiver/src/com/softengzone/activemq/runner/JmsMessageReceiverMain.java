package com.softengzone.activemq.runner;

import javax.jms.JMSException;

import com.softengzone.activemq.JmsMessageReceiver;

public class JmsMessageReceiverMain {

	public static void main(String[] args) {
		String brokerUrl = "tcp://192.168.1.8:61616";			
		
		try {
			JmsMessageReceiver receiver = new JmsMessageReceiver(brokerUrl);
			
			for (int i=0; i < 20; i++) {
				System.out.println(receiver.receive(SampleData.getQueue()));
			}
			
		} catch (JMSException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
