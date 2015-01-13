package AIGameEngine.StrategyBT;

import java.util.function.Function;

import AIGameEngine.Object;

/**
 * Элемент дерева поведения. Выполняет попорядку все свои дочернии вершины, если
 * условие этой вершины истинно, до тех пор, пока не обнаружится первая успешная
 * вершина.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public class BTNodeFP extends BTNodeComposite {

	/**
	 * Конструктор инициализирует условие ссылкой на условие.
	 * 
	 * @param condition
	 *            ссылка на некоторое условие
	 */
	public BTNodeFP(Function<Object, Boolean> condition) {
		super(condition);
	}

	@Override
	public boolean execute(Object object) {
		if (!this.getConditionResult(object))
			return false;
		for (BTNode nodeBT : this.getChildNodes()) {
			boolean executionResult = nodeBT.execute(object);
			if (executionResult)
				break;
		}
		return true;
	}

}