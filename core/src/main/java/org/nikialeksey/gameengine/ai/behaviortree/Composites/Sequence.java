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
 * Класс представляет композит-последовательность.
 * Выполняет всех своих детей до тех пор, пока они возвращают SUCCESS
 * @author Alexey Nikitin
 */
public class Sequence extends Node {

    /**
     * Конструктор.
     * @param nodes список дочеирних вершин
     */
    public Sequence(Node... nodes) {
        super(nodes);
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {

    }

    /**
     * Передает сигнал на исполнение всем своим дочерним вершинам до тех пор, пока они возвращают SUCCESS
     * @param tick объект тика
     * @return либо статус дочерней вершины, которая вернула результат, отличный от SUCCESS, либо SUCCESS
     */
    @Override
    public Status tick(Tick tick) {
        for (Node child: this.getChildren()) {
            Status status = child.execute(tick);

            if (status != Status.SUCCESS)
                return status;
        }

        return Status.SUCCESS;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}
