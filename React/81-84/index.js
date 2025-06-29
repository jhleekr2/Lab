import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import reducers from './reducers';
import reportWebVitals from './reportWebVitals';

const store = createStore(reducers);

const root = ReactDOM.createRoot(document.getElementById('root'));

// const listener = () => {
// 	ReactDOM.render(
// 		<Provider store={store}>
// 			<App indexProp="react"/>
// 		</Provider>,
// 		document.getElementById('root')
// 	);
// };

// store.subscribe(listener);
// listener;

// 위 코드는 react18 버전부터 deprecated 되어 더이상 작동하지 않는다.
// 18버전 이후는 아래와 같이 코드가 변경되어야 작동한다.

root.render(
	<Provider store={store}>
		<App indexProp="react"/>
	</Provider>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
