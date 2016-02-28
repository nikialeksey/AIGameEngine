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

import java.util.UUID;

/**
 * Класс представляет дерево поведения.
 * @author Alexey Nikitin
 */
public class BehaviorTree {

    /**
     * корневая вершина
     */
    private Node root;
    /**
     * уникальный идентификатор дерева поведения
     */
    private String uuid;

    /**
     * Конструктор дерева поведения.
     * Назначает уникальный идентификатор и устанавливает ссылку на корень дерева поведнеия.
     * @param root корень дерева поведения, именно этой вершине будут передаваться сигналы на исполнение.
     */
    public BehaviorTree(Node root) {
        this.uuid = UUID.randomUUID().toString();
        this.root = root;
    }

    /**
     * Создает объект тика и передает сигнал на исполнение корню дерева поведения
     * @param blackboard память для принятия решения
     * @return объект статуса
     */
    public Status execute(Blackboard blackboard) {
        Tick tick = new Tick(this, blackboard);

        return this.root.execute(tick);

    }

    /**
     * Возвращает уникальный идентификатор дерева поведения.
     * @return строка, представляющая уникальный идентификатор дерева поведения.
     */
    public String getUUID() {return this.uuid;}

}
