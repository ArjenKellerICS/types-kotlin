package karakum.browser

import java.io.File

internal const val EVENT_TYPE = "EventType"

private val PACKAGE_MAP = mapOf(
    "AbortSignal" to "web.abort",
    "Animation" to "web.animations",
    "AudioScheduledSourceNode" to "web.audio",
    "AudioWorkletNode" to "web.audio",
    "BaseAudioContext" to "web.audio",
    "Document" to "dom.events",
    "Element" to "dom.events",
    "EventSource" to "web.sse",
    "FontFaceSet" to "dom.events",
    "GlobalEventHandlers" to "dom.events",
    "HTMLMediaElement" to "dom.events",
    "HTMLVideoElement" to "dom.events",
    "HTMLVideoElement" to "dom.events",
    "IDBDatabase" to "web.idb",
    "IDBOpenDBRequest" to "web.idb",
    "IDBRequest" to "web.idb",
    "IDBTransaction" to "web.idb",
    "MediaDevices" to "media.capture",
    "MediaKeySession" to "media.key",
    "MediaRecorder" to "media.capture",
    "MediaSource" to "media.source",
    "MediaStreamTrack" to "media.stream",
    "Notification" to "web.notifications",
    "PaymentRequest" to "web.payment",
    "Performance" to "web.performance",
    "PermissionStatus" to "web.permissions",
    "PictureInPictureWindow" to "dom.events",
    "RTCDataChannel" to "webrtc",
    "RTCDtlsTransport" to "webrtc",
    "RTCIceTransport" to "webrtc",
    "RTCPeerConnection" to "webrtc",
    "RTCSctpTransport" to "webrtc",
    "RemotePlayback" to "remoteplayback",
    "ScreenOrientation" to "web.screen",
    "ServiceWorker" to "serviceworkers",
    "ServiceWorkerContainer" to "serviceworkers",
    "ServiceWorkerRegistration" to "serviceworkers",
    "ShadowRoot" to "dom.events",
    "SourceBuffer" to "media.source",
    "SourceBufferList" to "media.source",
    "SpeechSynthesis" to "web.speech",
    "TextTrack" to "webvtt",
    "TextTrackCue" to "webvtt",
    "TextTrackList" to "webvtt",
    "VisualViewport" to "dom.events",
    "WebSocket" to "websockets",
    "Window" to "dom.events",
    "WindowEventHandlers" to "dom.events",
    "XMLHttpRequest" to "web.xhr",
)

private data class EventData(
    val name: String,
    val type: String,
    val pkg: String,
) {
    val typeName: String = type.substringBefore("<")
}

private val ADDITIONAL_EVENTS = listOf(
    EventData(
        name = "webkitfullscreenchange",
        type = "Event",
        pkg = "dom.events", // ???
    ),
)

private val EXCLUDED = setOf(
    "MessageEvent",

    // deprecated
    "AudioProcessingEvent",
    "MediaRecorderErrorEvent",
    "SecurityPolicyViolationEvent",
)

internal fun eventDeclarations(
    content: String,
): List<ConversionResult> =
    eventTypes(content)
        .plus(eventAliases())
        .plus(eventPlaceholders())
        .plus(AnimationEvent())
        .plus(TransitionEvent())

private fun eventAliases(): List<ConversionResult> =
    EVENT_DATA.mapNotNull { info ->
        val alias = info.alias
            ?: return@mapNotNull null

        val name = info.name
        val initBody = when (name) {
            "TouchEvent", // TEMP
            "BeforeUnloadEvent",
            -> null

            else -> "typealias ${name}Init = ${alias}Init"
        }

        val body = listOfNotNull(
            initBody,
            "typealias $name = $alias"
        ).joinToString("\n")

        ConversionResult(
            name = name,
            body = body,
            pkg = info.pkg,
        )
    }

private fun eventPlaceholders(): List<ConversionResult> =
    EVENT_DATA
        .filter { it.missed }
        .mapNotNull { info ->
            val name = info.name
            val body = """
            import web.events.Event    
                
            sealed external class $name : Event {
                companion object
            }            
            """.trimIndent()

            ConversionResult(
                name = name,
                body = body,
                pkg = info.pkg,
            )
        }

private fun eventTypes(
    content: String,
): List<ConversionResult> =
    Regex("""interface .+?EventMap \{\n    "[\s\S]+?\n\}""")
        .findAll(content)
        .flatMap { parseEvents(it.value) }
        .filter { it.name != "orientationchange" }
        .plus(ADDITIONAL_EVENTS)
        .distinct()
        .groupBy { it.typeName }
        .filter { it.key !in EXCLUDED }
        .map { it.value }
        .flatMap { it.groupBy { it.pkg }.values }
        .map { items -> eventTypes(items) }

private fun eventTypes(
    items: List<EventData>,
): ConversionResult {
    val firstItem = items.first()
    val typeName = firstItem.typeName

    val info = EVENT_INFO_MAP.getValue(typeName)
    val imports = "import " + (info.alias ?: info.fqn)

    val members = items
        .sortedBy { it.name }
        .map { (name, type) ->
            val memberName = EVENT_CORRECTION_MAP
                .getOrDefault(name, name)
                .toUpperCase()

            """
            inline val $typeName.Companion.$memberName : $EVENT_TYPE<$type>
                get() = $EVENT_TYPE("$name")                        
            """.trimIndent()
        }

    val body = sequenceOf(imports)
        .plus(members)
        .joinToString("\n\n")

    val pkg = firstItem.pkg
        .takeIf { it.isNotEmpty() }

    return ConversionResult(
        name = "$typeName.types",
        body = body,
        pkg = pkg,
    )
}

private fun parseEvents(
    source: String,
): Sequence<EventData> {
    val mapId = source.substringBefore("EventMap")
        .substringAfterLast(" ")

    return source.splitToSequence("\n")
        .mapNotNull { parseEventData(it, mapId) }
}

private fun parseEventData(
    source: String,
    mapId: String,
): EventData? {
    if (!source.endsWith(";")) return null
    if (mapId == "AbortSignal") return null
    if (mapId == "Performance") return null

    val (name, type) = source
        .removeSurrounding("    \"", ";")
        .split("\": ", "<")

    val pkg = if (type == "Event") {
        PACKAGE_MAP.getValue(mapId)
            .ifEmpty { "org.w3c.dom.events" }
    } else ""

    return EventData(
        name = name,
        type = type,
        pkg = pkg,
    )
}
