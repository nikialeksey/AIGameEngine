package AIGameEngine;

import java.util.*;

/**
 * Неделимый объект. Класс необходим для объектов, которые не содержат в себе
 * ничего, то есть они <b>не</b> композитные.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 * @see Object
 */
public abstract class ObjectAtom extends Object {

	/**
	 * Неиспользуемая функция, так как объект {@code ObjectAtom} неделимый.
	 */
	@Override
	public void addObject(Object object) {
	}

	/**
	 * Неиспользуемая функция, так как объект {@code ObjectAtom} неделимый.
	 */
	@Override
	public void removeObject(Object object) {
	}

	/**
	 * Неиспользуемая функция, так как объект {@code ObjectAtom} неделимый.
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