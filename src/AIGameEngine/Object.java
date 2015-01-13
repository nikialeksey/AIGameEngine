package AIGameEngine;

import java.util.*;

/**
 * 
 */
public abstract class Object {

    /**
     * 
     */
    public Object() {
    }

    /**
     * 
     */
    private Object parentObject;

    /**
     * 
     */
    private Strategy strategy;






    /**
     * @param object
     */
    public abstract void addObject(Object object);

    /**
     * @param object
     */
    public abstract void removeObject(Object object);

    /**
     * @param object
     */
    public abstract void containsObject(Object object);

    /**
     * 
     */
    public void getParentObject() {
        // TODO implement here
    }

    /**
     * @param parentObject
     */
    public void setParentObject(Object parentObject) {
        // TODO implement here
    }

    /**
     * 
     */
    public abstract void getChildObjects();

    /**
     * @param actionEvent
     */
    public abstract void action(ActionEvent actionEvent);

    /**
     * 
     */
    public void getStrategy() {
        // TODO implement here
    }

    /**
     * @param strategy
     */
    public void setStrategy(Strategy strategy) {
        // TODO implement here
    }

    /**
     * @param conditionEvent
     */
    public void getConditionResult(ConditionEvent conditionEvent) {
        // TODO implement here
    }

}