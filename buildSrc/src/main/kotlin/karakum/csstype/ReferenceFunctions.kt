package karakum.csstype

internal fun referenceFunctions(): ConversionResult {
    val declarations = sequenceOf(
        function(
            name = "attr",
            returnType = "Content",
            parameters = arrayOf(
                "name" to "String",
            )
        ),
        function(
            name = "env",
            returnType = "T",
            parameters = arrayOf(
                "id" to IDENT,
            )
        ),
        function(
            name = "env",
            returnType = "T",
            parameters = arrayOf(
                "id" to IDENT,
                "fallback" to "T",
            )
        ),
        function(
            name = "url",
            returnType = URL,
            parameters = arrayOf(
                "value" to "String",
            )
        ),
        function(
            name = "var",
            returnType = "T?",
            parameters = arrayOf(
                "name" to "$VARIABLE<T>",
            )
        ),
        function(
            name = "var",
            returnType = "T",
            parameters = arrayOf(
                "name" to "$VARIABLE<T>",
                "fallback" to "T",
            )
        ),
    )

    return ConversionResult(
        name = "Reference.functions",
        body = declarations.joinToString("\n\n"),
    )
}
