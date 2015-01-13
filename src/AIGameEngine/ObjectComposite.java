package AIGameEngine;

import java.util.*;

/**
 * Сотавной объект. Данный класс может содержать в себе другие объекты
 * {@code Object}.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 * @see Object
 */
public abstract class ObjectComposite extends Object {

	/**
	 * Конструктор по умолчанию. Инициализирует список объектов.
	 */
	public ObjectComposite() {
		this.objects = new LinkedList<Object>();
	}

	/**
	 * Список дочерних объектов.
	 */
	private List<Object> objects;

	@Override
	public void addObject(Object object) {
		this.objects.add(object);
	}

	@Override
	public void removeObject(Object object) {
		this.objects.remove(object);
	}

	@Override
	public boolean containsObject(Object object) {
		return this.objects.contains(object);
	}

	@Override
	public List<Object> getChildObjects() {
		return this.objects;
	}
}