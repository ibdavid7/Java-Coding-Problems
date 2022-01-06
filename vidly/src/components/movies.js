import React, {Component} from "react";
import {getMovies} from "../services/fakeMovieService";
import Pagination from "./common/pagination";
import paginate from "../utils/paginate";
import ListGroup from "./common/listGroup";
import {getGenres} from "../services/fakeGenreService";
import filter from "../utils/filter";
import MoviesTable from "./moviesTable";
import _ from "lodash";
import Like from "./common/like";
import {Link} from "react-router-dom";

class Movies extends Component {
    state = {
        movies: [],
        currentPage: 1,
        pageSize: 4,
        genres: [],
        sortColumn: {path: "title", order: "asc"},
        columns: [
            {label: "Title", path: "title",
                content: (movie) => <Link to={`/movies/${movie._id}`}>{movie.title}</Link>},
            {label: "Genre", path: "genre[name]"},
            {label: "Stock", path: "numberInStock"},
            {label: "Rate", path: "dailyRentalRate"},
            {
                key: "like",
                content: movie => (<Like liked={movie.liked} onClick={() => this.handleLike(movie)}> </Like>)
            },
            {
                key: "delete",
                content: movie => (<button className="btn btn-danger btn-sm"
                                           onClick={() => this.handleDelete(movie._id)}>Delete </button>)
            },
        ],
    };

    componentDidMount() {
        const genres = [{name: "All Genres", _id: ""}, ...getGenres()]
        this.setState({movies: getMovies(), genres});
    }

    getPagedDate = () => {

        const {currentPage, pageSize, movies, selectedGenre, sortColumn} = this.state;


        const filtered = filter(movies, selectedGenre);
        const sorted = _.orderBy(filtered, [sortColumn.path], [sortColumn.order]);
        const moviesPaginated = paginate(sorted, currentPage, pageSize);
        return {totalCount: filtered.length, data: moviesPaginated}
    }

    render() {

        const {length: count} = this.state.movies;
        const {currentPage, pageSize, columns, genres, selectedGenre, sortColumn} = this.state;

        if (count < 1) {
            return (
                <h3 className="m-4">There are no movies in the database.</h3>
            );
        }

        const {totalCount, data: moviesPaginated} = this.getPagedDate();

        return (

            <div className="row">
                <div className="col-3">
                    <ListGroup
                        items={genres}
                        selectedItem={selectedGenre}
                        onItemSelect={this.handleGenreSelect}
                    />

                </div>
                <div className="col">

                    <h3 className="m-4">Showing {this.formatText(totalCount)} in the database.</h3>

                    <MoviesTable
                        movies={moviesPaginated}
                        columns={columns}
                        sortColumn={sortColumn}
                        onLike={this.handleLike}
                        onDelete={this.handleDelete}
                        onSort={this.handleSort}
                    />

                    <div className="container">
                        <Pagination
                            itemCount={totalCount}
                            pageSize={pageSize}
                            currentPage={currentPage}
                            onPageChange={this.handlePageChange}/>
                    </div>

                </div>

            </div>

        );
    }

    formatText = (numberOfMovies) => {

        if (numberOfMovies === 0) {
            return 'no movies';
        } else if (numberOfMovies === 1) {
            return "one movie";
        } else {
            return `${numberOfMovies} movies`;
        }
    }

    handleDelete = (id) => {
        this.setState({movies: this.state.movies.filter(mov => mov._id !== id)});
    }

    handleLike = (movie) => {
        const movies = [...this.state.movies];  //clone array
        const index = movies.indexOf(movie);
        movies[index] = {...movies[index]};     //clone object
        movies[index].liked = !movies[index].liked;
        this.setState({movies});
    }

    handlePageChange = (pageNumber) => {
        this.setState({currentPage: pageNumber});
    }

    handleGenreSelect = (genre) => {
        this.setState({selectedGenre: genre, currentPage: 1});
    }

    handleSort = (sortColumn) => {
        this.setState({sortColumn});
    }
}

export default Movies;