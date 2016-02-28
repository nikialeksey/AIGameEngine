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

package org.nikialeksey.gameengine.ai.behaviortreedsl

import org.nikialeksey.gameengine.ai.behaviortree.Composites.*
import org.nikialeksey.gameengine.ai.behaviortree.Node

/**
 * @author Alexey Nikitin
 */
fun <T : Node> Node.initNode(child: T, init: T.() -> Unit): T {
    child.init()
    children.add(child)
    return child
}

fun Node.selector(init: Selector.() -> Unit) = initNode(Selector(), init)
fun Node.sequence(init: Sequence.() -> Unit) = initNode(Sequence(), init)
fun Node.parallel(init: Parallel.() -> Unit) = initNode(Parallel(), init)
fun Node.memSelector(init: MemSelector.() -> Unit) = initNode(MemSelector(), init)
fun Node.memSequence(init: MemSequence.() -> Unit) = initNode(MemSequence(), init)
