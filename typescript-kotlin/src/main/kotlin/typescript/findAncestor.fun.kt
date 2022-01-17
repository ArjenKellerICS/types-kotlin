// Automatically generated - do not modify!

@file:JsModule("typescript")
@file:JsNonModule

package typescript

/**
 * Iterates through the parent chain of a node and performs the callback on each parent until the callback
 * returns a truthy value, then returns that value.
 * If no such value is found, it applies the callback until the parent pointer is undefined or the callback returns "quit"
 * At that point findAncestor returns undefined.
 */
/*
external fun findAncestor<T extends Node>(node: Node | undefined, callback: (element: Node) => element is T): T | undefined
*/

/*
external fun findAncestor(node: Node | undefined, callback: (element: Node) => boolean | "quit"): Node | undefined
*/