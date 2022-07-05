// Automatically generated - do not modify!

package tanstack.table.core

external interface SortingColumn<TData : RowData> {
    var getAutoSortingFn: () -> SortingFn<TData>
    var getAutoSortDir: () -> SortDirection
    var getSortingFn: () -> SortingFn<TData>
    var getNextSortingOrder: () -> SortDirection | false
    var getCanSort: () -> Boolean
    var getCanMultiSort: () -> Boolean
    var getSortIndex: () -> Number
    var getIsSorted: () -> false | SortDirection
    var clearSorting: () -> void
    var toggleSorting: (desc?: boolean, isMulti ?: boolean) -> void
    var getToggleSortingHandler: () -> undefined | ((event: unknown) -> void)
}
