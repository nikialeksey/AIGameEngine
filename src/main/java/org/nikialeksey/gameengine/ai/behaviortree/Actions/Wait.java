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

import org.nikialeksey.gameengine.ai.behaviortree.Node;
import org.nikialeksey.gameengine.ai.behaviortree.Status;
import org.nikialeksey.gameengine.ai.behaviortree.Tick;

/**
 * Класс представляет лист-действие "ожидание".
 * @author Alexey Nikitin
 */
public class Wait extends Node {
    private long waitingTime;

    /**
     * Конструктор.
     * @param milliseconds количество миллисекунд, которое необходимо подождать.
     */
    public Wait(long milliseconds) {
        super();
        this.waitingTime = milliseconds;
    }

    @Override
    public void enter(Tick tick) {

    }

    @Override
    public void open(Tick tick) {
        long startTime = System.currentTimeMillis();
        tick.getBlackboard().put("startTime", startTime, tick.getBehaviorTree().getUUID(), this.getUUID());
    }

    /**
     * Проверяет, сколько времени прошло с первого вызова и если прошло времени больше, чем заданное количество, то
     * вернет SUCCESS.
     * @param tick объект тика
     * @return ERROR, если время первого запуска не было записано; RUNNING, если время ожидания еще не прошло; SUCCESS,
     * если время ожидания превышает или совпает с заданным значением.
     */
    @Override
    public Status tick(Tick tick) {
        long currTime = System.currentTimeMillis();
        Long startTime = (Long)tick.getBlackboard().get("startTime", tick.getBehaviorTree().getUUID(), this.getUUID());

        if (startTime == null)
            return Status.ERROR;
        if (currTime - startTime >= this.waitingTime)
            return Status.SUCCESS;
        return Status.RUNNING;
    }

    @Override
    public void close(Tick tick) {

    }

    @Override
    public void exit(Tick tick) {

    }
}
