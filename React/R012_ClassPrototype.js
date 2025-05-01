import React, { Component } from 'react';

class R012_ClassPrototype extends Component {
    
    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
        //ES5 prototype
        var ExamCountFunc = (function () {
            function ExamCount(num) {
                this.number = num;
            }
            ExamCount.prototype.showNum = function() {
                console.log('1. react_' + this.number);
            };
            return ExamCount;
        }());
    
    var cnt = new ExamCountFunc('200');
    cnt.showNum();

    //ES6 class
    class ExamCountClass {
        constructor(num2) {
            this.number2 = num2;
        }
        showNum() {
            console.log(`2. react_${this.number2}`);
            // 이때 '' 쓰는 대신 ``로 써야 적용된다
            // 그렇지 않으면 Unexpected template string expression  no-template-curly-in-string
            // 발생하면서 적용이 안된다.
        }
    }

    var cnt2 = new ExamCountClass('2hundred');
    cnt2.showNum();
    }

    render() {
        return (
            <h2>[THIS IS Class]</h2>
        )
    }
}

export default R012_ClassPrototype;