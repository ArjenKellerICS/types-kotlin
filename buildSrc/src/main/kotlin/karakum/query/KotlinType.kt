package karakum.query

private const val PROMISE = "Promise"

private val CLASS_REGEX = Regex("""[\w\d]+""")

internal const val DYNAMIC = "dynamic"

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",

    "boolean" to "Boolean",
    "string" to "String",

    "void" to "Unit",

    "Date" to "kotlin.js.Date",

    "null" to "Void",
    "undefined" to "Void",

    "true" to "True",
    "false" to "False",

    "unknown | undefined" to "Any?",
    "TError | null" to "TError?",
    "boolean | T | undefined" to "T?",

    "TData | Promise<TData>" to "PromiseResult<TData>",
    "() => unknown | Promise<unknown>" to "() -> Promise<*>?",

    "Mutation<any, any>" to "Mutation<*, *, *, *>",
    "MutationState" to "MutationState<*, *, *, *>",
    "MutationObserver<any, any, any>" to "MutationObserver<*, *, *, *>",
    "MutationObserver<any, any, any, any>" to "MutationObserver<*, *, *, *>",

    "QueryState" to "QueryState<*, *>",
    "QueryOptions<any, any>" to "QueryOptions<*, *, *, *>",
    "QueryObserverOptions[]" to "ReadonlyArray<QueryObserverOptions<*, *, *, *, *>>",
    "UseQueryOptions[]" to "ReadonlyArray<UseQueryOptions<*, *, *, *>>",
    "UseQueryResult[]" to "ReadonlyArray<UseQueryResult<*, *>>",
    "RefetchOptions & RefetchQueryFilters<TPageData>" to "RefetchOptions /* & RefetchQueryFilters<TPageData> */",

    "QueriesResults<T>" to "QueriesResults<T,*,*>",
    "QueryTypeFilter | 'none'" to "QueryTypeFilter /* | 'none' */",

    "InvalidateQueryFilters" to "InvalidateQueryFilters<*>",
    "RefetchQueryFilters" to "RefetchQueryFilters<*>",
    "ResetQueryFilters" to "ResetQueryFilters<*>",

    "InvalidateQueryFilters<TPageData>" to "InvalidateQueryFilters<TPageData>",
    "RefetchQueryFilters<TPageData>" to "RefetchQueryFilters<TPageData>",
    "ResetQueryFilters<TPageData>" to "ResetQueryFilters<TPageData>",

    "typeof QueryObserver" to "JsClass<QueryObserver<*, *, *, *, *>>",
    "TData | InitialDataFunction<TData>" to "InitialDataFunction<TData> /* | TData */",
    "TQueryData | PlaceholderDataFunction<TQueryData>" to "PlaceholderDataFunction<TQueryData> /* | TQueryData */",
    "boolean | ((oldData: TData | undefined, newData: TData) => TData)" to
            "(oldData: TData?, newData: TData) -> TData /* | boolean */",
    "boolean | 'always' | ((query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) => boolean | 'always')" to
            "(query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) -> Boolean /* | boolean | 'always' */",
    "number | false | ((data: TData | undefined, query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) => number | false)" to
            "(data: TData?, query: Query<TQueryFnData, TError, TQueryData, TQueryKey>) -> JsDuration /* | number | false */",

    "Parameters<T>" to "ReadonlyArray<*> /* Parameters<T> */",

    "((value: QueryErrorResetBoundaryValue) => React.ReactNode) | React.ReactNode" to
            "(value: QueryErrorResetBoundaryValue) -> react.ReactNode",

    "boolean | ((error: TError) => boolean)" to "(error: TError) -> Boolean",

    "({ children, }: QueryErrorResetBoundaryProps) => JSX.Element" to
            "react.FC<QueryErrorResetBoundaryProps>",
    "({ client, children, context, contextSharing, }: QueryClientProviderProps) => JSX.Element" to
            "react.FC<QueryClientProviderProps>",
    "({ children, options, state }: HydrateProps) => React.ReactElement<any, string | React.JSXElementConstructor<any>>" to
            "react.FC<HydrateProps>",

    "import(\"./query\").Query<unknown, unknown, unknown, import(\"./types\").QueryKey>" to
            "Query<*, *, *, QueryKey>",
)

private val SAFE_PREFIXES = setOf(
    "Action",
    "Ensured",
    "Get",
    "Fetch",
    "Infinite",
    "Mutate",
    "Mutation",
    "Query",
    "Retry",
    "Updater",
    "Use",
)

