import React, {Component} from "react";

const Like = (props) => {

    let formatHeartClass = ((props) => {
        let classes = "fa fa-heart";
        classes += props.liked ? "" : "-o";
        return classes;
    })(props);

    return (
        <i
            onClick={props.onClick}
            style={{cursor: "pointer"}}
            className={formatHeartClass}
            aria-hidden="false">
        </i>
    );

}


export default Like;