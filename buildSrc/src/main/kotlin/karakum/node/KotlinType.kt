package karakum.node

internal const val DYNAMIC = "dynamic"
internal const val UNIT = "Unit"

internal const val STRING = "String"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "{}" to "Any",

    "boolean" to "Boolean",
    "true" to "Boolean /* true */",

    "string" to STRING,

    "never" to "Nothing",
    "unknown" to "Any?",

    "number" to "Number",
    "bigint" to "BigInt",

    "void" to UNIT,
    "null" to "Void",
    "undefined" to "Void",

    "Function" to "Function<*>",

    "Date" to "kotlin.js.Date",

    "unique symbol" to "Symbol",
    "string | symbol" to "String /* | Symbol */",
    "Array<string | symbol>" to "ReadonlyArray<String /* | Symbol */>",

    "boolean | Promise<boolean>" to "PromiseResult<Boolean>",

    "[number, number]" to "JsPair<Int, Int>",
    "ReadonlySet<string>" to "JsSet<out String>",

    "Uint8Array | ReadonlyArray<number>" to "Uint8Array /* | ReadonlyArray<number> */",
    "NodeJS.EventEmitter" to "node.events.IEventEmitter",
    "NodeJS.ArrayBufferView" to "ArrayBufferView",
    "NodeJS.Platform" to "node.process.Platform",
    "NodeJS.ReadableStream" to "node.ReadableStream",
    "NodeJS.WritableStream" to "node.WritableStream",

    "Blob" to "org.w3c.files.Blob",

    "() => void" to "() -> Unit",
    "(error?: Error) => void" to "(error: Error?) -> Unit",
    "(error: Error | null) => void" to "(error: Error?) -> Unit",
    "(error?: Error | null) => void" to "(error: Error?) -> Unit",
    "(error: Error | null | undefined) => void" to "(error: Error?) -> Unit",

    "Buffer" to "node.buffer.Buffer",
    "BufferEncoding" to "node.buffer.BufferEncoding",
    "ReadableStream" to "node.stream.ReadableStream",
    "stream.Duplex" to "Duplex",
    "symlink.Type" to "SymlinkType",

    "ObjectEncodingOptions | BufferEncoding" to "node.buffer.BufferEncoding /* ObjectEncodingOptions | BufferEncoding */",

    "AsyncIterable<FileChangeInfo<string>> | AsyncIterable<FileChangeInfo<Buffer>>" to
            "AsyncIterable<FileChangeInfo<Any /* string | Buffer */>>",

    "NodeJS.Dict<NetworkInterfaceInfo[]>" to "Dict<ReadonlyArray<NetworkInterfaceInfo>>",
    "NodeJS.ReadOnlyDict<Socket[]>" to "ReadOnlyDict<ReadonlyArray<Socket>>",
    "NodeJS.ReadOnlyDict<IncomingMessage[]>" to "ReadOnlyDict<ReadonlyArray<IncomingMessage>>",

    "streamWeb.ReadableStream" to "node.stream.ReadableStream",
    "streamWeb.WritableStream" to "node.stream.WritableStream",
    "net.SocketConstructorOpts" to "node.net.SocketConstructorOpts",

    "-1 | 0 | 1" to "Int /* -1 | 0 | 1 */",

    "UrlWithStringQuery" to "Url /* UrlWithStringQuery */",

    "PathLike | FileHandle" to "PathLike /* | FileHandle */",

    // TEMP
    "Require" to "$DYNAMIC /* Require */",
    "ClientRequestArgs" to "$DYNAMIC /* ClientRequestArgs */",
    "Context" to "$DYNAMIC /* Context */",
    "EventLoopUtilityFunction" to "Function<*> /* EventLoopUtilityFunction */",
)

private val TYPED = setOf(
    "ReadonlyArray",
)

internal fun kotlinType(
    type: String,
    name: String,
): String {
    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    if (" is " in type)
        return "Boolean /* $type */"

    if (name == "port" && type.startsWith("string | number"))
        return STRING

    if (type.endsWith(" | undefined"))
        return kotlinType(type.removeSuffix(" | undefined"), name)

    if (type.endsWith(" | null")) {
        var resultType = kotlinType(type.removeSuffix(" | null"), name)
        if (!resultType.startsWith(DYNAMIC)) {
            if (" /* " in resultType) {
                resultType = resultType.replace(" /* ", "? /* ")
            } else {
                resultType += "?"
            }
        }

        return resultType
    }

    if (" | " in type && !type.startsWith("Promise<")) {
        if ("| BufferEncoding" in type)
            return "BufferEncoding /* $type */"

        return "Any /* $type */"
    }

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    for (typedType in TYPED) {
        if (type.startsWith("$typedType<"))
            return "$typedType<${kotlinType(type.removeSurrounding("$typedType<", ">"), name)}>"
    }

    if (type.startsWith("Promise<")) {
        val typeParameter = type.removeSurrounding("Promise<", ">")
        val parameter = when (typeParameter) {
            "unknown" -> "*"
            "void" -> "Void"
            else -> kotlinType(typeParameter, name)
        }

        return "Promise<$parameter>"
    }

    if (name == "event" && type.startsWith("'"))
        return "$EVENT.${eventName(type.removeSurrounding("'"))}"

    // TODO: remove
    if ("IterableIterator" in type)
        return "$DYNAMIC /* $type */"

    if (type.startsWith("'"))
        return "$STRING /* $type */"

    if (type.startsWith("{"))
        return "$DYNAMIC /* $type */"
            .prependIndent("    ")

    if (" & " in type)
        return (type.substringBefore(" & ") + " /* " + type.substringAfter(" & ") + " */")
            .prependIndent("    ")
            .removePrefix("    ")

    return type
        .replace("(...args: any[]) => void", "Function<Unit>")
        .replace("<string>", "<String>")
        .replace(": number", ": Number")
        .replace(": string", ": String")
        .replace(": boolean", ": Boolean")
        .replace("=> string", "-> String")
        .replace("=> number", "-> Number")
        .replace("=> void", "-> Unit")
        .replace("=> string", "-> String")
        .replace("=> Socket", "-> Socket")
        .replace(": any", ": Any")
        .replace(": unknown", ": Any?")

        // TEMP
        .replace("number)", "Number)")
        .replace("string)", "String)")
        .replace(": stream.Readable)", ": Readable)")
        .replace("stream.Duplex)", "Duplex)")
}
