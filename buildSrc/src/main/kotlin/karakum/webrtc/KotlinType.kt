package karakum.webrtc

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "string" to STRING,

    "never" to "Nothing",
    "number" to "Number",
    "void" to UNIT,

    "Date" to "kotlin.js.Date",

    "ArrayBuffer" to "kotlinx.js.ArrayBuffer",
    "ArrayBufferView" to "kotlinx.js.ArrayBufferView",

    "Blob" to "web.buffer.Blob",
    "DOMHighResTimeStamp" to "kotlinx.js.HighResTimeStamp",

    "() => void" to "() -> Unit",
    "(report: RTCStatsReport) => void" to "(report: RTCStatsReport) -> Unit",
)

private val TYPED = setOf(
    "ReadonlyArray",
    "DataChannelEventHandler",
    "DtlsTransportEventHandler",
    "IceTransportEventHandler",
    "PeerConnectionEventHandler",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (type.endsWith(" | null"))
        return kotlinType(type.removeSuffix(" | null"), name) + "?"

    if (" | " in type)
        return "$DYNAMIC /* $type */"

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    for (typedType in TYPED) {
        if (type.startsWith("$typedType<"))
            return "$typedType<${kotlinType(type.removeSurrounding("$typedType<", ">"), name)}>"
    }

    if (type.startsWith("Promise<")) {
        val parameter = kotlinType(type.removeSurrounding("Promise<", ">"), name)
        return "kotlin.js.Promise<$parameter>"
    }

    return type
        .replace("(this: RTCDTMFSender, ", "(")
        .replace("(this: RTCDataChannel, ", "(")
        .replace("(this: RTCDtlsTransport, ", "(")
        .replace("(this: RTCIceTransport, ", "(")
        .replace("(this: RTCPeerConnection, ", "(")
        .replace("(this: RTCSctpTransport, ", "(")
        .replace("(ev: Event) => any", "(event: Event) -> Unit")
        .replace("(ev: ", "(event: ")
        .replace("Event) => any", "Event) -> Unit")
        .replace("MessageEvent", "MessageEvent<*>")
}
