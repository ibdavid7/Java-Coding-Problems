import React, {Component} from "react";
import Table from "./common/table";


class MoviesTable extends Component {

    render() {

        const {movies, columns, sortColumn, onSort} = this.props;

        return (

            <Table
                columns={columns}
                data={movies}
                sortColumn={sortColumn}
                onSort={onSort}
            />);

    }
}

export default MoviesTable;