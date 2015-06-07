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

import junit.framework.TestCase;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Alexey Nikitin
 */
public class TestBlackboard extends TestCase {

    @Test
    public void testBlackBoardPut1() {
        Blackboard blackboard = new Blackboard();
        blackboard.put("key", "value");
        String value = (String) blackboard.get("key");

        assertEquals(value, "value");
    }

    @Test
    public void testBlackBoardPut2() {
        Blackboard blackboard = new Blackboard();
        String treeUUID = UUID.randomUUID().toString();
        blackboard.put("key", "value", treeUUID);
        String value = (String) blackboard.get("key", treeUUID);

        assertEquals(value, "value");
    }

    @Test
    public void testBlackBoardPut3() {
        Blackboard blackboard = new Blackboard();
        String treeUUID = UUID.randomUUID().toString();
        String nodeUUID = UUID.randomUUID().toString();
        blackboard.put("key", "value", treeUUID, nodeUUID);
        String value = (String) blackboard.get("key", treeUUID, nodeUUID);

        assertEquals(value, "value");
    }
}
