import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {createStore} from 'redux';
import reducers from './reducers';
import reportWebVitals from './reportWebVitals';

const store = createStore(reducers);

const root = ReactDOM.createRoot(document.getElementById('root'));
const renderApp = () => {
	root.render(
		<App store={store}/> // App 컴포넌트에 store prop을 전달
	);
};
store.subscribe(renderApp);
renderApp();

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
