package AIGameEngine.StrategyBT;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import AIGameEngine.Object;

/**
 * Ёлемент дерева поведени€. Ќе может иметь дочерних вершин. —одержит в себе
 * список действий, которые примен€ютс€ к объекту.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public class BTNodeLeaf extends BTNode {

	/**
	 *  онструктор инициализирует условие ссылкой на условие.
	 * 
	 * @param condition
	 *            ссылка на некоторое условие
	 */
	public BTNodeLeaf(Function<Object, Boolean> condition) {
		super(condition);
	}

	/**
	 * —писок действий дл€ владельца стратегии.
	 */
	private List<Consumer<Object>> actions;

	/**
	 * ƒобавл€ет действие в список по индексу.
	 * 
	 * @param index
	 *            индекс действи€
	 * @param action
	 *            действие
	 */
	public void addAction(int index, Consumer<Object> action) {
		this.actions.add(index, action);
	}

	/**
	 * ”дал€ет действие из списка действий по индексу
	 * 
	 * @param index
	 *            индекс удал€емого действи€
	 */
	public void removeAction(int index) {
		this.actions.remove(index);
	}

	/**
	 * ”станавливает новое действие на позицию по номеру.
	 * 
	 * @param index
	 *            номер нового действи€
	 * @param action
	 *            новое действие
	 */
	public void setAction(int index, Consumer<Object> action) {
		this.actions.set(index, action);
	}

	/**
	 * ¬озвращает действие по индексу.
	 * 
	 * @param index
	 *            индекс дейтсви€
	 * @return действие
	 */
	public Consumer<Object> getAction(int index) {
		return this.actions.get(index);
	}

	/**
	 * ¬ыполн€ет по пор€дку все действи€, если условие вершины истинно.
	 * 
	 * @param object
	 *            владелец стратегии
	 * @return {@code true} если условие истино
	 */
	@Override
	public boolean execute(Object object) {
		if (!this.getConditionResult(object))
			return false;
		for (Consumer<Object> p : actions)
			p.accept(object);
		return true;
	}

	/**
	 * Ќе используема€ функци€, так как не может иметь дочерние вершины.
	 */
	@Override
	public void addChild(int index, BTNode child) {
	}

	/**
	 * Ќе используема€ функци€, так как не может иметь дочерние вершины.
	 */
	@Override
	public void removeChild(int index) {
	}

	/**
	 * Ќе используема€ функци€, так как не может иметь дочерние вершины.
	 */
	@Override
	public void setChild(int index, BTNode child) {
	}

	/**
	 * Ќе используема€ функци€, так как не может иметь дочерние вершины.
	 */
	@Override
	public BTNode getChild(int index) {
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Ќе используема€ функци€, так как не может иметь дочерние вершины.
	 */
	@Override
	public List<BTNode> getChildNodes() {
		return new LinkedList<BTNode>();
	}

}