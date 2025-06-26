import React, { Component } from 'react';

class R066_onClick extends Component {
    buttonClick = (param) => {
        if(typeof param != 'string') param = "click a"
        console.log('param : ' + param)
    }
    render() {
        return (
            <>
                <button onClick={e => this.buttonClick("Click button")}>
                        Click button</button>
                <div onClick={e => this.buttonClick("Click div")}>Click div</div>
                <a href="javascript:" onclick={this.buttonClick}>Click a</a>
            </>
        )
    }
}
// <a href="javascript:"> 형태의 링크는 현재는 React에서 차단되었다. XSS취약점에 취약하기 때문에.
export default R066_onClick;