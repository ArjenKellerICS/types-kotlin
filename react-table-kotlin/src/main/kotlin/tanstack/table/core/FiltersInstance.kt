// Automatically generated - do not modify!

package tanstack.table.core

external interface FiltersInstance<TData : RowData> {
    setColumnFilters: (updater: Updater<ColumnFiltersState>) => void
    resetColumnFilters: (defaultState?: boolean) => void
    getPreFilteredRowModel: () => RowModel<TData>
    getFilteredRowModel: () => RowModel<TData>
    setGlobalFilter: (updater: Updater<any>) => void
    resetGlobalFilter: (defaultState?: boolean) => void
    getGlobalAutoFilterFn: () => FilterFn<TData> | undefined
    getGlobalFilterFn: () => FilterFn<TData> | undefined
    getGlobalFacetedRowModel: () => RowModel<TData>
    getGlobalFacetedUniqueValues: () => Map<any, number>
    getGlobalFacetedMinMaxValues: () => undefined | [number, number]
}
