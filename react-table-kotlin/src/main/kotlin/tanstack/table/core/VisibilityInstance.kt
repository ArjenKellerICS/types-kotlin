// Automatically generated - do not modify!

package tanstack.table.core

external interface VisibilityInstance<TData : RowData> {
    var getVisibleFlatColumns: () -> Column<TData>[]
    var getVisibleLeafColumns: () -> Column<TData>[]
    var getLeftVisibleLeafColumns: () -> Column<TData>[]
    var getRightVisibleLeafColumns: () -> Column<TData>[]
    var getCenterVisibleLeafColumns: () -> Column<TData>[]
    var setColumnVisibility: (updater: Updater<VisibilityState>) -> void
    var resetColumnVisibility: (defaultState?: boolean) -> void
    var toggleAllColumnsVisible: (value?: boolean) -> void
    var getIsAllColumnsVisible: () -> Boolean
    var getIsSomeColumnsVisible: () -> Boolean
    var getToggleAllColumnsVisibilityHandler: () -> (event: unknown) -> void
}
