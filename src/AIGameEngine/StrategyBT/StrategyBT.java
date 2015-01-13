package AIGameEngine.StrategyBT;

import AIGameEngine.Object;
import AIGameEngine.Strategy;

/**
 * Стратегия, определяющая поведение объекта с помощью деревьев поведения.
 * @author Alexey Nikitin
 * @version 0.1
 * @see <a href="http://en.wikipedia.org/wiki/Behavior_Trees_(Artificial_Intelligence,_Robotics_and_Control)"> wiki </a>
 * @see Strategy
 */
public class StrategyBT extends Strategy {

    /**
     * Конструктор инициализирует ссылку на корень дерева поведения.
     * @param ownObject владелец стратегией
     * @param rootBT корень дерева поведения
     */
    public StrategyBT(BTNode rootBT) {
    	this.rootBT = rootBT;
    }

    /**
     * Корень дерева поведения.
     */
    private BTNode rootBT;

	@Override
	public void execute(Object object) {
		this.rootBT.execute(object);
	}



}