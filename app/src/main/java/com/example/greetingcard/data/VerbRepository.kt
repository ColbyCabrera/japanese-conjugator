package com.example.greetingcard.data

class VerbRepository {

    private enum class VerbType {
        U_VERB,
        RU_VERB,
        IRREGULAR_VERB
    }

    fun conjugate(verb: String, conjugationType: String): String {
        val verbType = getVerbType(verb)
        return when (conjugationType) {
            "Plain Form (Present)" -> verb
            "Masu Form (Present)" -> conjugateMasu(verb, verbType)
            "Nai Form (Negative)" -> conjugateNai(verb, verbType)
            "Te Form" -> conjugateTe(verb, verbType)
            else -> "Unsupported conjugation type"
        }
    }

    private fun getVerbType(verb: String): VerbType {
        if (verb == "する" || verb == "来る") {
            return VerbType.IRREGULAR_VERB
        }
        if (verb.endsWith("る")) {
            val stem = verb.substring(0, verb.length - 1)
            val lastChar = stem.last()
            if (lastChar == 'い' || lastChar == 'え') {
                return VerbType.RU_VERB
            }
        }
        return VerbType.U_VERB
    }

    private fun conjugateMasu(verb: String, verbType: VerbType): String {
        return when (verbType) {
            VerbType.U_VERB -> {
                val stem = verb.substring(0, verb.length - 1)
                val lastChar = verb.last()
                val newLastChar = when (lastChar) {
                    'u' -> 'i'
                    'く' -> 'き'
                    'ぐ' -> 'ぎ'
                    'す' -> 'し'
                    'つ' -> 'ち'
                    'ぬ' -> 'に'
                    'ぶ' -> 'び'
                    'む' -> 'み'
                    'る' -> 'り'
                    else -> lastChar
                }
                "$stem$newLastChar" + "ます"
            }
            VerbType.RU_VERB -> verb.substring(0, verb.length - 1) + "ます"
            VerbType.IRREGULAR_VERB -> when (verb) {
                "する" -> "します"
                "来る" -> "来ます"
                else -> ""
            }
        }
    }

    private fun conjugateNai(verb: String, verbType: VerbType): String {
        return when (verbType) {
            VerbType.U_VERB -> {
                val stem = verb.substring(0, verb.length - 1)
                val lastChar = verb.last()
                val newLastChar = when (lastChar) {
                    'う' -> 'わ'
                    'く' -> 'か'
                    'ぐ' -> 'が'
                    'す' -> 'さ'
                    'つ' -> 'た'
                    'ぬ' -> 'な'
                    'ぶ' -> 'ば'
                    'む' -> 'ま'
                    'る' -> 'ら'
                    else -> lastChar
                }
                "$stem$newLastChar" + "ない"
            }
            VerbType.RU_VERB -> verb.substring(0, verb.length - 1) + "ない"
            VerbType.IRREGULAR_VERB -> when (verb) {
                "する" -> "しない"
                "来る" -> "来ない"
                else -> ""
            }
        }
    }

    private fun conjugateTe(verb: String, verbType: VerbType): String {
        if (verb == "行く") return "行って" // Special case

        return when (verbType) {
            VerbType.U_VERB -> {
                val stem = verb.substring(0, verb.length - 1)
                when (verb.last()) {
                    'く' -> stem + "いて"
                    'ぐ' -> stem + "いで"
                    'む', 'ぶ', 'ぬ' -> stem + "んで"
                    'る', 'う', 'つ' -> stem + "って"
                    'す' -> stem + "して"
                    else -> ""
                }
            }
            VerbType.RU_VERB -> verb.substring(0, verb.length - 1) + "て"
            VerbType.IRREGULAR_VERB -> when (verb) {
                "する" -> "して"
                "来る" -> "来て"
                else -> ""
            }
        }
    }
}
