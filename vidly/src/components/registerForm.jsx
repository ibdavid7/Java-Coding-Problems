import React from "react";
import Form from "./common/form";
import Joi from "joi-browser";

class RegisterForm extends Form {
    state = {
        data: {email: "", password: "", name: ""},
        errors: {},
    }

    joiSchema = {
        email: Joi.string().email().required().label("Email"),
        password: Joi.string().min(5).label("Password"),
        name: Joi.string().alphanum().required().label("Name"),
    }

    doSubmit = () => {
        console.log("Registered!");
    }

    render() {

        return (
            <form onSubmit={(event) => this.handleSubmit(event)}>
                {this.renderInput("email", "Email")}
                {this.renderInput("password", "Password", "password")}
                {this.renderInput("name", "Name")}
                {this.renderButton("Register")}
            </form>
        );
    }
}

export default RegisterForm;