/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alexey Nikitin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.nikialeksey.gameengine.ai.behaviortree.Actions;

import org.nikialeksey.gameengine.ai.behaviortree.Blackboard;
import org.nikialeksey.gameengine.ai.behaviortree.Node;
import org.nikialeksey.gameengine.ai.behaviortree.Status;
import org.nikialeksey.gameengine.ai.behaviortree.Tick;

import java.util.function.Predicate;

/**
 * Класс представляет вершину-условие.
 * В зависимости от резульатата проверки условия эта вершина возвратит статус SUCCESS или FAILURE
 * @author Alexey Nikitin
 */
public class Condition extends Node {
    private Predicate<Tick> condition;

    /**
     * Конструктор.
     * Условие по-умолчанию всегда истино.
     */
    public Condition() {
        this(tick -> true);
    }

    /**
     * Конструктор.
     * @param condition условие
     */
    public Condition(Predicate<Tick> condition) {
        super();
        this.condition = condition;
    }

    /**
     * Получить условие вершины
     * @return условие
     */
    public Predicate<Tick> getCondition() {
        return condition;
    }

    /**
     * Установить условие для вершины
     * @param condition новое условие для вершины
     */
    public void setCondition(Predicate<Tick> condition) {
        this.condition = condition;
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {

    }

    /**
     * Проверяет условие, заданное пользователем, на основании результата проверки возвращает SUCCESS, если результат
     * положительный, иначе FAILURE
     * @param tick объект тика
     * @return возвращает SUCCESS, если результат
     *           положительный, иначе FAILURE
     */
    @Override
    public Status tick(Tick tick) {
        boolean result = condition.test(tick);
        return result ? Status.SUCCESS: Status.FAILURE;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}
