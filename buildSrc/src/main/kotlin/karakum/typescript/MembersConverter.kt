package karakum.typescript

private val IGNORED = setOf(
    "Iterator",
    "JSDocAugmentsTag",
    "JSDocImplementsTag",
)

private const val SIGNATURE_TO_SIGNATURE = """ & {
    typeArguments?: NodeArray<TypeNode>;
} | undefined;"""

val SIGNATURE_TO_SIGNATURE_REPLACEMENT = SIGNATURE_TO_SIGNATURE
    .replace("\n    ", " ")
    .replace(";\n", "; ")

internal fun convertMembers(
    name: String,
    source: String,
    typeConverter: TypeConverter,
): String {
    if (source.isEmpty())
        return ""

    if (name in IGNORED) return "    /*\n$source\n    */"

    val thisReplacement = when (name) {
        "ESMap" -> "ESMap<K, V>"
        "ModeAwareCache" -> "ModeAwareCache<T>"
        "Set" -> "Set<T>"
        else -> "<this>"
    }

    return source.trimIndent()
        .replace(";\n * ", ";---\n * ")
        .replace(RELATION_CACHE_SIZES_BODY, RELATION_CACHE_SIZES)
        .replace(SIGNATURE_TO_SIGNATURE, SIGNATURE_TO_SIGNATURE_REPLACEMENT)
        .replace(": this;", ": $thisReplacement;")
        .removeSuffix(";")
        .splitToSequence(";\n")
        .map { convertMember(it, typeConverter) }
        .joinToString("\n")
        .replace(";---\n * ", ";\n * ")
}

private fun convertMember(
    source: String,
    typeConverter: TypeConverter,
): String {
    if (source.startsWith("[") || source.startsWith("\" __sortedArrayBrand\""))
        return "    // $source"

    val comment = source.substringBeforeLast("\n", "")
        .ifEmpty { null }

    val body = source.substringAfterLast("\n")
        .replace("(...args: any[]) => void", "Function<$UNIT>")

    if (comment != null && "* @deprecated" in comment)
        return ""

    val content = if (isProperty(body)) {
        convertProperty(body, typeConverter)
    } else {
        convertMethod(body)
    }

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
    typeConverter: TypeConverter,
): String {
    val modifier = if (source.startsWith("readonly ")) "val" else "var"
    val body = source.removePrefix("readonly ")

    val name = body.substringBefore(": ").removeSuffix("?")
    var type = typeConverter.convert(body.substringAfter(": "), name)

    if (body.startsWith("$name?"))
        type = type.addOptionality()

    return "$modifier $name: $type"
}

internal fun convertMethod(
    source: String,
): String {
    val name = source.substringBefore("(")
        .substringBefore("<")
        .removeSuffix("?")
        .ifEmpty { "/* native */ invoke" }

    val typeParameters = source.substringBefore("(")
        .substringAfter("<", "")
        .let { if (it.isNotEmpty()) "<$it" else "" }
        .replace(" extends ", " : ")

    val parametersSource = source
        .substringAfter("(")
        .substringBeforeLast("): ")

    val optional = source.startsWith("$name?")
    val parameters = when {
        parametersSource == "action: (value: V, key: K) => void" || parametersSource == "action: (value: T, key: T) => void"
        -> parametersSource.replace(" => void", " -> $UNIT")

        parametersSource == "cb: (elem: T, key: string, mode: ModuleKind.CommonJS | ModuleKind.ESNext | undefined) => void"
        -> "cb: (elem: T, key: String, mode: ResolutionMode?) -> Unit"

        parametersSource.isNotEmpty()
        -> parametersSource
            .splitToSequence(", ")
            .joinToString(",\n") {
                convertParameter(it, optional)
            }

        else -> ""
    }

    val returnType = kotlinType(source.substringAfterLast("): "), name)

    if (optional)
        return "val $name: (($parameters) -> $returnType)?"

    val isOperator = when (name) {
        "get" -> parameters.count { it == ':' } == 1
        "set" -> parameters.count { it == ':' } == 2
        else -> false
    }
    val keyword = if (isOperator) "operator fun" else "fun"

    val returnDeclaration = if (returnType != UNIT) {
        ": $returnType"
    } else ""

    var result = "$keyword $typeParameters $name($parameters)$returnDeclaration"
    if (name == "createToken" && typeParameters.isNotEmpty())
        result = "    // TODO: restore after alias update\n    // $result"

    return result
}

internal fun convertParameter(
    source: String,
    lambdaMode: Boolean,
): String {
    when (source) {
        "...values: T[]" -> return "vararg values: T"
        "...args: any[]" -> return "/* vararg */ args: $DYNAMIC"
    }

    val name = source
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

    return "$name: $declaration"
}
