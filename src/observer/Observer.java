package observer;


/**
 * A simple observer interface for the Observer pattern
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @updated 1/29/2018
 */
public interface Observer {
	/**
	 * Update observer data/state
	 * This is automatically called by the subject's notifyObserver(Object data)
	 * 
	 * @param Object data, this is the corresponding data container passed from the subject's notifyObservers(Object data) call
	 */
	void update(Object data);
}
