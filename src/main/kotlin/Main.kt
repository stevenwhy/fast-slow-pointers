fun main() {
    println("Welcome to Fast/Slow Pointers")
    val head = Node(1)
    head.next = Node(2)
    head.next!!.next = Node(3)
    head.next!!.next!!.next = Node(4)
    head.next!!.next!!.next!!.next = Node(5)
    head.next!!.next!!.next!!.next!!.next = Node(6)
    println("Has cycle: ${linkedListHasCycle(head)} expected false")

    head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
    println("Has cycle: ${linkedListHasCycle(head)} expected true")
    println("Cycle length: ${lengthOfCycle(head)} expected 4")

    head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next!!.next
    println("Has cycle: ${linkedListHasCycle(head)} expected true")
    println("Cycle length: ${lengthOfCycle(head)} expected 3")
}
fun lengthOfCycle(head: Node): Int {
    var result = 0

    var slow: Node? = head
    var fast: Node? = head

    while(fast != null && fast.next != null) {
        fast = fast.next!!.next
        slow = slow?.next
        if(fast?.next == slow) {
            var pointer = slow
            while(true) {
                pointer = pointer?.next
                result++
                if(pointer == slow) break
            }
            break
        }
    }
    return result
}
/**
 * Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.
 */
fun linkedListHasCycle(head: Node): Boolean {
    var slow: Node? = head
    var fast: Node? = head

    while(fast != null && fast.next != null) {
        if(fast.next == slow) return true
        slow = slow?.next
        fast = fast.next!!.next
    }
    return false
}
class Node(
    val value: Int,
    var next: Node? = null
)