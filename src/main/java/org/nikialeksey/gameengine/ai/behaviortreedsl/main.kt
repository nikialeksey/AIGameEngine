package org.nikialeksey.gameengine.ai.behaviortreedsl

import org.nikialeksey.gameengine.ai.behaviortree.BehaviorTree
import org.nikialeksey.gameengine.ai.behaviortree.Blackboard
import java.util.function.Consumer

fun main(agrs: Array<String>) {
    val root = _sequence {
        userAction { tick, parent ->
            parent.addChild(_userAction {
                action = Consumer {
                    println("After 1 sec ${uuid}")
                    parent.removeChild(this)
                }
            })
        }
    }
    val bt = BehaviorTree(root)

    val bb = Blackboard()
    bt.execute(bb)
    bt.execute(bb)
    bt.execute(bb)
}