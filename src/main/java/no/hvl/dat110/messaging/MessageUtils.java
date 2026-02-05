package no.hvl.dat110.messaging;

import java.util.Arrays;



public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();

		segment[0] = (byte) data.length;
		for(int i =0; i<data.length; i++){
			segment[i+1] = data[i];
		}
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;

		int length = segment[0] & 0xFF;
		byte[] data = new byte[length];

		for(int i = 0; i<length; i++){
			data[i] = segment[i+1];
		}
		message = new Message(data);
		
		return message;
		
	}
	
}
