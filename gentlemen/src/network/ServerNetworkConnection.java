package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.Constants;

public class ServerNetworkConnection implements Runnable, INetworkConnection {
	private final List<ClientConnection> clientConnections;
	private final List<byte[]> incomeData;
	private ServerSocket serverConnection;
	private final Thread serverThread;

	public ServerNetworkConnection() {
		clientConnections = new LinkedList<ClientConnection>();
		incomeData = new LinkedList<byte[]>();
		serverThread = new Thread(this);

		try {
			serverConnection = new ServerSocket(Constants.NETWORK_PORT);
		} catch (IOException e) {
			System.out.println("Error: Unable to create ServerSocket on port "
					+ Constants.NETWORK_PORT);
		}

		serverThread.start();

	}

	private synchronized void addDataToIncomeList(byte[] b) {
		incomeData.add(b);
	}

	@Override
	public boolean hasNewData() {
		return !incomeData.isEmpty();
	}

	@Override
	public synchronized List<byte[]> getDataList() {

		List<byte[]> newData = null;
		Collections.copy(newData, incomeData);
		incomeData.clear();

		return newData;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
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
		DataInputStream streamIn = null;
		DataOutputStream streamOut = null;
		try {
			streamIn = new DataInputStream(clientConnection.getInputStream());

			streamOut = new DataOutputStream(clientConnection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Error: Unable to get output- or inputstream");
		}

		// TODO: Update objects, PlayerNames

		clientConnections.add(new ClientConnection(clientConnection, streamIn,
				streamOut));

	}

}
