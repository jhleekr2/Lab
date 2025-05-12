import React, { Component } from 'react';
import { UncontrolledCarousel } from "reactstrap";

const items = [
    {
        src: 'https://han.gl/q6jDb',
        altTest: ' 슬라이드1 이미지 대체 문구',
        caption: ' 슬라이드1 설명',
        header: ' 슬라이드1 제목'
    },
    {
        src: 'https://han.gl/d4jbj',
        altTest: ' 슬라이드2 이미지 대체 문구',
        caption: ' 슬라이드2 설명',
        header: ' 슬라이드2 제목'
    },
    {
        src: 'https://han.gl/U7FFH',
        altTest: ' 슬라이드3 이미지 대체 문구',
        caption: ' 슬라이드3 설명',
        header: ' 슬라이드3 제목'
    }
];

class R041_ReactstrapCarousel extends Component {
    render() {
        return (
            <UncontrolledCarousel items={items}/>
        )
    }
}

// 해당 에러 메시지 react_dom__WEBPACK_IMPORTED_MODULE_3__.findDOMNode is not a function는 React 18 버전 이상에서 매우 흔하게 발생하는 에러입니다.
//
//     문제의 원인:
//
//     ReactDOM.findDOMNode() API의 변경/제한:
// ReactDOM.findDOMNode()는 React에서 특정 컴포넌트의 DOM 노드에 직접 접근하는 데 사용되던 레거시(구식) API입니다. React 18부터는 이 API의 사용이 엄격히 제한되거나 특정 환경(예: StrictMode)에서 아예 동작하지 않도록 변경되었습니다.
//
//     reactstrap 버전과의 비호환성:
//     reactstrap 라이브러리의 이전 버전(특히 v9.0.0 미만 또는 일부 v9.0.x 버전)은 UncontrolledCarousel과 같은 일부 컴포넌트 내부적으로 findDOMNode()를 사용하고 있었습니다. 이로 인해 프로젝트의 React 버전이 18 이상일 때, 구버전 reactstrap을 사용하면 findDOMNode is not a function 에러가 발생합니다.
//
//     해결 방법:
//
//     가장 확실한 해결책은 reactstrap을 React 18과 호환되는 최신 버전으로 업데이트하는 것입니다.
export default R041_ReactstrapCarousel;