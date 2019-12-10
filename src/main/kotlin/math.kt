import java.util.*

fun permutationsOf(list: MutableList<Int>): List<List<Int>> {
    val all = mutableListOf<List<Int>>()
    permutationOf(list, list.size, all)
    return all
}

// O(n!)
private fun permutationOf(input: List<Int>, lastElement: Int, allCombinations: MutableList<List<Int>>) {
    if (lastElement == 1) {
        allCombinations.add(input.toList())
        return
    }
    for (i in 0 until lastElement) {
        Collections.swap(input, i, lastElement - 1) // (remove the ith element)
        permutationOf(input, lastElement - 1, allCombinations)
        Collections.swap(input, i, lastElement - 1) // (restore for the next round)
    }
}