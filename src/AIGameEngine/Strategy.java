package AIGameEngine;

/**
 * ��������� ���������� ��������� ��������. 
 */
public abstract class Strategy {

    /**
     * �������������� ������ �� ������, ��������� ���� ����������.
     * @param ownObject ������, ��������� ������ ���������� 
     */
    public Strategy(Object ownObject) {
    	this.ownObject = ownObject;
    }

    /**
     * ������ �� ������, ��������� ���� ����������.
     */
    private final Object ownObject;






    /**
     * ��������� ���������.
     */
    public abstract void execute();

    /**
     * ���������� ������, ��������� ������ ����������.
     * @return ������, ��������� ������ ����������
     */
    public Object getOwnObject() {
    	return this.ownObject;
    }

}