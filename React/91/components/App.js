import React, {Component} from 'react';
import { Route, Routes } from "react-router-dom";
import reactRouter from './R089_reactRouter';
import reactRouter2 from './R089_reactRouter2';

// css
import '../css/new.css';

// header
import HeaderAdmin from './Header/Header admin';

// footer
import Footer from './Footer/Footer';

class App extends Component {
    render () {
        return (
            <div className="App">
                <HeaderAdmin/>
                <Routes>
                    <Route exact path='/' Component={reactRouter}/>
                    <Route exact path='/reactRouter2' Component={reactRouter2}/>
                </Routes>
                <Footer/>
            </div>
        );
    }
}

//책에서 나온것과는 다르게, Route는 Routes로 wrap 씌워야 정상 작동한다.

export default App;