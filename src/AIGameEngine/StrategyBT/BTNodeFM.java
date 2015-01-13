package AIGameEngine.StrategyBT;

import java.util.function.Function;

/**
 * ������� ������ ���������. ��������� ��������� ��� ���� �������� �������, ����
 * ������� ���� ������� �������, �� ��� ���, ���� �� ����������� ������ ��
 * �������� �������.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public class BTNodeFM extends BTNodeComposite {

	/**
	 * ����������� �������������� ������� ������� �� �������.
	 * 
	 * @param condition
	 *            ������ �� ��������� �������
	 */
	public BTNodeFM(Function<Object, Boolean> condition) {
		super(condition);
	}

	@Override
	public boolean execute(Object object) {
		if (!this.getConditionResult(object))
			return false;
		for (BTNode nodeBT : this.getChildNodes()) {
			boolean executionResult = nodeBT.execute(object);
			if (!executionResult)
				break;
		}
		return true;
	}

}