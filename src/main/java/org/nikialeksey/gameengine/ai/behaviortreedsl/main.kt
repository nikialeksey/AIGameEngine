package org.nikialeksey.gameengine.ai.behaviortreedsl

import org.nikialeksey.gameengine.ai.behaviortree.Blackboard
import java.util.function.Consumer

fun main(agrs: Array<String>) {
    /**
     * sequence {
     *      wait(n)
     *      sequence {
     *          userAction { ... }
     *          condition { ... }
     *          delete() - удалить текущую вершину из списка родительской
     *      }
     *      add(node { ... })
     * }
     */
    val bt = behaviorTree {
        _sequence {
            userAction { tick, parent ->
                println(parent.children.size)
                parent.addChild(_userAction {
                    action = Consumer {
                        println("After 1 sec ${uuid}")
                        parent.removeChild(this)
                    }
                })
            }
        }
    }

    val bb = Blackboard()
    bt.execute(bb)
    bt.execute(bb)
    bt.execute(bb)
}