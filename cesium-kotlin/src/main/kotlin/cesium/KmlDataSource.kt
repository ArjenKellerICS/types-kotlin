// Automatically generated - do not modify!

@file:JsModule("cesium")

@file:Suppress(
    "NON_EXTERNAL_DECLARATION_IN_INAPPROPRIATE_FILE",
)

package cesium

/**
 * A [DataSource] which processes Keyhole Markup Language 2.2 (KML).
 *
 * KML support in Cesium is incomplete, but a large amount of the standard,
 * as well as Google's `gx` extension namespace, is supported. See Github issue
 * [#873](https://github.com/CesiumGS/cesium/issues/873) for a
 * detailed list of what is and isn't supported. Cesium will also write information to the
 * console when it encounters most unsupported features.
 *
 * Non visual feature data, such as `atom:author` and `ExtendedData`
 * is exposed via an instance of [KmlFeatureData], which is added to each [Entity]
 * under the `kml` property.
 * ```
 * const viewer = new Viewer('cesiumContainer');
 * viewer.dataSources.add(KmlDataSource.load('../../SampleData/facilities.kmz',
 *      {
 *           camera: viewer.scene.camera,
 *           canvas: viewer.scene.canvas
 *      })
 * );
 * ```
 * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html">Online Documentation</a>
 */
