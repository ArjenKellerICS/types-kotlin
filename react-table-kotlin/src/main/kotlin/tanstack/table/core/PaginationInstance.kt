// Automatically generated - do not modify!

package tanstack.table.core

external interface PaginationInstance<TData : RowData> {
    setPagination: (updater: Updater<PaginationState>) => void
    resetPagination: (defaultState?: boolean) => void
    setPageIndex: (updater: Updater<number>) => void
    resetPageIndex: (defaultState?: boolean) => void
    setPageSize: (updater: Updater<number>) => void
    resetPageSize: (defaultState?: boolean) => void
    setPageCount: (updater: Updater<number>) => void
    getPageOptions: () => number[]
    getCanPreviousPage: () => boolean
    getCanNextPage: () => boolean
    previousPage: () => void
    nextPage: () => void
    getPrePaginationRowModel: () => RowModel<TData>
    getPaginationRowModel: () => RowModel<TData>
    getPageCount: () => number
}
