package day04

import day04.Field.Companion.requiredFields
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
    BirthYear("byr", { it.length == 4 && it.toInt() in 1920..2002 }),
    IssueYear("iyr", { it.length == 4 && it.toInt() in 2010..2020 }),
    ExpirationYear("eyr", { it.length == 4 && it.toInt() in 2020..2030 }),
    Height("hgt", {
        when {
            it.endsWith("cm") -> it.dropLast(2).toInt() in 150..193
            it.endsWith("in") -> it.dropLast(2).toInt() in 59..76
            else -> false
        }
    }),
    HairColor("hcl", { """#[a-z0-9]{6}""".toRegex().matches(it) }),
    EyeColor("ecl", { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") }),
    PassportID("pid", { """\d{9}""".toRegex().matches(it) }),
    CountryID("cid", { true });

    companion object {
        val requiredFields = values().filter { it != CountryID }
    }
}

class Password(private val fieldToValue: Map<Field, String>) : Map<Field, String> by fieldToValue {
    val hasRequiredFields: Boolean = requiredFields.all { it in keys }
    val isValid: Boolean = hasRequiredFields && all { (field, value) -> field.validate(value) }
}

fun parsePasswords(str: String): List<Password> = str
        .split("""(\r?\n){2}""".toRegex()) // separate passwords
        .map { it.replace("""(\r?\n)""".toRegex(), " ") } // join passwords to one line
        .map { line -> // split into key-value-pairs
            Password(line.split(' ').map { keyValuePair ->
                val (key, value) = keyValuePair.split(':')
                enumValues<Field>().find { it.id == key }!! to value
            }.toMap())
        }