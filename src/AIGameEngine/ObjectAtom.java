package AIGameEngine;

import java.util.*;

/**
 * ��������� ������. ����� ��������� ��� ��������, ������� �� �������� � ����
 * ������, �� ���� ��� <b>��</b> �����������.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 * @see Object
 */
public abstract class ObjectAtom extends Object {

	/**
	 * �������������� �������, ��� ��� ������ {@code ObjectAtom} ���������.
	 */
	@Override
	public void addObject(Object object) {
	}

	/**
	 * �������������� �������, ��� ��� ������ {@code ObjectAtom} ���������.
	 */
	@Override
	public void removeObject(Object object) {
	}

	/**
	 * �������������� �������, ��� ��� ������ {@code ObjectAtom} ���������.
	 * 
	 * @return {@code false}
	 */
	@Override
	public boolean containsObject(Object object) {
		return false;
	}

	@Override
	public List<Object> getChildObjects() {
		return new LinkedList<Object>();
	}
}