package karakum.browser

private val CLASSES = listOf(
    "File",
    "FileList",
    "FileReader",
)

internal fun fileAliases(): List<ConversionResult> =
    CLASSES.map { name ->
        ConversionResult(
            name = name,
            body = "typealias $name = org.w3c.files.$name",
            pkg = "web.file",
        )
    }
