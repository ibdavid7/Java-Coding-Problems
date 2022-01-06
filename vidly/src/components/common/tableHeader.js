import React, {Component} from "react";

class TableHeader extends Component {

    raiseSort = (path) => {

        let sortColumn = {...this.props.sortColumn};

        if (sortColumn.path === path) {
            sortColumn.order = sortColumn.order === 'asc' ? 'desc' : 'asc';
        } else {
            sortColumn = {path, order: 'asc'};
        }

        this.props.onSort(sortColumn);
    }

    renderSortIcon = (column) => {
        const {sortColumn} = this.props;

        if (column.path !== sortColumn.path) {
            return null;
        }

        if (sortColumn.order === 'asc') {
            return (<i className="fa fa-sort-asc"></i>);
        } else {
            return (<i className="fa fa-sort-desc"></i>);
        }
    }

    render() {

        const {columns} = this.props;

        return (<thead>
            <tr>
                {columns.map(column => {
                    return (<th key={column.path || column.key}
                                className="clickable"
                                onClick={() => this.raiseSort(column.path)}
                                scope="column">{column.label} {this.renderSortIcon(column)}
                    </th>);
                })}
            </tr>
            </thead>

        );
    }
}

export default TableHeader;