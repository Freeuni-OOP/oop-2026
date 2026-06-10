import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import BooksApp from './BooksApp';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <BooksApp/>
    </React.StrictMode>
);
