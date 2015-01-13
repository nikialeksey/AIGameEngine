package AIGameEngine.StrategyBT;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ������� ������ ���������. �� ����� ����� �������� ������. �������� � ����
 * ������ ��������, ������� ����������� � �������.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public class BTNodeLeaf extends BTNode {

	/**
	 * ����������� �������������� ������� ������� �� �������.
	 * 
	 * @param condition
	 *            ������ �� ��������� �������
	 */
	public BTNodeLeaf(Function<Object, Boolean> condition) {
		super(condition);
	}

	/**
	 * ������ �������� ��� ��������� ���������.
	 */
	private List<Predicate<Object>> actions;

	/**
	 * ��������� �������� � ������ �� �������.
	 * 
	 * @param index
	 *            ������ ��������
	 * @param action
	 *            ��������
	 */
	public void addAction(int index, Predicate<Object> action) {
		this.actions.add(index, action);
	}

	/**
	 * ������� �������� �� ������ �������� �� �������
	 * 
	 * @param index
	 *            ������ ���������� ��������
	 */
	public void removeAction(int index) {
		this.actions.remove(index);
	}

	/**
	 * ������������� ����� �������� �� ������� �� ������.
	 * 
	 * @param index
	 *            ����� ������ ��������
	 * @param action
	 *            ����� ��������
	 */
	public void setAction(int index, Predicate<Object> action) {
		this.actions.set(index, action);
	}

	/**
	 * ���������� �������� �� �������.
	 * 
	 * @param index
	 *            ������ ��������
	 * @return ��������
	 */
	public Predicate<Object> getAction(int index) {
		return this.actions.get(index);
	}

	/**
	 * ��������� �� ������� ��� ��������, ���� ������� ������� �������.
	 * 
	 * @param object
	 *            �������� ���������
	 * @return {@code true} ���� ������� ������
	 */
	@Override
	public boolean execute(Object object) {
		if (!this.getConditionResult(object))
			return false;

		return true;
	}

	/**
	 * �� ������������ �������, ��� ��� �� ����� ����� �������� �������.
	 */
	@Override
	public void addChild(int index, BTNode child) {
	}

	/**
	 * �� ������������ �������, ��� ��� �� ����� ����� �������� �������.
	 */
	@Override
	public void removeChild(int index) {
	}

	/**
	 * �� ������������ �������, ��� ��� �� ����� ����� �������� �������.
	 */
	@Override
	public void setChild(int index, BTNode child) {
	}

	/**
	 * �� ������������ �������, ��� ��� �� ����� ����� �������� �������.
	 */
	@Override
	public BTNode getChild(int index) {
		throw new IndexOutOfBoundsException();
	}

	/**
	 * �� ������������ �������, ��� ��� �� ����� ����� �������� �������.
	 */
	@Override
	public List<BTNode> getChildNodes() {
		return new LinkedList<BTNode>();
	}

}