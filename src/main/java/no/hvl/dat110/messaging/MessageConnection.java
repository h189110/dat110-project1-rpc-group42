package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data = MessageUtils.encapsulate(message);
		
		try {
			outStream.write(data);
			outStream.flush();
		} catch (IOException e) {
            System.out.println("outStream failed" + e.getMessage());
        }

    }

	public Message receive() {

		Message message = null;
		byte[] data = new byte[MessageUtils.SEGMENTSIZE];
		
		try {
			int read = inStream.read(data, 0, MessageUtils.SEGMENTSIZE);

			message = MessageUtils.decapsulate(data);

		} catch (IOException e) {
            System.out.println("inStream failure" + e.getMessage()); //størrelse større enn 128 probably
        }

        return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}