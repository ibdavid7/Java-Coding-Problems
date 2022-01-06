import React, {Component} from "react";

class ListGroup extends Component {
    render() {

        const {items, selectedItem, textProperty, valueProperty, onItemSelect} = this.props;

        return (
            <ul className="list-group">
                {items.map(item => {
                    return (
                        <li
                            className={item === selectedItem ? "list-group-item active" : "list-group-item "}
                            key={item[valueProperty]}
                            onClick={() => onItemSelect(item)}
                            style={{cursor: 'pointer'}}
                        >
                            {item[textProperty]}
                        </li>);
                })}
            </ul>
        )
    }
}

ListGroup.defaultProps = {
    textProperty: "name",
    valueProperty: "_id",
}

export default ListGroup;