// Automatically generated - do not modify!

package dom

sealed external class Attr :
    Node {
    val localName: String
    val name: String
    val namespaceURI: String?

    // TEMP: WA for old `Node`
    // override val ownerDocument: Document
    val ownerElement: Element?
    val prefix: String?
    val specified: Boolean
    var value: String
}
