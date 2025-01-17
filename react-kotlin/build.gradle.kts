plugins {
    id("io.github.turansky.kfc.library")
    id("io.github.turansky.seskar")
    `react-declarations`
}

val reactTypesVersion = property("react-types.version") as String

dependencies {
    implementation(npm("@types/react", reactTypesVersion))

    implementation(enforcedPlatform(kotlinwBom()))
    implementation(kotlinw("browser"))
    implementation(kotlinw("react-core"))
}
