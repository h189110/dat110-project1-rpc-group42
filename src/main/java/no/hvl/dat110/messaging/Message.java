package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {


	// the up to 127 bytes of data (payload) that a message can hold
	private final byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) throws IllegalArgumentException {
		

		
		if (data == null)
			throw new IllegalArgumentException("data kan ikke være null");

		if(data.length > 127)
			throw new IllegalArgumentException("data kan ikke være lengre en 127");


		this.data = data;
	}

	public byte[] getData() {
		return this.data; 
	}

}
