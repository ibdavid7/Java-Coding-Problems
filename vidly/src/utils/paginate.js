import __ from "lodash";

function paginate(itemsArray, currentPage, pageSize) {
    const startIndex = (currentPage - 1) * pageSize;
    return __(itemsArray)
        .slice(startIndex)
        .take(pageSize)
        .value();
}

export default paginate;