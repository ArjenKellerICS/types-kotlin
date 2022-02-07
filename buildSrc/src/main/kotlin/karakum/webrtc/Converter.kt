package karakum.webrtc

import karakum.common.unionBody

internal data class ConversionResult(
    val name: String,
    val body: String,
)

internal fun convertDefinitions(
    source: String,
): Sequence<ConversionResult> {
    val interfaces = source
        .splitToSequence("\ninterface ")
        .drop(1)
        .map { convertInterface(it) }
        .filter { it.name != "Window" }

    val types = source
        .splitToSequence("\ntype ")
        .drop(1)
        .map { it.substringBefore(";\n") }
        .map { convertType(it) }

    return interfaces + types
}

private fun convertInterface(
    source: String,
): ConversionResult {
    val name = source.substringBefore(" ")
        .substringBefore("<")
        .substringBefore("(")
        .substringBefore(":")

    val declaration = source.substringBefore(" {\n")
        .replace(" extends DOMException", " /* : DOMException */")
        .replace(" extends EventInit", " : org.w3c.dom.EventInit")
        .replace(" extends Event", " : org.w3c.dom.events.Event")
        .replace(" extends ", " : ")

    val body = convertMembers(
        name = name,
        source = source.substringAfter(" {\n")
            .let { if (it.startsWith("}")) "" else it }
            .substringBefore("\n}")
    )

    val type = when {
        name == "RTCError"
        -> "class"

        declaration.endsWith(" : org.w3c.dom.events.Event")
        -> "class"

        declaration.endsWith(" : org.w3c.dom.events.EventTarget")
        -> "class"

        else -> "sealed interface"
    }

    return ConversionResult(
        name = name,
        body = "external $type $declaration {\n$body\n}",
    )
}

private fun convertType(
    source: String,
): ConversionResult {
    if (" | '" in source) {
        val (name, body) = source
            .split(" =")

        val values = body.removePrefix(" ")
            .splitToSequence(" | ", "\n    | ")
            .map { it.removeSurrounding("'") }
            .toList()

        return ConversionResult(
            name = name,
            body = unionBody(name, values),
        )
    }

    val (declaration, bodySource) = source
        .split(" = ")

    val name = declaration.substringBefore("<")
    val body = "typealias " +
            declaration.replace("<E extends Event>", "<E>") +
            " = " +
            bodySource
                .replace("this: RTCDataChannel, ", "")
                .replace("this: RTCDtlsTransport, ", "")
                .replace("this: RTCIceTransport, ", "")
                .replace("this: RTCPeerConnection, ", "")
                .replace("ev: Event)", "ev: org.w3c.dom.events.Event)")
                .replace(") => any) | null", ") -> Nothing?)?")

    return ConversionResult(
        name = name,
        body = body,
    )
}