external class KmlDataSource(options: ConstructorOptions) {
    /**
     * @property [camera] The camera that is used for viewRefreshModes and sending camera properties to network links.
     * @property [canvas] The canvas that is used for sending viewer properties to network links.
     * @property [ellipsoid] The global ellipsoid used for geographical calculations.
     *   Default value - [Ellipsoid.WGS84]
     * @property [credit] A credit for the data source, which is displayed on the canvas.
     */
    interface ConstructorOptions {
        var camera: Camera
        var canvas: org.w3c.dom.HTMLCanvasElement
        var ellipsoid: Ellipsoid?
        var credit: Credit?
    }

    /**
     * Gets or sets a human-readable name for this instance.
     * This will be automatically be set to the KML document name on load.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#name">Online Documentation</a>
     */
    var name: String

    /**
     * Gets the clock settings defined by the loaded KML. This represents the total
     * availability interval for all time-dynamic data. If the KML does not contain
     * time-dynamic data, this value is undefined.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#clock">Online Documentation</a>
     */
    var clock: DataSourceClock

    /**
     * Gets the collection of [Entity] instances.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#entities">Online Documentation</a>
     */
    var entities: EntityCollection

    /**
     * Gets a value indicating if the data source is currently loading data.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#isLoading">Online Documentation</a>
     */
    var isLoading: Boolean

    /**
     * Gets an event that will be raised when the underlying data changes.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#changedEvent">Online Documentation</a>
     */
    var changedEvent: Event

    /**
     * Gets an event that will be raised if an error is encountered during processing.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#errorEvent">Online Documentation</a>
     */
    var errorEvent: Event

    /**
     * Gets an event that will be raised when the data source either starts or stops loading.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#loadingEvent">Online Documentation</a>
     */
    var loadingEvent: Event

    /**
     * Gets an event that will be raised when the data source refreshes a network link.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#refreshEvent">Online Documentation</a>
     */
    var refreshEvent: Event

    /**
     * Gets an event that will be raised when the data source finds an unsupported node type.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#unsupportedNodeEvent">Online Documentation</a>
     */
    var unsupportedNodeEvent: Event

    /**
     * Gets whether or not this data source should be displayed.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#show">Online Documentation</a>
     */
    var show: Boolean

    /**
     * Gets or sets the clustering options for this data source. This object can be shared between multiple data sources.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#clustering">Online Documentation</a>
     */
    var clustering: EntityCluster

    /**
     * Gets the credit that will be displayed for the data source
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#credit">Online Documentation</a>
     */
    var credit: Credit

    /**
     * Gets the KML Tours that are used to guide the camera to specified destinations on given time intervals.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#kmlTours">Online Documentation</a>
     */
    var kmlTours: Array<out KmlTour>

    /**
     * Asynchronously loads the provided KML data, replacing any existing data.
     * @param [data] A url, parsed KML document, or Blob containing binary KMZ data or a parsed KML document.
     * @return A promise that will resolve to this instances once the KML is loaded.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#load">Online Documentation</a>
     */
    fun load(
        data: Resource,
        options: LoadOptions? = definedExternally,
    ): kotlin.js.Promise<KmlDataSource>

    /**
     * @property [sourceUri] Overrides the url to use for resolving relative links and other KML network features.
     * @property [clampToGround] true if we want the geometry features (Polygons, LineStrings and LinearRings) clamped to the ground. If true, lines will use corridors so use Entity.corridor instead of Entity.polyline.
     *   Default value - `false`
     * @property [ellipsoid] The global ellipsoid used for geographical calculations.
     *   Default value - [Ellipsoid.WGS84]
     * @property [screenOverlayContainer] A container for ScreenOverlay images.
     */
    interface LoadOptions {
        var sourceUri: Resource?
        var clampToGround: Boolean?
        var ellipsoid: Ellipsoid?
        var screenOverlayContainer: org.w3c.dom.Element?
    }

    fun load(
        data: String,
        options: LoadOptions? = definedExternally,
    ): kotlin.js.Promise<KmlDataSource>

    fun load(
        data: org.w3c.dom.Document,
        options: LoadOptions? = definedExternally,
    ): kotlin.js.Promise<KmlDataSource>

    fun load(
        data: org.w3c.files.Blob,
        options: LoadOptions? = definedExternally,
    ): kotlin.js.Promise<KmlDataSource>

    /**
     * Cleans up any non-entity elements created by the data source. Currently this only affects ScreenOverlay elements.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#destroy">Online Documentation</a>
     */
    fun destroy()

    /**
     * Updates any NetworkLink that require updating.
     * @param [time] The simulation time.
     * @return True if this data source is ready to be displayed at the provided time, false otherwise.
     * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#update">Online Documentation</a>
     */
    fun update(time: JulianDate): Boolean

    companion object {
        /**
         * Creates a Promise to a new instance loaded with the provided KML data.
         * @param [data] A url, parsed KML document, or Blob containing binary KMZ data or a parsed KML document.
         * @param [options] An object specifying configuration options
         * @return A promise that will resolve to a new KmlDataSource instance once the KML is loaded.
         * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#.load">Online Documentation</a>
         */
        fun load(
            data: Resource,
            options: LoadOptions? = definedExternally,
        ): kotlin.js.Promise<KmlDataSource>

        fun load(
            data: String,
            options: LoadOptions? = definedExternally,
        ): kotlin.js.Promise<KmlDataSource>

        fun load(
            data: org.w3c.dom.Document,
            options: LoadOptions? = definedExternally,
        ): kotlin.js.Promise<KmlDataSource>

        fun load(
            data: org.w3c.files.Blob,
            options: LoadOptions? = definedExternally,
        ): kotlin.js.Promise<KmlDataSource>

        /**
         * Initialization options for the `load` method.
         * @property [camera] The camera that is used for viewRefreshModes and sending camera properties to network links.
         * @property [canvas] The canvas that is used for sending viewer properties to network links.
         * @property [sourceUri] Overrides the url to use for resolving relative links and other KML network features.
         * @property [clampToGround] true if we want the geometry features (Polygons, LineStrings and LinearRings) clamped to the ground.
         *   Default value - `false`
         * @property [ellipsoid] The global ellipsoid used for geographical calculations.
         *   Default value - [Ellipsoid.WGS84]
         * @property [credit] A credit for the data source, which is displayed on the canvas.
         * @property [screenOverlayContainer] A container for ScreenOverlay images.
         * @see <a href="https://cesium.com/docs/cesiumjs-ref-doc/KmlDataSource.html#.LoadOptions">Online Documentation</a>
         */
        interface LoadOptions {
            var camera: Camera
            var canvas: org.w3c.dom.HTMLCanvasElement
            var sourceUri: String?
            var clampToGround: Boolean?
            var ellipsoid: Ellipsoid?
            var credit: Credit?
            var screenOverlayContainer: org.w3c.dom.Element?
        }
    }
}

inline fun KmlDataSource(
    block: KmlDataSource.ConstructorOptions.() -> Unit,
): KmlDataSource {
    val options: KmlDataSource.ConstructorOptions = js("({})")
    block(options)
    return KmlDataSource(options)
}