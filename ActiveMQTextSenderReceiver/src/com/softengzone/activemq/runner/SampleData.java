package com.softengzone.activemq.runner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class SampleData {
			
	public static String getMessage(int number) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return String.format("["+formatter.format(LocalDateTime.now())+"] Message %d sent.", number);
	}
	
	public static String getQueue() {
		int randomQueueNumber = ThreadLocalRandom.current().nextInt(0, 5);
		return "QUEUE_" + randomQueueNumber;
	}
	
}
