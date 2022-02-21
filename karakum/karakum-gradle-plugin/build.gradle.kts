plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
}

val executableJs by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    compileOnly(kotlin("gradle-plugin"))

    executableJs(
        project(
            mapOf(
                "path" to ":karakum-converter",
                "configuration" to "executableJs"
            )
        )
    )
}

tasks.named<Jar>("jar") {
    dependsOn(":karakum-converter:compileProductionExecutableKotlinJs")

    from(executableJs.asPath)
}

gradlePlugin {
    val karakumConverter by plugins.creating {
        id = "team.karakum.converter"
        implementationClass = "team.karakum.converter.gradle.plugin.KarakumConverterPlugin"
    }
}
