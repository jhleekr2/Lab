import React, { Component } from 'react';
import { throttle } from "lodash";

class R095_reactThrottle extends Component {
    throttleFunc = throttle(() => {
        console.log("Throttle API Call");
    }, 1000);

    render() {
        return (
            <>
                <h2>검색어 입력</h2>
                <input type="text" onChange={this.throttleFunc}/>
            </>
        )
    }
}

export default R095_reactThrottle;