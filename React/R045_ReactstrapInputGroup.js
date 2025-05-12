import React, { Component } from 'react';
import { InputGroup, InputGroupAddon, InputGroupText, Input, Button } from "reactstrap";

class R045_ReactstrapInputGroup extends Component {
    render() {
        return (
            <>
            <InputGroup>
                <Input placeholder="userId"/>
                {/*<InputGroupAddon addonType="append">*/}
                    <InputGroupText>@reactmail.com</InputGroupText>
                {/*</InputGroupAddon>*/}
            </InputGroup>
            <InputGroup>
                {/*<InputGroupAddon addonType="prepend"><Button>*/}
                {/*    버튼</Button></InputGroupAddon>*/}
                <Button>버튼</Button>
                <Input />
            </InputGroup>
            </>
        )
    }
}
// reactstrap 라이브러리는 버전 9.0.0부터 Bootstrap 5와의 호환성을 강화하면서 여러 가지 변경 사항을 적용했습니다. 이 과정에서 InputGroupAddon 컴포넌트의 이름이 InputGroupText로 변경되었습니다.
//
//     즉, 현재 당신의 프로젝트에 설치된 reactstrap 버전은 9.0.0 이상일 가능성이 높고, 예제 코드나 튜토리얼이 이전 버전(InputGroupAddon을 사용하던 버전)을 기반으로 작성되었기 때문에 에러가 발생한 것입니다.
//
//     해결 방법:
//
//     InputGroupAddon을 사용하고 있는 모든 곳을 InputGroupText로 변경해 주어야 합니다.
export default R045_ReactstrapInputGroup;