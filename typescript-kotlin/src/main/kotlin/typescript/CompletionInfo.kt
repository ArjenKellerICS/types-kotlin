// Automatically generated - do not modify!

package typescript

external interface CompletionInfo {
    /*
    /** Not true for all global completions. This will be true if the enclosing scope matches a few syntax kinds. See `isSnippetScope`. */
    isGlobalCompletion: boolean;
    isMemberCompletion: boolean;
    /**
     * In the absence of `CompletionEntry["replacementSpan"], the editor may choose whether to use
     * this span or its default one. If `CompletionEntry["replacementSpan"]` is defined, that span
     * must be used to commit that completion entry.
     */
    optionalReplacementSpan?: TextSpan;
    /**
     * true when the current location also allows for a new identifier
     */
    isNewIdentifierLocation: boolean;
    /**
     * Indicates to client to continue requesting completions on subsequent keystrokes.
     */
    isIncomplete?: true;
    entries: CompletionEntry[];
    */
}
