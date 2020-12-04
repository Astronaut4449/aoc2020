package day04

import java.io.File

fun main(args: Array<String>) {
    val input = File(args.firstOrNull() ?: "input/day04.txt").readText()
    val passwords = parsePasswords(input)

    val part1 = passwords.count { it.hasRequiredFields }
    println("Part 1: $part1")

    val part2 = passwords.count { it.isValid }
    println("Part 2: $part2")
}

enum class Field(val id: String, val validate: (String) -> Boolean) {
    BirthYear("byr", { it.toInt() in 1920..2002 }),
    IssueYear("iyr", { it.toInt() in 2010..2020 }),
    ExpirationYear("eyr", { it.toInt() in 2020..2030 }),
    Height("hgt", {
        when {
            it.endsWith("cm") -> it.dropLast(2).toInt() in 150..193
            it.endsWith("in") -> it.dropLast(2).toInt() in 59..76
            else -> false
        }
    }),
    HairColor("hcl", { hexColorPattern.matches(it) }),
    EyeColor("ecl", { it in colors }),
    PassportID("pid", { nineDigitPattern.matches(it) }),
    CountryID("cid", { true });

    companion object {
        private val hexColorPattern = """#[a-z0-9]{6}""".toRegex()
        private val nineDigitPattern = """\d{9}""".toRegex()
        private val colors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

        fun ofId(id: String) = values().first { it.id == id }
    }
}

class Password(private val fieldToValue: Map<Field, String>) : Map<Field, String> by fieldToValue {
    companion object {
        val requiredFields = Field.values().filter { it != Field.CountryID }
    }

    val hasRequiredFields: Boolean = requiredFields.all { it in keys }
    val isValid: Boolean = hasRequiredFields && all { (field, value) -> field.validate(value) }
}

fun parsePasswords(str: String): List<Password> = str
        .split("""(\r?\n){2}""".toRegex()) // separate passwords
        .map { it.replace("""(\r?\n)""".toRegex(), " ") } // join passwords to one line
        .map { line -> // split into key-value-pairs
            line.split(' ').map { joinedKeyValuePair ->
                val (key, value) = joinedKeyValuePair.split(':')
                Field.ofId(key) to value
            }
        }
        .map { pairs -> Password(pairs.toMap()) }