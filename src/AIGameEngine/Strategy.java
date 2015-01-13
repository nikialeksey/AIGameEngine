package AIGameEngine;

/**
 * Стратегия определяет поведение объектов. 
 */
public abstract class Strategy {

    /**
     * Выполняет стратегию.
     * @param object владелец стратегии
     */
    public abstract void execute(Object object);
}