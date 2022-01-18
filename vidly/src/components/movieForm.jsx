import React from "react";
import Form from "./common/form";

class MovieForm extends Form {

    state = {
        data: {
            title: "",
            genre: "",
            stock: "",
            rate: "",
        },
        errors: {},
    }

    joiSchema = {}

    render() {

        return (

            <div>
                <h1>Movie Form {match.params.id}</h1>
                <form onSubmit={(event) => this.handleSubmit(event)}>
                    {this.renderInput("email", "Email")}
                    {this.renderInput("password", "Password", "password")}
                    {this.renderInput("name", "Name")}
                    {this.renderButton("Register")}
                </form>
                <button className="btn btn-primary" onClick={() => history.push("/movies")}>Save</button>
            </div>
        )
    }

}

export default MovieForm;