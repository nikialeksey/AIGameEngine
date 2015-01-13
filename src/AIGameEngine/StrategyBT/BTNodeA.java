package AIGameEngine.StrategyBT;

import java.util.function.Function;

import AIGameEngine.Object;

/**
 * ������� ������ ���������, ������� ��������� ��� ������������ �������.
 */
public class BTNodeA extends BTNodeComposite {

	/**
	 * ����������� �������������� ������� ������� �� �������.
	 * 
	 * @param condition
	 *            ������ �� ��������� �������
	 */
	public BTNodeA(Function<Object, Boolean> condition) {
		super(condition);
	}

	/**
	 * �� ������� �������� �� �������, ��������� ��� ���� �������� �������
	 * 
	 * @param object
	 *            ������ ���������
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