package karakum.csstype

import karakum.common.sealedUnionBody
import karakum.common.unionBody

internal const val GEOMETRY_POSITION = "GeometryPosition"
internal val GEOMETRY_POSITION_VALUES = listOf(
    "bottom",
    "center",
    "left",
    "right",
    "top",
)

private val GEOMETRY_POSITION_BODY = sealedUnionBody(
    name = GEOMETRY_POSITION,
    values = GEOMETRY_POSITION_VALUES,
)

internal fun GeometryPosition(
    parentProvider: ParentProvider,
): ConversionResult {
    val parentTypes = parentProvider.parentTypes
        .joinToString(",\n")

    val body = GEOMETRY_POSITION_BODY
        .replaceFirst(GEOMETRY_POSITION, GEOMETRY_POSITION + " :\n" + parentTypes)

    return ConversionResult(
        name = GEOMETRY_POSITION,
        body = body,
    )
}
