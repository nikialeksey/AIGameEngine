package AIGameEngine.StrategyBT;

import AIGameEngine.Object;
import AIGameEngine.Strategy;

/**
 * ���������, ������������ ��������� ������� � ������� �������� ���������.
 * @author Alexey Nikitin
 * @version 0.1
 * @see <a href="http://en.wikipedia.org/wiki/Behavior_Trees_(Artificial_Intelligence,_Robotics_and_Control)"> wiki </a>
 * @see Strategy
 */
public class StrategyBT extends Strategy {

    /**
     * ����������� �������������� ������ �� ������ ������ ���������.
     * @param ownObject �������� ����������
     * @param rootBT ������ ������ ���������
     */
    public StrategyBT(BTNode rootBT) {
    	this.rootBT = rootBT;
    }

    /**
     * ������ ������ ���������.
     */
    private BTNode rootBT;

	@Override
	public void execute(Object object) {
		this.rootBT.execute(object);
	}



}