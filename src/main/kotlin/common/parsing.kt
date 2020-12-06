package common

private val blankLinePattern = """(\r?\n){2}""".toRegex()

fun String.splitOnBlankLines(): List<String> = split(blankLinePattern)