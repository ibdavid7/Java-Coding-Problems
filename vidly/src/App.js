import React from "react";
import Movies from "./components/movies";
import NavBar from "./components/navBar";
import {Redirect, Route, Switch} from "react-router-dom";
import Rentals from "./components/rentals";
import Customers from "./components/customers";
import NotFound from "./components/common/notFound";
import './App.css';
import movieForm from "./components/movieForm";

function App() {
    return (
        <React.Fragment>
            <NavBar/>
            <main className="container">
                <Switch>
                    <Route path="/movies/:id" component={movieForm}/>
                    <Route path="/movies" component={Movies}/>
                    <Route path="/customers" component={Customers}/>
                    <Route path="/rentals" component={Rentals}/>
                    <Route path="/not-found" component={NotFound}/>
                    <Redirect from="/" exact to="/movies"/>
                    <Redirect to="/not-found"/>
                </Switch>
            </main>
        </React.Fragment>
    )
        ;

}

export default App;
