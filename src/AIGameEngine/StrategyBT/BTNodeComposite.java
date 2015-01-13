package AIGameEngine.StrategyBT;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import AIGameEngine.Object;

/**
 * —оставной элемент дерева поведени€.
 * @author Alexey Nikitin
 * @version 0.1
 */
public abstract class BTNodeComposite extends BTNode {

	/**
	 *  онструктор инициализирует условие ссылкой на условие и список дочерних вершин. 
	 * @param condition ссылка на некоторое условие
	 */
	public BTNodeComposite(Function<Object, Boolean> condition) {
		super(condition);
		this.childNodes = new LinkedList<BTNode>();
	}

	/**
     * —писок дочерних вершин.
     */
	private List<BTNode> childNodes;

	@Override
	public void addChild(int index, BTNode child) {
		childNodes.add(index, child);
	}

	@Override
	public void removeChild(int index) {
		childNodes.remove(index);
	}

	@Override
	public void setChild(int index, BTNode child) {
		childNodes.set(index, child);
	}

	@Override
	public BTNode getChild(int index) {
		return childNodes.get(index);
	}
	
	@Override
	public List<BTNode> getChildNodes() {
		return childNodes;
	}

}
