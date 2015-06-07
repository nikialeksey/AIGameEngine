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
 * @author Alexey Nikitin
 */
public abstract class Node {

    private String uuid;
    private ArrayList<Node> children;

    public Node(Node... nodes) {
        this.uuid = UUID.randomUUID().toString();
        this.children = new ArrayList<Node>(nodes.length);
        Collections.addAll(children, nodes);
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public String getUUID(){return this.uuid;}

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

    public abstract void enter(Tick tick);
    public abstract void open(Tick tick);
    public abstract Status tick(Tick tick);
    public abstract void close(Tick tick);
    public abstract void exit(Tick tick);
}
