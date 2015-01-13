package AIGameEngine;

/**
 * Стратегия определяет поведение объектов. 
 */
public abstract class Strategy {

    /**
     * Инициализирует ссылку на объект, владеющей этой стратегией.
     * @param ownObject объект, владеющей данной стратегией 
     */
    public Strategy(Object ownObject) {
    	this.ownObject = ownObject;
    }

    /**
     * Ссылка на объект, владеющей этой стратегией.
     */
    private final Object ownObject;






    /**
     * Выполняет стратегию.
     */
    public abstract void execute();

    /**
     * Возвращает объект, владеющей данной стратегией.
     * @return объект, владеющий данной стратегией
     */
    public Object getOwnObject() {
    	return this.ownObject;
    }

}