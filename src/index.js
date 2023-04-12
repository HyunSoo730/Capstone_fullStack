import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <App />
  </React.StrictMode>
);
