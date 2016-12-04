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

import org.nikialeksey.gameengine.ai.behaviortree.Decorators.*
import org.nikialeksey.gameengine.ai.behaviortree.Node

/**
 * @author Alexey Nikitin
 */

fun <T : Node> Node.initDecorator(decorator: T): T {
    children.add(decorator)
    return decorator
}

fun Node.alwaysFailure(node: Node) = initDecorator(AlwaysFailure(node))
fun Node.alwaysSuccess(node: Node) = initDecorator(AlwaysSuccess(node))
fun Node.inverter(node: Node) = initDecorator(Inverter(node))
fun Node.untilFailure(node: Node) = initDecorator(UntilFailure(node))
fun Node.untilSuccess(node: Node) = initDecorator(UntilSuccess(node))
fun Node.repeat(count: Int, node: Node) = initDecorator(Repeat(node))