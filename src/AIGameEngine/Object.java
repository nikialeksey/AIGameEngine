package AIGameEngine;

import java.util.*;

/**
 * Этот класс представляет любой объект в игре. Абсолютно любой объект в игре
 * может влиять на происходящее. Поэтому все объекты должны так или иначе
 * порождать свой Object класс, который будет ответственным за логику
 * виртуального интеллекта.
 * 
 * @author Alexey Nikitin
 * @version 0.1
 */
public abstract class Object {

	/**
	 * Ссылка на родительский объект для данного.
	 */
	private Object parentObject;

	/**
	 * Стратегия, используемая данным объектом.
	 */
	private Strategy strategy;

	/**
	 * Добавляет объект к данному как дочерний.
	 * 
	 * @param object
	 *            дочерний объект
	 */
	public abstract void addObject(Object object);

	/**
	 * Удаляет дочерний объект из данного.
	 * 
	 * @param object
	 *            удаляемый объект
	 */
	public abstract void removeObject(Object object);

	/**
	 * Проверяет, содержится ли объект в данном как дочерний.
	 * 
	 * @param object
	 *            искомый объект
	 * @return {@code true} если объект содержится в данном как дочерний
	 */
	public abstract boolean containsObject(Object object);

	/**
	 * Возвращает родительский объект к данному.
	 * 
	 * @return родительский объект для данного
	 */
	public Object getParentObject() {
		return this.parentObject;
	}

	/**
	 * Устанавливает новый родительский объект к данному.
	 * 
	 * @param parentObject
	 *            новый родительский объект
	 */
	public void setParentObject(Object parentObject) {
		this.parentObject = parentObject;
	}

	/**
	 * Возвращает список дочерних объектов.
	 * 
	 * @return список дочерних объектов
	 */
	public abstract List<Object> getChildObjects();

	/**
	 * Выполняет действие, задаваемое идентификатором.
	 * 
	 * @param actionEvent
	 *            идентификатор действия
	 */
	public abstract void action(int actionEvent);

	/**
	 * Возвращает стратегию данного объекта.
	 * 
	 * @return стратегию данного объекта
	 */
	public Strategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Устанавливает новую стратегию для данного объекта.
	 * 
	 * @param strategy
	 *            новая стратегия объекта
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Проверяет условие, заданное идентификатором.
	 * 
	 * @param conditionEvent
	 *            идентификатор условия, которое необходимо проверить
	 * @return {@code true} если условие истино
	 */
	public abstract boolean getConditionResult(int conditionEvent);

	/**
	 * Выполняет один тик стратегии.
	 */
	public void run() {
		if (this.strategy != null)
			this.strategy.execute(this);
	}

}