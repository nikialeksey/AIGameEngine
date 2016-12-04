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

package org.nikialeksey.gameengine.ai.behaviortree.Composites;

import org.nikialeksey.gameengine.ai.behaviortree.Node;
import org.nikialeksey.gameengine.ai.behaviortree.Status;
import org.nikialeksey.gameengine.ai.behaviortree.Tick;

/**
 * Класс представляет параллельный композит.
 * @author Alexey Nikitin
 */
public class Parallel extends Node {
    private int successCount, failureCount;

    /**
     * Конструктор.
     * Устанавливает {@code successCount} в {@code nodes.length / 2}, а {@code failureCount} в {@code 0}.
     * @param nodes список дочерних вершин
     */
    public Parallel(Node... nodes) {
        this(nodes.length / 2, 0, nodes);
    }

    /**
     * Конструктор.
     * @param successCount количество дочерних вершин, которое должно вернуть SUCCESS,
     *                     чтобы данный композит вернул SUCCESS
     * @param failureCount количество дочерних вершин, которое должно вернуть FAILURE,
     *                     чтобы данный композит вернул FAILURE
     * @param nodes список дочеирних вершин
     */
    public Parallel(int successCount, int failureCount, Node... nodes) {
        super(nodes);
        this.successCount = successCount;
        this.failureCount = failureCount;
    }

    /**
     * Получить количество дочерних вершин, которые должны вернуть FAILURE
     * @return количество дочерних вершин, которые должны вернуть FAILURE
     * @see #tick(Tick)
     */
    public int getFailureCount() {
        return failureCount;
    }

    /**
     * Установить количество дочерних вершин, которое должно вернуть FAILURE
     * @param failureCount количество дочерних вершин, которое должно вернуть FAILURE
     * @see #tick(Tick)
     */
    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    /**
     * Получить количество дочерних вершин, которые должны вернуть SUCCESS
     * @return количество дочерних вершин, которые должны вернуть SUCCESS
     * @see #tick(Tick)
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * Установить количество дочерних вершин, которое должно вернуть SUCCESS
     * @param successCount количество дочерних вершин, которое должно вернуть SUCCESS
     * @see #tick(Tick)
     */
    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {

    }

    /**
     * Передает сигнал на исполнение всем дочерним вершинам одновременно.
     * @param tick объект тика
     * @return если количество дочерних вершин, вернувших результат SUCCESS больше порогового значение
     * {@code successCount} и, при этом, количество вершин, вернувших результат FAILURE меньше порогового значения
     * {@code failureCount}, то SUCCESS; если количество дочерних вершин, вернувших результат FAILURE больше порогового значение
     * {@code failureCount} и, при этом, количество вершин, вернувших результат SUCCESS меньше порогового значения
     * {@code successCount}, то FAILURE; иначе ERROR
     */
    @Override
    public Status tick(Tick tick) {
        int currentSuccessCount = 0,
                currentFailureCount = 0;
        for (Node child: this.getChildren()) {
            Status status = child.execute(tick);
            if (status == Status.SUCCESS)
                currentSuccessCount++;
            else if (status == Status.FAILURE)
                currentFailureCount++;
        }
        if (currentSuccessCount >= successCount && currentFailureCount < failureCount)
            return Status.SUCCESS;

        if (currentFailureCount >= failureCount && currentSuccessCount < successCount)
            return Status.FAILURE;

        return Status.ERROR;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}
