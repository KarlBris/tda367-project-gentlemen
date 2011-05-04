package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.Constants;

/**
 * ServerNetworkConnection is a connection that holds a server
 */
public class ServerNetworkConnection implements Runnable, INetworkConnection {
	private final List<ClientConnection> clientConnections;
	private final List<Object[]> incomeList;
	private ServerSocket serverConnection;
	private final Thread serverThread;
	private final Thread updateThread;
	private boolean threadRunning = true;

	public ServerNetworkConnection() {
		clientConnections = new LinkedList<ClientConnection>();
		incomeList = new LinkedList<Object[]>();
		serverThread = new Thread(this);

		try {
			serverConnection = new ServerSocket(Constants.NETWORK_PORT);
		} catch (IOException e) {
			System.out.println("Error: Unable to create ServerSocket on port "
					+ Constants.NETWORK_PORT);
		}

		updateThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (threadRunning) {
					updateIncomeList();
				}
			}
		});

		serverThread.start();
		updateThread.start();
	}

	/**
	 * Update the List that contains the data from other players
	 */
	private synchronized void updateIncomeList() {

		for (ClientConnection cc : clientConnections) {
			if (cc.hasNewData()) {
				for (Object[] b : cc.getIncomeList()) {
					sendDataToAllOther(cc, b);

					incomeList.add(b);
				}
			}
		}

	}

	/**
	 * Sends data to all connection but one notTo ClientConnection
	 * 
	 * @param notTo
	 *            is the ClientConnection that not gona get the data
	 * @param data
	 *            is the data that is about to be sent
	 */
	private void sendDataToAllOther(ClientConnection notTo, Object[] data) {
		for (ClientConnection cc : clientConnections) {
			if (!(cc == notTo)) {
				cc.send(data);
			}
		}
	}

	@Override
	public boolean hasNewData() {

		boolean isEmpty = false;

		for (ClientConnection cc : clientConnections) {
			if (!cc.getIncomeList().isEmpty()) {
				isEmpty = true;
				break;
			}
		}

		return isEmpty;
	}

	@Override
	public synchronized List<Object[]> getDataList() {

		List<Object[]> copiedList = null;
		Collections.copy(copiedList, incomeList);
		incomeList.clear();

		return copiedList;

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		for (ClientConnection cc : clientConnections) {
			cc.closeConnection();
		}

		try {
			serverConnection.close();
		} catch (IOException e) {
			System.out.println("Error: Unable to close server socket");
		}

		threadRunning = false;
	}

	@Override
	public void run() {
		while (threadRunning) {
			try {
				Socket clientConnection = serverConnection.accept();

				newClientConnected(clientConnection);

			} catch (IOException e) {
				System.out
						.println("Error: Unable to get a connection from client");
			}

		}
	}

	/**
	 * Get the client up to date about what's existing in the running game and
	 * who's playing in it
	 * 
	 * @param clientConnection
	 *            is the new clients network socket
	 */
	private void newClientConnected(Socket clientConnection) {
		ObjectInputStream streamIn = null;
		ObjectOutputStream streamOut = null;
		try {
			streamIn = new ObjectInputStream(clientConnection.getInputStream());

			streamOut = new ObjectOutputStream(
					clientConnection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error: Unable to get output- or inputstream");
		}

		// TODO: Update objects, PlayerNames

		clientConnections.add(new ClientConnection(clientConnection, streamIn,
				streamOut));

	}

	@Override
	public void sendData(Object[] data) {
		for (ClientConnection cc : clientConnections) {
			cc.send(data);
		}
	}

}
