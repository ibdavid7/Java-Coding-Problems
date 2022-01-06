import _ from "lodash";

function filter(itemsArray, filterCriteria) {
    return filterCriteria && filterCriteria._id ?
        _(itemsArray).filter(item => item.genre._id === filterCriteria._id).value() :
        itemsArray;
}

export default filter;