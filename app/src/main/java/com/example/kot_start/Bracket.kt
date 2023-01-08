package com.example.kot_start

class Bracket {
    var brackets = mutableListOf<Char>()
    var pairsForBrackets = mutableMapOf(')' to '(')

    public fun RightBracketCombination(bracket: Char) :Boolean {
        if (pairsForBrackets.containsValue(bracket)) {
            brackets.add(bracket)
            return true
        }

        val openBracket =pairsForBrackets[bracket]

        if (brackets.contains(openBracket)) {
            brackets.remove(openBracket)
            return true
        }

        return false
    }
}