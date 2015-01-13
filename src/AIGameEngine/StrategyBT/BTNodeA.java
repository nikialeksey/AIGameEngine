package AIGameEngine.StrategyBT;

import java.util.function.Function;

import AIGameEngine.Object;

/**
 * Элемент дерева поведения, который выполняет все своидочерние вершины.
 */
public class BTNodeA extends BTNodeComposite {

	/**
	 * Конструктор инициализирует условие ссылкой на условие.
	 * 
	 * @param condition
	 *            ссылка на некоторое условие
	 */
	public BTNodeA(Function<Object, Boolean> condition) {
		super(condition);
	}

	/**
	 * Не обращая внимания на условие, выполняет все свои дочерние вершины
	 * 
	 * @param object
	 *            объект стратегии
	 * @return {@code true}
	 */
	@Override
	public boolean execute(Object object) {
		if (!this.getConditionResult(object))
			return false;
		this.getChildNodes().forEach(nodeBT -> nodeBT.execute(object));
		return true;
	}

}