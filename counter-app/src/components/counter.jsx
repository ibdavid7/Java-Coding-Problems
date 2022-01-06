import React, {Component} from "react";

class Counter extends Component {

    // state = {
    //     value: this.props.counter.value,
    //     // tags: [],
    // }

    // styles = {
    //     fontSize: 50,
    //     fontWeight: 'bold',
    // }

    // constructor() {
    //     super();
    //     this.state.counter = this.props.value;
    //     // console.log(this);
    //     // this.handleIncrement = this.handleIncrement.bind(this);
    // }

    // componentDidUpdate(prevProps, prevState, snapshot) {
    //     console.log("prevProps: ", prevProps);
    //     console.log("prevState: ", prevState);
    //     console.log("snapshot: ", snapshot);
    //     console.log(prevProps.counter.value);
    //     console.log(this.props.counter.value);
    //     if (prevProps.counter.value !== this.props.counter.value) {
    //         console.log("value changed");
    //     }
    // }

    // componentWillUnmount() {
    //     console.log('Counter - Unmount');
    // }

    render() {

        // console.log("Counter - Rendered");

        const {counter, onDelete, onIncrement, onDecrement} = this.props;

        return (
            <React.Fragment>
                <div className="row">
                    <div className="col-1">

                        <span className={this.getBadgeClasses()}>{this.formatCount()}</span>
                    </div>

                    <div className="col">
                        <button onClick={() => onIncrement(counter)} className="btn btn-secondary btn-sm">
                            +
                        </button>
                        <button onClick={() => onDecrement(counter)} disabled={this.formatDecrementButton()}
                                className="btn btn-secondary btn-sm m-2">
                            -
                        </button>
                        <button onClick={() => onDelete(counter.id)} className="btn btn-danger btn-sm">
                            x
                        </button>

                    </div>

                    {/*{this.state.tags.length === 0 && <p>Please add tags!</p>}*/}
                    {/*{this.renderTags()}*/}
                </div>
            </React.Fragment>);
    }

    getBadgeClasses() {
        let classes = "badge m-2 badge-";
        classes += this.props.counter.value < 1 ? "warning" : "primary";
        return classes;
    }

    formatCount() {
        let {value} = this.props.counter;
        let zero = "zero";
        return value < 1 ? zero : value;
    }

    formatDecrementButton() {
        return this.props.counter.value === 0;
    }

    // renderTags() {
    //     if (this.state.tags.length === 0) {
    //         // return <p>There are no tags!</p>;
    //     } else {
    //         return (
    //             <ul>
    //                 {this.state.tags.map(tag => <li key={tag}>{tag}</li>)}
    //             </ul>
    //         );
    //     }
    // }

    // handleIncrement = () => {
    //     // console.log('Increment clicked');
    //     this.setState({value: this.props.counter.value + 1})
    //     // console.log(product)
    //     // this.state.counter++;
    //     // console.log(this.state.counter);
    // }

    /*handleIncrementSupport = () => {
        this.handleIncrement({id: 1});
    }*/
}


export default Counter;