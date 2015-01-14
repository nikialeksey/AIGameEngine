package AIGameEngine;

import java.util.*;

/**
 * ���� ����� ������������ ����� ������ � ����. ��������� ����� ������ � ����
 * ����� ������ �� ������������. ������� ��� ������� ������ ��� ��� �����
 * ��������� ���� Object �����, ������� ����� ������������� �� ������
 * ������������ ����������.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public abstract class Object {

	/**
	 * ������ �� ������������ ������ ��� �������.
	 */
	private Object parentObject;

	/**
	 * ���������, ������������ ������ ��������.
	 */
	private Strategy strategy;

	/**
	 * ��������� ������ � ������� ��� ��������.
	 * 
	 * @param object
	 *            �������� ������
	 */
	public abstract void addObject(Object object);

	/**
	 * ������� �������� ������ �� �������.
	 * 
	 * @param object
	 *            ��������� ������
	 */
	public abstract void removeObject(Object object);

	/**
	 * ���������, ���������� �� ������ � ������ ��� ��������.
	 * 
	 * @param object
	 *            ������� ������
	 * @return {@code true} ���� ������ ���������� � ������ ��� ��������
	 */
	public abstract boolean containsObject(Object object);

	/**
	 * ���������� ������������ ������ � �������.
	 * 
	 * @return ������������ ������ ��� �������
	 */
	public Object getParentObject() {
		return this.parentObject;
	}

	/**
	 * ������������� ����� ������������ ������ � �������.
	 * 
	 * @param parentObject
	 *            ����� ������������ ������
	 */
	public void setParentObject(Object parentObject) {
		this.parentObject = parentObject;
	}

	/**
	 * ���������� ������ �������� ��������.
	 * 
	 * @return ������ �������� ��������
	 */
	public abstract List<Object> getChildObjects();

	/**
	 * ��������� ��������, ���������� ���������������.
	 * 
	 * @param actionEvent
	 *            ������������� ��������
	 */
	public abstract void action(int actionEvent);

	/**
	 * ���������� ��������� ������� �������.
	 * 
	 * @return ��������� ������� �������
	 */
	public Strategy getStrategy() {
		return this.strategy;
	}

	/**
	 * ������������� ����� ��������� ��� ������� �������.
	 * 
	 * @param strategy
	 *            ����� ��������� �������
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * ��������� �������, �������� ���������������.
	 * 
	 * @param conditionEvent
	 *            ������������� �������, ������� ���������� ���������
	 * @return {@code true} ���� ������� ������
	 */
	public abstract boolean getConditionResult(int conditionEvent);

	/**
	 * ��������� ���� ��� ���������.
	 */
	public void run() {
		if (this.strategy != null)
			this.strategy.execute(this);
	}

}