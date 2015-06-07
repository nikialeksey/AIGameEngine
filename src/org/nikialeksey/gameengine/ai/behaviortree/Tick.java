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

import java.util.Stack;

/**
 * Класс содержит ссылки на blackboard и behaviorTree.
 * Используется, когда сигнал на исполнение поступает в дерево поведения и пробрасывается дочерним вершинам, чтобы
 * дочерние вершины могли пользоваться blackboard'ом.
 *
 * Так же удобно здесь писать отладочный код.
 * @author Alexey Nikitin
 */
public class Tick {
    private BehaviorTree behaviorTree;
    private Blackboard blackboard;
    private Stack<Node> openNodes;

    public Tick(BehaviorTree behaviorTree, Blackboard blackboard) {
        this.behaviorTree = behaviorTree;
        this.blackboard = blackboard;
        this.openNodes = new Stack<Node>();
    }

    public Blackboard getBlackboard() {
        return this.blackboard;
    }

    public BehaviorTree getBehaviorTree() {
        return this.behaviorTree;
    }

    public void enterNode(Node node) {
    }

    public void openNode(Node node) {
        this.openNodes.push(node);
    }

    public void tickNode(Node node) {

    }

    public void closeNode(Node node) {
        this.openNodes.pop();
    }

    public void exitNode(Node node) {

    }
}
