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
import java.util.Collections;
import java.util.UUID;

/**
 * Абстрактный класс вершины дерева повдения.
 * Содержит необходимые методы для выполнения логики вершины.
 * @author Alexey Nikitin
 */
public abstract class Node {

    private String uuid;
    private ArrayList<Node> children;

    /**
     * Конструктор.
     * @param nodes дочерние вершины
     */
    public Node(Node... nodes) {
        this.uuid = UUID.randomUUID().toString();
        this.children = new ArrayList<Node>(nodes.length);
        Collections.addAll(children, nodes);
    }

    /**
     * Возвращает список дочерних вершин
     * @return список дочерних вершин
     */
    public ArrayList<Node> getChildren() {
        return this.children;
    }

    /**
     * Возвращает уникальный идентификатор вершины
     * @return уникальный идентификатор вершины
     */
    public String getUUID(){return this.uuid;}

    /**
     * Выполняет логику вершины
     * @param tick объект тика
     * @return статус, после выполнения логики
     */
    public Status btExecute(Tick tick) {
        this.btEnter(tick);

        this.btOpen(tick);

        Status status = this.btTick(tick);

        if (status != Status.RUNNING) {
            this.btClose(tick);
        }

        this.btExit(tick);

        return status;
    }

    public void btEnter(Tick tick) {
        tick.enterNode(this);
        this.enter(tick);
    }

    public void btOpen(Tick tick) {
        tick.openNode(this);
        tick.getBlackboard().put("isOpen", true, tick.getBehaviorTree().getUUID(), this.getUUID());
        this.open(tick);
    }

    public Status btTick(Tick tick) {
        tick.tickNode(this);
        return this.tick(tick);
    }

    public void btClose(Tick tick) {
        tick.closeNode(this);
        tick.getBlackboard().put("isOpen", false, tick.getBehaviorTree().getUUID(), this.getUUID());
        this.close(tick);
    }

    public void btExit(Tick tick) {
        tick.exitNode(this);
        this.exit(tick);
    }

    /**
     * Вызывается, при осуществлении входа в вершину
     * @param tick объект тика
     */
    public abstract void enter(Tick tick);

    /**
     * Вызывается, при осуществлении открытия вершины (всегда после входа, но не всегда открытая вершина закрывается
     * после выполнения логики)
     * @param tick объект тика
     */
    public abstract void open(Tick tick);

    /**
     * Содержит код логики вершины, после отработки возвращает один из четырех статусов: RUNNING, WAIT, FAILURE, SUCCESS
     * @param tick объект тика
     * @return один из четырех статусов: RUNNING, WAIT, FAILURE, SUCCESS
     */
    public abstract Status tick(Tick tick);

    /**
     * Вызывается, при осуществлении закрытия вершины (не вызывается после выполнении логики,
     * если статус {@code RUNNING})
     * @param tick объект тика
     */
    public abstract void close(Tick tick);

    /**
     * Вызывается, при осуществлении выхода из вершины (всегда после выполнения логики, но позже
     * закрытия вершины, если оно было)
     * @param tick объект тика
     */
    public abstract void exit(Tick tick);
}
