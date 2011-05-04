package network;

import java.util.List;

/**
 * INetworkConnection is an interface used to set a connection over the network
 */
public interface INetworkConnection {

	/**
	 * @param data
	 *            is the data to send to other players
	 */
	public void sendData(Object[] data);

	/**
	 * Determines if there is any new data from other players
	 * 
	 * @return true if there is new data, false if there has not
	 */
	public boolean hasNewData();

	/**
	 * @return the list containing the income data
	 */
	public List<Object[]> getDataList();

	/**
	 * Close the network connection
	 */
	public void closeConnection();

}
