import React, { Component } from 'react';
import { Jumbotron, Button } from "reactstrap";

class R046_ReactstrapJumbotron extends Component {
    render() {
        return (
            <>
            <Jumbotron style={{backgroundColor: "#D38C7C"}}>
                <h1 className="display-4">REACT 200</h1>
                <p className="h4">Contrary to popular belief
                Lorem ipsum is not simpy ramdom text.</p>
                <hr className="my-2" />
                <p>There are many variations of passages of
                Lorem Ipsum available.</p>
                <p className="lead">
                    <Button color="danger">Go Detail</Button>
                </p>
            </Jumbotron>
            </>
        )
    }
}
// Jumbotron은 Bootstrap 5에서 필요 없다고 간주되어 삭제되었다.
// reactstrap 버전 9.0.0부터 Jumbotron 컴포넌트도 제거되었다. 따라서 이 코드는 에러가 발생한다.
export default R046_ReactstrapJumbotron;