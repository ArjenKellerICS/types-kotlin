// Automatically generated - do not modify!

package tanstack.table.core

type FilterFn<TData extends RowData> = {
    (row: Row<TData>, columnId: string, filterValue: any, addMeta: (meta: any) => void): boolean
    resolveFilterValue ?: TransformFilterValueFn<TData>
    autoRemove ?: ColumnFilterAutoRemoveTestFn<TData>
}