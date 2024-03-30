import React from 'react'; // 리액트 사용을 위해 import
import { createRoot } from 'react-dom/client'; 
import './index.css'; // css import
import AppRouter from './AppRouter';
import reportWebVitals from './reportWebVitals'; 

const root = createRoot(document.getElementById('root'));

// 루트에 AppRouter 컴포넌트 렌더링
root.render(
  <React.StrictMode>
    <AppRouter />
  </React.StrictMode>
);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();