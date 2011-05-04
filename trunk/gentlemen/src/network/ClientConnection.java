package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

/**
 * ClientConnection is a connection to a server with the means to communicate
 * with the server
 */
public class ClientConnection implements Runnable {
	private final Socket clientConnection;
	private final Thread clientThread;
	private ObjectInputStream streamIn;
	private ObjectOutputStream streamOut;
	private List<Object[]> incomeList;
	private boolean threadRunning = true;

	public ClientConnection(Socket clientConnection,
			ObjectInputStream streamIn, ObjectOutputStream streamOut) {
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
			streamIn = new ObjectInputStream(clientConnection.getInputStream());

			streamOut = new ObjectOutputStream(
					clientConnection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error: Unable to get output- or inputstream");
		}
		this.clientConnection = clientConnection;

		clientThread = new Thread(this);
		clientThread.start();
	}

	@Override
	public void run() {
		while (threadRunning) {
			try {
				addToIncomeList((Object[]) streamIn.readObject());

			} catch (IOException e) {
				System.out.println("Error: IOException reading from streamIn");
			} catch (ClassNotFoundException e) {
				System.out
						.println("Error: ClassNotFoundException reading from streamIn");
			}

		}
	}

	/**
	 * Determines if there is any new data from this socket
	 * 
	 * @return true if there is new data, false if there has not
	 */
	public boolean hasNewData() {
		return !incomeList.isEmpty();
	}

	/**
	 * Add to the income list
	 * 
	 * @param b
	 *            will be added to the income list
	 */
	private synchronized void addToIncomeList(Object[] b) {
		incomeList.add(b);
	}

	/**
	 * @return the list containing the income data
	 */
	public synchronized List<Object[]> getIncomeList() {
		List<Object[]> copiedList = null;
		Collections.copy(copiedList, incomeList);
		incomeList.clear();

		return copiedList;
	}

	public void send(Object[] data) {

	}

	/**
	 * Close the connection and streams to the client
	 */
	public void closeConnection() {
		try {
			threadRunning = false;

			streamIn.close();
			streamOut.close();
			clientConnection.close();
		} catch (IOException e) {
			System.out.println("Error: Unable to close connection");
		}
	}
}