internal fun kotlinType(
    type: String,
    name: String? = null,
): String {
    if (type.startsWith("import(\"@tanstack/query-core\")."))
        return kotlinType(type.removePrefix("import(\"@tanstack/query-core\")."), name)

    // TEMP
    if (type.startsWith("useQueries<T extends"))
        return "QueriesResults<T, *, *>"

    if (STANDARD_TYPE_MAP.containsKey(type))
        return STANDARD_TYPE_MAP.getValue(type)

    if (type == "number")
        return when {
            name == null -> "Int"
            name.endsWith("At") -> "EpochTimeStamp"
            name.endsWith("Time") -> "JsDuration"
            name == "timeout" -> "JsDuration"

            else -> "Int"
        }

    if (type.startsWith("'")) {
        return when (name) {
            "type" -> "Type /* $type */"
            "status" -> "QueryStatus /* $type */"
            "_optimisticResults" -> "String /* $type */"
            else -> TODO("Name - $name, Type - $type")
        }
    }

    if (type == "unknown") {
        return when (name) {
            "pages",
            -> "Page"

            "pageParam",
            "pageParams",
            -> "PageParam"

            else -> "Any?"
        }
    }

    if (" is " in type)
        return "Boolean /* $type */"

    if (type.isClassLike())
        return type

    if (type.startsWith("React."))
        return type.replace("React.", "react.")
            .replace(" | undefined>", "?>")
            .replace("<boolean>", "<Boolean>")

    if (type.startsWith("Omit<"))
        return kotlinType(type.removePrefix("Omit<").substringBefore(", '"))

    if (SAFE_PREFIXES.any { type.startsWith(it) } && !type.startsWith("QueryKey |") && " | TOptions" !in type)
        return type
            .replace("<any", "<*")
            .replace(", any", ", *")
            .replace("<unknown", "<*")
            .replace(", unknown", ", *")
            .replace(" | undefined", "?")
            .let { t ->
                when {
                    t.startsWith("Action<") && t.count { it == ',' } == 3
                    -> t.replace("Action<", "MutationAction<")

                    t == "QueryObserverOptions<*, TError>"
                    -> t.replace(">", ", *, *, *>")

                    t == "QueryObserverOptions<*, *, *, *>"
                    -> t.replace(">", ", *>")

                    t.startsWith("QueryBehavior<T") && t.count { it == ',' } == 2
                    -> t.replace(Regex(">$"), ", *>")

                    t.startsWith("Query<") && t.count { it == ',' } == 2
                    -> t.replace(Regex("(>\\??)$"), ", *$1")

                    else -> t
                }
            }
            .let { t ->
                if (t.endsWith("[]")) {
                    var itemType = kotlinType(type.removeSuffix("[]"))
                    itemType = when (itemType) {
                        "Mutation" -> itemType + "<*, *, *, *>"
                        "Query" -> itemType + "<*, *, *, *>"
                        "QueryObserverResult" -> itemType + "<*, *>"
                        else -> itemType
                    }

                    "ReadonlyArray<$itemType>"
                } else t
            }

    if (type.startsWith("() => "))
        return "() -> ${kotlinType(type.removePrefix("() => "))}"

    if (type.endsWith(" | undefined") && type.indexOf("|") == type.lastIndexOf("|")) {
        val baseType = kotlinType(type.removeSuffix(" | undefined"), name)
        return if (baseType != DYNAMIC) baseType + "?" else baseType
    }

    if (type.endsWith("[]"))
        return "ReadonlyArray<${kotlinType(type.removeSuffix("[]"), name)}>"

    if (type.startsWith("[") && type.endsWith("]")) {
        val (a, b) = type.removeSurrounding("[", "]")
            .split(", ")
            .map { kotlinType(it) }

        return "JsTuple2<$a, $b>"
    }

    val promiseResult = type.removeSurrounding("Promise<", ">")
    if (promiseResult != type)
        return "$PROMISE<${kotlinType(promiseResult)}>"

    if (type == "Element | string")
        return kotlinType("Element")

    if (type.startsWith("<TPageData>"))
        return kotlinType(type.removePrefix("<TPageData>"))

    if (type.startsWith("(")) {
        if ("?" in type) {
            if (type.count { it == '?' } == 1) {
                val sourceType = type
                    .replaceFirst("?:", ":")
                    .replaceFirst(")", "?)")

                return kotlinFunctionType(sourceType)
            }
        } else {
            val sourceType = type
                .replace("Promise<TContext | undefined> | TContext | undefined", "Promise<TContext?>?")
                .replace(" | undefined", "?")
                .replace(" | null", "?")
                .replace(" | void", "?")

            if ("|" !in sourceType) {
                return kotlinFunctionType(sourceType)
            }
        }
    }

    if (type.startsWith("("))
        return type
            .replace(" | undefined", "?")
            .replace(" | null", "?")
            .replace("Mutation<unknown, unknown, unknown>", "Mutation<*, *, *, *>")
            .replace("Mutation<unknown, unknown, unknown, unknown>", "Mutation<*, *, *, *>")
            .replace(") => Promise<unknown> | unknown", ") -> Promise<*>?")
            .replace("unknown", "Any?")
            .replace(" => ", " -> ")

    if (" | " in type || " & " in type || type.startsWith("typeof "))
        return "Any /* $type */"

    if (type.startsWith("{"))
        return "$DYNAMIC /* $type */"

    return type
}

fun kotlinFunctionType(type: String): String =
    type.replace("=>", "->")
        .replace("void", "Unit")
        .replace("string", "String")
        .replace("number", "Int")
        .replace("?: boolean", ": Boolean?")
        .replace("boolean", "Boolean")
        .replace("Query)", "Query<*, *, *, *>)")
        .replace("TPageData[]", "ReadonlyArray<TPageData>")
        .replace("TQueryFnData[]", "ReadonlyArray<TQueryFnData>")
        .replace("Query<unknown, unknown, unknown>", "Query<*, *, *, *>")
        .replace("Mutation<any, any, any>", "Mutation<*, *, *, *>")
        .replace("Mutation<unknown, unknown, unknown>", "Mutation<*, *, *, *>")
        .replace("Mutation<unknown, unknown, unknown, unknown>", "Mutation<*, *, *, *>")
        .replace(
            "RefetchOptions & RefetchQueryFilters<TPageData>?",
            "RefetchOptions? /* & RefetchQueryFilters<TPageData> */"
        )
        .replace("unknown", "Any")

fun String.fixDefaultOptions(): String =
    if (this == "DefaultOptions") "$this<*>" else this

private fun String.isClassLike(): Boolean =
    CLASS_REGEX.matches(this) && get(0) == get(0).toUpperCase()
