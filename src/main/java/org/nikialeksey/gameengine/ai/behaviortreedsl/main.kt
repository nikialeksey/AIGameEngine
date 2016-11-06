package org.nikialeksey.gameengine.ai.behaviortreedsl

import org.nikialeksey.gameengine.ai.behaviortree.BehaviorTree
import org.nikialeksey.gameengine.ai.behaviortree.Blackboard
import java.util.function.Consumer

fun main(agrs: Array<String>) {
    val root = Sequence {
        userAction { tick, parent ->
            parent.addChild(UserAction {
                action = Consumer {
                    println(uuid)
                    parent.removeChild(this)
                }
            })
        }
    }
    println(root.children.size) // 1

    val bt = BehaviorTree(root)
    val bb = Blackboard()

    bt.execute(bb) // первый userAction добавит еще один userAction к sequence
    println(root.children.size) // 2

    bt.execute(bb) // первый userAction сделает то же самое, а второй userAction
                   // выведет свой id и удалит себя из sequence
    println(root.children.size) // 2

    bt.execute(bb) // --//--//--
    println(root.children.size) // 2
}