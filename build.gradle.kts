plugins {
    kotlin("jvm") version "1.4.20"
}

repositories {
    jcenter()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    // Create tasks for every day
    (1..25).map { "$it".padStart(2, '0') }.forEach { i ->
        register<Day>("day$i") {
            group = "advent-of-code"
            classpath(sourceSets.main.get().runtimeClasspath)
            mainClass.set("day$i.Day${i}Kt")
            doFirst {
                if (inputPath != null) {
                    args = mutableListOf(inputPath)
                }
            }
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

/**
 * Task for executing a day of advent of code.
 */
open class Day : JavaExec() {
    var inputPath: String? = null

    @Option(option = "input", description = "path to input for day")
    fun inputPath(path: String) {
        this.inputPath = path
    }
}