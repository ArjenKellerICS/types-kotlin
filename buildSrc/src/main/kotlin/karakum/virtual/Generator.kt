package karakum.virtual

import karakum.common.GENERATOR_COMMENT
import java.io.File

private val DEFAULT_IMPORTS = listOf(
    "Promise" to "kotlin.js.Promise",
    "RegExp" to "kotlin.js.RegExp",

    "ReadonlyArray" to "kotlinx.js.ReadonlyArray",
    "Record" to "kotlinx.js.Record",
    "JsTuple2" to "kotlinx.js.JsTuple2",
    "Void" to "kotlinx.js.Void",
)

fun generateKotlinDeclarations(
    coreDefinitionsFile: File,
    sourceDir: File,
) {
    val targetDir = sourceDir.resolve("tanstack/virtual/core")
        .also { it.mkdirs() }

    for ((name, body) in convertDefinitions(coreDefinitionsFile)) {
        val annotations = when {
            "external val " in body || "external object " in body || "external fun " in body || "external class " in body
            -> "@file:JsModule(\"${Package.VIRTUAL_CORE.moduleName}\")\n@file:JsNonModule"

            else -> ""
        }

        targetDir.resolve("${name}.kt")
            .writeText(fileContent(Package.VIRTUAL_CORE, annotations, body))
    }
}

private fun fileContent(
    pkg: Package,
    annotations: String,
    body: String,
): String {
    val defaultImports = DEFAULT_IMPORTS
        .filter { it.first in body }
        .map { "import ${it.second}" }
        .joinToString("\n")

    return sequenceOf(
        "// $GENERATOR_COMMENT",
        annotations,
        pkg.pkg,
        defaultImports,
        body,
    ).filter { it.isNotEmpty() }
        .joinToString("\n\n")
}
