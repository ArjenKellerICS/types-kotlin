// Automatically generated - do not modify!

package tanstack.table.core

type ExpandedInstance<TData extends RowData> = {
    _autoResetExpanded: () => void
    setExpanded: (updater: Updater<ExpandedState>) => void
    toggleAllRowsExpanded: (expanded ?: boolean) => void
    resetExpanded: (defaultState ?: boolean) => void
    getCanSomeRowsExpand: () => boolean
    getToggleAllRowsExpandedHandler: () => (event: unknown) => void
    getIsSomeRowsExpanded: () => boolean
    getIsAllRowsExpanded: () => boolean
    getExpandedDepth: () => number
    getExpandedRowModel: () => RowModel<TData>
    _getExpandedRowModel ?: () => RowModel<TData>
    getPreExpandedRowModel: () => RowModel<TData>
}