package AIGameEngine.StrategyBT;

import java.util.*;

/**
 * 
 */
public abstract class BTNode {

    /**
     * 
     */
    public BTNode() {
    }

    /**
     * 
     */
    private Condition condition;

    /**
     * 
     */
    private List<BTNode> childNodes;







    /**
     * 
     */
    public abstract void execute();

    /**
     * @param index 
     * @param child
     */
    public void addChild(int index, BTNode child) {
        // TODO implement here
    }

    /**
     * @param index
     */
    public void removeChild(int index) {
        // TODO implement here
    }

    /**
     * @param index 
     * @param child
     */
    public void setChild(int index, BTNode child) {
        // TODO implement here
    }

    /**
     * @param index
     */
    public BTNode getChild(int index) {
        // TODO implement here
    	return null;
    }

}