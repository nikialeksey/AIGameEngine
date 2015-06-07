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

package org.nikialeksey.gameengine.ai.behaviortree;

import java.util.ArrayList;

/**
 * Класс представляет композит-селектор с запоминанием вершины, которая вернула результат RUNNING.
 * На следующем тике данная вершина начнет давать сигнал на исполнение как раз последней запущенной
 * дочерней вершне, а не с начала спика дочерних вершин.
 * @author Alexey Nikitin
 */
public class MemSelector extends Node {

    /**
     * Конструктор.
     * @param nodes список дочеирних вершин
     */
    public MemSelector(Node... nodes) {
        super(nodes);
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {
        tick.getBlackboard().put("runningChild", 0, tick.getBehaviorTree().getUUID(), this.getUUID());
    }

    /**
     * Передает сигнал на исполнение дочерним вершинам до тех пор, пока они возвращают статус FAILURE. Как только
     * дочерняя вершина вернет статус, отличный от FAILURE, этот статус будет сразу возращен этой вершиной и выполнение
     * закончится. Если это был статус RUNNING, то в таком случае будет запомнен в blackboard индекс данной дочерней
     * вершины и в следующий раз передача сигнала на исполнение продолжится именно с этой вершины.
     * @param tick объект тика
     * @return либо статус дочерней вершины, которая вернула результат, отличный от FAILURE, либо FAILURE
     */
    @Override
    public Status tick(Tick tick) {
        ArrayList<Node> children = this.getChildren();
        Integer startIndex = (Integer)tick.getBlackboard().get("runningChild", tick.getBehaviorTree().getUUID(), this.getUUID());
        for (int i = startIndex == null ? 0 : startIndex; i < children.size(); i++) {
            Node child = children.get(i);
            Status status = child.btExecute(tick);

            if (status != Status.FAILURE) {
                if (status == Status.RUNNING) {
                    tick.getBlackboard().put("runningChild", i, tick.getBehaviorTree().getUUID(), this.getUUID());
                }
                return status;
            }
        }

        return Status.FAILURE;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}
