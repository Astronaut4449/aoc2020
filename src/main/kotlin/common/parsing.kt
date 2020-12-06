package common

private val blankLinePattern = """(\r?\n){2}""".toRegex()

fun String.textBlocks(): List<String> = split(blankLinePattern)