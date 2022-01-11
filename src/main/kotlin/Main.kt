fun main() {
    println("Welcome to Fast/Slow Pointers")
    val head = Node(1)
    head.next = Node(2)
    head.next!!.next = Node(3)
    head.next!!.next!!.next = Node(4)
    head.next!!.next!!.next!!.next = Node(5)

    println("Middle Node: ${findMiddleNode(head)?.value} expected 3")
    head.next!!.next!!.next!!.next!!.next = Node(6)
    println("Middle Node: ${findMiddleNode(head)?.value} expected 4")
    head.next!!.next!!.next!!.next!!.next!!.next = Node(7)
    println("Middle Node: ${findMiddleNode(head)?.value} expected 4")


    println("Has cycle: ${linkedListHasCycle(head)} expected false")
    println("~~~~~~~~~~~~~~")
    head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next
    println("Has cycle: ${linkedListHasCycle(head)} expected true")
    println("Cycle length: ${lengthOfCycle(head)} expected 4")
    println("Start of cycle: ${findStartingNodeOfCycle(head)?.value} expected 3")
    println("~~~~~~~~~~~~~~")
    head.next!!.next!!.next!!.next!!.next!!.next = head.next!!.next!!.next
    println("Has cycle: ${linkedListHasCycle(head)} expected true")
    println("Cycle length: ${lengthOfCycle(head)} expected 3")
    println("Start of cycle: ${findStartingNodeOfCycle(head)?.value} expected 4")
    println("~~~~~~~~~~~~~~")
    head.next!!.next!!.next!!.next!!.next!!.next = head
    println("Start of cycle: ${findStartingNodeOfCycle(head)?.value} expected 1)")
    println("~~~~~~~~~~~~~~")
    println("Is 23 happy num: ${findHappyNumber(23)} expected true)")
    println("Is 12 happy num: ${findHappyNumber(12)} expected false)")

    println("~~~~~~~~~~~~~~")
    println("Palindrome")
    val head2 = Node(2)
    head2.next = Node(4)
    head2.next!!.next = Node(6)
    head2.next!!.next!!.next = Node(4)
    head2.next!!.next!!.next!!.next = Node(2)
    println("Is palindrome: ${isPalindrome(head2)} expected true")

    head2.next!!.next!!.next!!.next!!.next = Node(2)
    println("Is palindrome: ${isPalindrome(head2)} expected false")
}
/**
 * Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.
 */
fun isPalindrome(head: Node): Boolean {
    // step 1 is to find the middle then reverse the second half of the linked list
    // then we can compare the first half to the second half step by step
    // then reverse the second-half back
    var result = false
    var secondHalfHead: Node? = reverse(findMiddleNode(head) ?: head)
    val copyOfSecondHalfHead = secondHalfHead
    var firstHalfHead: Node? = head
    while(firstHalfHead != null && secondHalfHead != null) {
        if(firstHalfHead.value != secondHalfHead.value) break
        firstHalfHead = firstHalfHead.next
        secondHalfHead = secondHalfHead.next
    }
    if(firstHalfHead == null || secondHalfHead == null) result = true
    reverse(copyOfSecondHalfHead ?: head)
    return result
}
private fun reverse(node: Node): Node? {
    var previous: Node? = null
    var head: Node? = node
    while(head != null) {
        val next: Node? = head.next
        head.next = previous
        previous = head
        head = next
    }
    return previous
}
/**
 * Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
 *  If the total number of nodes in the LinkedList is even, return the second middle node.
 */
fun findMiddleNode(head: Node): Node? {
    // We can do this in one traversal by using two pointers.
    // One can move 1 step and the other 2 steps.
    // every loop, if fast.next is null then slow is the middle
    // if fast.next.next is null then slow.next is the middle
    var slow: Node? = head
    var fast: Node? = head
    while(fast != null && slow != null) {
        if(fast.next == null) return slow
        else if(fast.next!!.next == null) return slow.next
        else {
            fast = fast.next!!.next!!
            slow = slow.next
        }
    }
    return head
}
/**
 * Any number will be called a happy number if,
 *     after repeatedly replacing it with a number equal to the sum of the square of all of its digits,
 *     leads us to number ‘1’.
 */
fun findHappyNumber(num: Int): Boolean {
    var slow = num
    var fast = num
    while(true) {
        slow = findSumOfSquareOfDigits(slow)
        fast = findSumOfSquareOfDigits(findSumOfSquareOfDigits(fast))
        if(fast == slow) break
    }
    return slow == 1
}
private fun findSumOfSquareOfDigits(num: Int): Int {
    var result = 0
    var temp = num
    while(temp > 0) {
        val digit = temp%10
        result += digit * digit
        temp /= 10
    }
    return result
}

/**
 * Given the head of a Singly LinkedList that contains a cycle,
 *    write a function to find the starting node of the cycle.
 */
fun findStartingNodeOfCycle(head: Node): Node? {
    // we need to make the fastPointer ahead by the length of the cycle,
    //   then just increment fast and slow by 1 until they meet
    val length = lengthOfCycle(head)
    var slow: Node? = head
    var fast: Node? = head
    // first increment fast by the length of the cycle
    for(i in 0 until length) {
        fast = fast?.next
    }
    while(true) {
        if(fast == slow) return slow
        if(fast == null || slow == null) return null
        else {
            fast = fast.next
            slow = slow.next
        }
    }
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