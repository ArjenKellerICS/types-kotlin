// Automatically generated - do not modify!

package tanstack.table.core

type FiltersOptions<TData extends RowData> = {
    enableFilters ?: boolean
    manualFiltering ?: boolean
    filterFromLeafRows ?: boolean
    getFilteredRowModel ?: (table: Table<any>) => () => RowModel<any>
    onColumnFiltersChange ?: OnChangeFn<ColumnFiltersState>
    enableColumnFilters ?: boolean
    globalFilterFn ?: FilterFnOption<TData>
    onGlobalFilterChange ?: OnChangeFn<any>
    enableGlobalFilter ?: boolean
    getColumnCanGlobalFilter ?: (column: Column<TData>) => boolean
    getFacetedRowModel ?: (table: Table<TData>, columnId: string) => () => RowModel<TData>
    getFacetedUniqueValues ?: (table: Table<TData>, columnId: string) => () => Map<any, number>
    getFacetedMinMaxValues ?: (table: Table<TData>, columnId: string) => () => undefined | [number, number]
}