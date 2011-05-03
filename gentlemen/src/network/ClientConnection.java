package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection implements Runnable {
	private final Socket clientConnection;
	private final Thread clientThread;
	private DataInputStream streamIn;
	private DataOutputStream streamOut;

	public ClientConnection(Socket clientConnection, DataInputStream streamIn,
			DataOutputStream streamOut) {
		this.clientConnection = clientConnection;
		this.streamIn = streamIn;
		this.streamOut = streamOut;

		clientThread = new Thread(this);
		clientThread.start();
	}

	public ClientConnection(Socket clientConnection) {
		streamIn = null;
		streamOut = null;
		try {
			streamIn = new DataInputStream(clientConnection.getInputStream());

			streamOut = new DataOutputStream(clientConnection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error: Unable to get output- or inputstream");
		}
		this.clientConnection = clientConnection;

		clientThread = new Thread(this);
		clientThread.start();
	}

	@Override
	public void run() {
		while (true) {
			// TODO: Data from the network will come here
		}
	}

	/**
	 * Close the connection and streams to the client
	 */
	public void closeConnection() {
		try {
			streamIn.close();
			streamOut.close();
			clientConnection.close();
		} catch (IOException e) {
			System.out.println("Error: Unable to close connection");
		}
	}
}
