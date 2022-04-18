fun main() {
    // only the last problem statement is in this class, rest are in Main.kt

    println("${findIfCycleInArray(listOf(1, 2, -1, 2, 2))} expected true")
    println("${findIfCycleInArray(listOf(2, 2, -1, 2))} expected true")
    println("${findIfCycleInArray(listOf(2, 1, -1, -2))} expected false")
    println("${findIfCycleInArray(listOf(2, 1, 5, -1, -2))} expected false")
}

/*
    We are given an array containing positive and negative numbers.
    Suppose the array contains a number ‘M’ at a particular index.
    Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative move backwards ‘M’ indices.
    You should assume that the array is circular

    Write a method to determine if the array has a cycle.
    The cycle should have more than one element
        and should follow one direction which means the cycle should not contain both forward and backward movements.

    Here we can use 2 points, one fast ( +2 ) one slow ( +1 )
    Starting index determines the direction of the cycle (positive or negative)
    Then for each increment we check,
        if pointer is in different direction, move on to next cycle
        else if fast = slow index we found a cycle return true
        else fast+=2 slow++
    We need to maintain a map of indices we have already visited so it is more efficient to start next potential cycles
 */
fun findIfCycleInArray(list: List<Int>): Boolean {
    val indexMap = HashMap<Int,Int>()
    // O(n)
    for(index in list.indices) indexMap[index] = 1


    while(indexMap.isNotEmpty()) {
        val slow = indexMap.keys.first()
        indexMap.remove(slow)
        var fast = slow
        val isPositive = list[slow] > 0

        while(true) {
            fast = getNextIndex(list, fast, isPositive)
            if(fast != -1) fast = getNextIndex(list, fast, isPositive)
            if(fast != -1) indexMap.remove(fast)

            if(slow == -1 || fast == -1 || slow == fast) break
        }
        if(slow != -1 && slow == fast) return true
    }
    return false

}
private fun getNextIndex(list: List<Int>, index: Int, isPositive: Boolean): Int {
    if(index >= list.size || index < 0) return -1
    if(list[index] > 0 != isPositive) return -1

    val next = (index + list[index]) % list.size
    if(next == index) return -1

    return next
}

fun solution(list: List<Int>): Boolean {

    for(i in list.indices) {
        val isPositive = list[i] >= 0
        var slow = i
        var fast = i

        while(true) {
            slow = getNextIndex(list, slow, isPositive)
            fast = getNextIndex(list, fast, isPositive)
            if(fast != -1) fast = getNextIndex(list, fast, isPositive)
            if(slow == -1 || fast == -1 || slow == fast) break
        }
        if(slow != -1 && slow == fast) return true
    }
    return false
}