package AIGameEngine.StrategyBT;

import java.util.List;
import java.util.function.Function;

import AIGameEngine.Object;
/**
 * ������� ������ ���������. ��������� ��� �������� ������ ������ ���������.
 * ������ ����� ������� �������� ������� ��������� ����� ��������.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 * @see StrategyBT
 */
public abstract class BTNode {

	/**
	 * �����������. �������������� ������� �� ������� ������� �������, �
	 * �������������� ������ �������� ������.
	 */
	public BTNode(Function<Object, Boolean> condition) {
		this.condition = condition;
	}

	/**
	 * ������� ������� ����� ��������.
	 */
	private Function<Object, Boolean> condition;

	/**
	 * ��������� ������� �, ��������, ��������� �������� ��������.
	 * 
	 * @param object
	 *            ������ ���������
	 * @return {@code true} ���� ������� �������, ����������� � ������� �������
	 */
	public abstract boolean execute(Object object);

	/**
	 * ��������� �������� ������� �� ����������� ������ index.
	 * 
	 * @param index
	 *            ���������� ����� �������
	 * @param child
	 *            ����� �������
	 */
	public abstract void addChild(int index, BTNode child);

	/**
	 * ������� �������� ������� �� ������.
	 * 
	 * @param index
	 *            ������ ���������� ��������
	 */
	public abstract void removeChild(int index);

	/**
	 * �������� �������� ������� �� ������ ����� ���������.
	 * 
	 * @param index
	 *            ����� ��������� ��������
	 * @param child
	 *            ����� �������� ��������� ��������
	 */
	public abstract void setChild(int index, BTNode child);

	/**
	 * ���������� �������� ������� �� ������.
	 * 
	 * @param index
	 *            ����� ��������� ��������
	 * @return �������� ������� ������ ���������
	 */
	public abstract BTNode getChild(int index);

	/**
	 * ���������� ������ �������� ������
	 * 
	 * @return ������ �������� ������
	 */
	public abstract List<BTNode> getChildNodes();

	/**
	 * ���������� ��������� �������, ������������ � ��������� ���������.
	 * 
	 * @param object
	 *            �������� ���������
	 * @return {@code true} ���� ��������� ������� �������
	 */
	public boolean getConditionResult(Object object) {
		return this.condition.apply(object);
	}
}