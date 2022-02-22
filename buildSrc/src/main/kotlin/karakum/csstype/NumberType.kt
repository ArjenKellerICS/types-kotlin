package karakum.csstype

internal const val NUMBER = "Number"
internal const val NUMBER_TYPE = "NumberType"

internal fun NumberType(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider
        .parentTypes.sorted()
        .joinToString(",\n")

    val body = """
        sealed external interface $NUMBER_TYPE:
        $parentTypes
        
        ${factory("number", NUMBER_TYPE, arrayOf("value" to "Double"))}
    """.trimIndent()

    return ConversionResult(NUMBER, body)
}
