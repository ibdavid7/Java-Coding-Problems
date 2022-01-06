import React, {Component} from "react";
import Counter from "./counter";

class Counters extends Component {

    // constructor(props) {
    //     super(props);
    //     this.state.poopoo = this.props.counters;
        // console.log("Counters - Constructed", this.props);
    // }

    // componentDidMount() {
        // Ajax call to the server to get data
        // this.setState({counters: this.state.poopoo, doo: "Hiay!"});
        // console.log('Counters - Mounted');
    // }

    // state = {};

    render() {

        // console.log("Counters - Rendered");

        const {onReset, onIncrement, onDecrement, onDelete, counters} = this.props;

        return (
            <div>
                <button onClick={() => onReset()} className="btn btn-primary btn-sm m2">Reset</button>
                {counters.map(counter =>
                    <Counter
                        key={counter.id}
                        onDelete={onDelete}
                        onIncrement={onIncrement}
                        onDecrement={onDecrement}
                        counter={counter}>
                    </Counter>
                )}
            </div>
        );
    }

    // handleDelete = (counterId) => {
    //     // console.log("Handle Delete called");
    //     const {counters} = this.state;
    //     this.setState({counters: counters.filter(counter => counter.id !== counterId)});
    // }

    // handleReset = () => {
    //     const counters = this.state.counters.map(counter => {
    //         counter.value = 0;
    //         return counter;
    //     })
    //     this.setState({counters});
    //     // console.log("reset called");
    // }

    // handleIncrement = (counter) => {
    //     const counters = [...this.state.counters];
    //     const index = counters.indexOf(counter);
    //     counters[index] = {...counter};
    //     counters[index].value++;
    //     this.setState({counters});
    //     // console.log(this.state.counters[0].value)
    // }
}

export default Counters;