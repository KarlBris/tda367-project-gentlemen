package network;

import java.util.List;

/**
 * ClientNetworkConnection is a connection that holds a client connection to a
 * server connection
 */
public class ClientNetworkConnection implements INetworkConnection {

	@Override
	public boolean hasNewData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object[]> getDataList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendData(Object[] data) {
		// TODO Auto-generated method stub

	}

}
