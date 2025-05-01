import React, { Component } from 'react';
import datatype from 'prop-types';

class R019_PropsBoolean extends Component {

    render() {
        let {
            BooleanTrueFalse
        } = this.props
        return (
            <div style={{padding: "0px"}}>
                {BooleanTrueFalse ? '2. ' : '1. '}
                {BooleanTrueFalse.toString()}
            </div>
        )
    }
}

export default R019_PropsBoolean;