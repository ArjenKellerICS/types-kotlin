package karakum.browser

import java.io.File

internal fun browserFunctionTypes(
    definitionsFile: File,
): Sequence<ConversionResult> {
    val content = definitionsFile.readText()

    return Regex("""interface .+? \{\n    \([\s\S]+?\}""")
        .findAll(content)
        .map { it.value }
        .mapNotNull { convertFunctionType(it) }
}

private fun convertFunctionType(
    source: String,
): ConversionResult? {
    val name = source.substringAfter(" ")
        .substringBefore(" ")

    val pkg = when {
        name == "VideoFrameRequestCallback" -> "dom.html"

        name == "RemotePlaybackAvailabilityCallback" -> "remoteplayback"

        name.startsWith("RTC") -> "webrtc"
        name == "VoidFunction" -> "webrtc"

        else -> return null
    }

    var bodySource = source.substringAfter("\n    ")
        .substringBefore(";")
        .replace(": boolean", ": Boolean")
        .replace(": DOMException", ": Throwable /* DOMException */")
        .replace(": DOMHighResTimeStamp", ": HighResTimeStamp")
        .replace("): void", ") -> Unit")

    if ("()" !in bodySource)
        bodySource = bodySource
            .replaceFirst("(", "(\n")
            .replace(", ", ",\n")

    var body = "typealias $name = $bodySource"

    return ConversionResult(
        name = name,
        body = body,
        pkg = pkg
    )
}