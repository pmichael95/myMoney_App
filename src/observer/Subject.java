package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The subject in the Observer pattern
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @updated 1/29/2018
 */
public class Subject {
	// The list of observers that this subject needs to notify
    private List<Observer> observers = new ArrayList<>();
    
  
    /**
     *  Add an observer to this subject
     * @param o the observer to add to this subject
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     *  Notify all the dependent observers with the data
     * @param data to pass to the observers
     */
    public void notifyObservers(Object data) {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
}