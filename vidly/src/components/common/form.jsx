import React, {Component} from "react";
import Joi from "joi-browser";
import Input from "./input";

class Form extends Component {
    state = {
        data: {},
        errors: {},
    }

    validate = () => {
        const options = {abortEarly: false};
        const {error} = Joi.validate(this.state.data, this.joiSchema, options);
        // console.log(Joi.validate(this.state.data, this.joiSchema, options));
        if (!error) return null;

        const errors = {};
        error.details.map(item => errors[item.path[0]] = item.message)
        return errors;

    }

    validateProperty = ({value, name}) => {

        const obj = {[name]: value};
        const schema = {[name]: this.joiSchema[name]};
        const {error} = Joi.validate(obj, schema, {abortEarly: true});
        // console.log(Joi.validate(obj, schema, {abortEarly: true}));
        if (error) return error.details[0].message;

    }

    handleSubmit = (e) => {
        e.preventDefault();

        const errors = this.validate();
        this.setState({errors: errors || {}});
        if (errors) return;

        this.doSubmit();
    }

    handleChange = ({currentTarget: eventTgt}) => {

        //validate input field input
        const errors = {...this.state.errors};
        const errorMessage = this.validateProperty(eventTgt);
        if (errorMessage) errors[eventTgt.name] = errorMessage;
        else delete errors[eventTgt.name];

        //update state of controlled element
        const data = {...this.state.data};
        data[eventTgt.name] = eventTgt.value;
        this.setState({data, errors});
    }

    renderButton = (label) => {
        return(<button
            disabled={this.validate()}
            type="submit"
            className="btn btn-primary"
            // onClick={() => history.replace("/movies")}
        >{label}
        </button>);
    }

    renderInput = (name, label, type = "text") => {

        const {data, errors} = this.state;

        return(<Input
            type={type}
            name={name}
            label={label}
            value={data[name]}
            onChange={this.handleChange}
            placeholder={`Enter ${label}`}
            error={errors[name]}
        />);
    }

}

export default Form;