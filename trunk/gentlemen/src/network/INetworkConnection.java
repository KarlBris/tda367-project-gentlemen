package network;

import java.util.List;

public interface INetworkConnection {
	/**
	 * Determines if there is any new data from other players
	 * 
	 * @return true if there is new data, false if there has not
	 */
	public boolean hasNewData();

	/**
	 * @return the list containing the income data
	 */
	public List<byte[]> getDataList();

	/**
	 * Close the network connection
	 */
	public void closeConnection();

}
