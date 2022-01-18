import React, {Component} from "react";
import Input from "./common/input";
import Joi from "joi-browser";
import Form from "./common/form";

class LoginForm extends Form {

    // username = React.createRef();

    state = {
        data: {username: "", password: ""},
        errors: {},
    }

    joiSchema = {
        username: Joi.string().alphanum().min(3).required().label("Username"),
        password: Joi.string().required("Password"),
    };

    // componentDidMount() {
    //     this.username.current.focus();
    // }

    doSubmit = () => {
        // call service, save changes, redirect user
        console.log("Submitted!");
    }

    render() {

        return (
            <form onSubmit={(event) => this.handleSubmit(event)}>
                {this.renderInput("username", "Username")}
                {this.renderInput("password", "Password", "password")}
                {this.renderButton("Login")}
            </form>
        );
    }

}

export default LoginForm;