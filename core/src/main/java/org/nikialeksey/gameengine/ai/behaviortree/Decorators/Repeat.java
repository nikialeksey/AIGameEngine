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

package org.nikialeksey.gameengine.ai.behaviortree.Decorators;

import org.nikialeksey.gameengine.ai.behaviortree.Node;
import org.nikialeksey.gameengine.ai.behaviortree.Status;
import org.nikialeksey.gameengine.ai.behaviortree.Tick;

/**
 * Класс представляет декоратор, который передает сигнал на исполнение дочерней вершине заданное количество раз.
 * @author Alexey Nikitin
 */
public class Repeat extends Node {

    private int repeatCount;

    /**
     * Конструктор.
     * Начальное значение для {@code repeatCount 1}.
     * @param node дочерняя вершина
     */
    public Repeat(Node node) {
        this(1, node);
    }

    /**
     * Конструктор.
     * @param repeatCount количество передач сигнала на исполнение дочерней вершине.
     * @param node дочерняя вершина
     */
    public Repeat(int repeatCount, Node node) {
        super(node);
        this.repeatCount = repeatCount;
    }

    /**
     * Получить количество повторений сигнала
     * @return количество повторений сигнала
     */
    public int getRepeatCount() {
        return repeatCount;
    }

    /**
     * Установить количество повторения сигнала
     * @param repeatCount новое количество повторения сигнала
     */
    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {

    }

    /**
     * Передает сигнал на исполнение дочерней вершине заданное число раз.
     * @param tick объект тика
     * @return ERROR, если дочерней вершины нет; последний статус, который вернула дочерняя вершина, если количество
     * повторений было не нулевое; если количество повторений было нулевое, то SUCCESS.
     */
    @Override
    public Status tick(Tick tick) {
        if (this.getChildren().isEmpty())
        return Status.ERROR;

        Node child = this.getChildren().get(0);
        if (child == null)
            return Status.ERROR;

        Status status = Status.SUCCESS;
        for (int iter = 0; iter < this.repeatCount; iter++) {
            status = child.execute(tick);
        }

        return status;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}

