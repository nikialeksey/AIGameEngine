package AIGameEngine.StrategyBT;

import java.util.List;
import java.util.function.Function;

import AIGameEngine.Object;
/**
 * Элемент дерева поведения. Необходим для создания самого дерева поведения.
 * Каждый такой элемент содержит условие выполения этого элемента.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 * @see StrategyBT
 */
public abstract class BTNode {

	/**
	 * Конструктор. Инициализирует ссылкой на функцию функцию условия, и
	 * инициализирует список дочерних вершин.
	 */
	public BTNode(Function<Object, Boolean> condition) {
		this.condition = condition;
	}

	/**
	 * Функция условия этого элемента.
	 */
	private Function<Object, Boolean> condition;

	/**
	 * Проверяет условие и, возможно, исполняет дочернии элементы.
	 * 
	 * @param object
	 *            объект стратегии
	 * @return {@code true} если функция условия, примененная к объекту истинна
	 */
	public abstract boolean execute(Object object);

	/**
	 * Добавляет дочернюю вершину по порядковому номеру index.
	 * 
	 * @param index
	 *            порядковый номер вершины
	 * @param child
	 *            новая вершина
	 */
	public abstract void addChild(int index, BTNode child);

	/**
	 * Удаляет дочерний элемент по номеру.
	 * 
	 * @param index
	 *            индекс удаляемого элемента
	 */
	public abstract void removeChild(int index);

	/**
	 * Заменяет дочерний элемент по номеру новым элементом.
	 * 
	 * @param index
	 *            номер дочернего элемента
	 * @param child
	 *            новое значение дочернего элемента
	 */
	public abstract void setChild(int index, BTNode child);

	/**
	 * Возвращает дочерний элемент по номеру.
	 * 
	 * @param index
	 *            номер дочернего элемента
	 * @return дочерний элемент дерева поведения
	 */
	public abstract BTNode getChild(int index);

	/**
	 * Возвращает список дочерних вершин
	 * 
	 * @return список дочерних вершин
	 */
	public abstract List<BTNode> getChildNodes();

	/**
	 * Возвращает результат условия, примененного к владельцу стратегии.
	 * 
	 * @param object
	 *            владелец стратегии
	 * @return {@code true} если результат условия истиный
	 */
	public boolean getConditionResult(Object object) {
		return this.condition.apply(object);
	}
}