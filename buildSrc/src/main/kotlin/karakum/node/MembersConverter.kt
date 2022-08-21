package karakum.node

private const val STATIC_MARKER = "/* STATIC */\n"

internal fun convertMembers(
    source: String,
): String {
    if (source.isEmpty())
        return ""

    val members = source.removeSuffix(";")
        .replace(";\n  ", "-111-\n  ")
        .replace(";\n}", "-222-\n}")
        .splitToSequence(";\n")
        .map { it.replace("-111-\n  ", ";\n  ") }
        .map { it.replace("-222-\n}", ";\n}") }
        .map { convertMember(it) }
        .toList()

    if (members.none { STATIC_MARKER in it })
        return members.joinToString("\n")

    val groups = members.groupBy { STATIC_MARKER in it }

    val body = groups.getValue(false)
        .joinToString("\n")

    val companionBody = groups.getValue(true)
        .joinToString("\n") {
            it.replace(STATIC_MARKER, "")
        }

    return body + "\n\n" +
            "companion object {\n$companionBody\n}"
}

private fun convertMember(
    source: String,
): String {
    var comment = source.substringBeforeLast("\n */\n", "")
        .ifEmpty { null }
        ?.let { "$it\n */" }

    var body = if (comment != null) {
        source.substringAfter("$comment\n")
    } else source

    if (body.startsWith("// ") || body.startsWith("/* EventEmitter */") || body.startsWith("/** @deprecated ")) {
        comment = (comment ?: "") + "\n    " + body.substringBefore("\n")
        body = body.substringAfter("\n")
    }

    body = body
        .replace("(this: This, ...args: any[]) => Result", "Function<Result> /* this: This */")
        .replace("(...args: TArgs) => R", "Function<R> /* ...args: TArgs */")
        .replace("Pick<ReadableOptions, 'encoding' | 'highWaterMark' | 'objectMode' | 'signal'>", "ReadableOptions")
        .replace(
            "Pick<WritableOptions, 'decodeStrings' | 'highWaterMark' | 'objectMode' | 'signal'>",
            "WritableOptions"
        )

    val content = when {
        body.startsWith("[Symbol.")
        -> "    /* $body */"

        body.startsWith("constructor(") || body.startsWith("new (")
        -> convertConstructor(body)

        isProperty(body)
        -> convertProperty(body)

        else -> convertMethod(body)
    }

    comment = comment?.replace("* /*\n", "* ---\n")

    return sequenceOf(comment, content)
        .filterNotNull()
        .joinToString("\n")
}

private fun isProperty(
    source: String,
): Boolean =
    ("(" !in source) || (source.indexOf(":") < source.indexOf("("))

private fun convertProperty(
    source: String,
): String {
    if (source.startsWith("static "))
        return STATIC_MARKER + convertProperty(source.removePrefix("static "))

    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ")
        .substringBefore(":\n")
        .removeSuffix("?")

    val typeSource = body
        .substringAfter(":")
        .removePrefix(" ")
        .removePrefix("\n")
        .trimIndent()
        .removePrefix("| ")

    var type = kotlinType(typeSource, name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

private fun convertConstructor(
    source: String,
): String {
    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")
        .removeSuffix(")")

    val parameters = when {
        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, false)
            }

        else -> ""
    }

    return "constructor($parameters)"
}

private fun convertMethod(
    source: String,
): String {
    if ("{" in source && !source.startsWith("pipe<")) {
        return "\n// HIDDEN METHOD START\n/*\n$source\n*/\n// HIDDEN METHOD END\n".prependIndent("    ")
    }

    if (source.startsWith("(time?: ["))
        return "    /* $source */"

    if (source.startsWith("static "))
        return STATIC_MARKER + convertMethod(source.removePrefix("static "))

    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "/* native */ invoke" }

    val typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")
        .replace("TArgs : any[]", "TArgs")
        .replace("NodeJS.ArrayBufferView", "ArrayBufferView")
        .replace("NodeJS.WritableStream", "node.WritableStream")
        .replace(" = Buffer", "")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    val parameters = when {
        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(",\n", ", ")
            .joinToString(",\n") {
                convertParameter(it, optional)
            }

        else -> ""
    }

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    if (optional) {
        val comment: String?
        val params: String
        if (parameters.startsWith("this: ")) {
            comment = "/* " + parameters.substringBefore(",\n") + " */"
            params = parameters.substringAfter(",\n")
        } else {
            comment = null
            params = parameters
        }

        return listOfNotNull(
            comment,
            "var $name: (($params) -> $returnType)?"
        ).joinToString("\n")
    }

    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    var result = "fun $typeParameters $name($parameters)$returnDeclaration"
    if (" => " in result)
        result = result
            .replace("value: any", "value: Any")
            .replace(" => void", "-> Unit")

    return result
}

private fun convertParameter(
    source: String,
    lambdaMode: Boolean,
): String {
    val name = source
        .removePrefix("...")
        .substringBefore(": ")
        .removeSuffix("?")

    val type = kotlinType(source.substringAfter(": "), name)
    val declaration = if (source.startsWith("$name?:")) {
        if (lambdaMode) {
            type.addOptionality()
        } else {
            "$type = definedExternally"
        }
    } else type

    var result = "$name: $declaration"
    if (source.startsWith("..."))
        result = "vararg $result"

    return result
}
