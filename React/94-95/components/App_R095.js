import React, {Component} from 'react';
import { Route, Routes } from "react-router-dom";
import reactThrottle from './R095_reactThrottle';

// css
import '../css/new.css';

// header
import HeaderAdmin from './Header/Header admin';

// footer
import Footer from './Footer/Footer';

// login
import LoginForm from './LoginForm';

class App extends Component {
    render () {
        return (
            <div className="App">
                <HeaderAdmin/>
                <Routes>
                    <Route exact path='/' Component={LoginForm}/>
                    <Route exact path='/Throttle' Component={reactThrottle}/>
                </Routes>
                <Footer/>
            </div>
        );
    }
}

//책에서 나온것과는 다르게, Route는 Routes로 wrap 씌워야 정상 작동한다.

export default App;