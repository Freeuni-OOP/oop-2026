import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

/**
 * REACT CONCEPT: Entry point
 *
 * ReactDOM.createRoot() mounts the entire React app into the
 * <div id="root"> element in public/index.html.
 *
 * React 18+ uses the new concurrent root API (createRoot) instead of
 * the legacy ReactDOM.render().
 */
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // StrictMode renders every component twice in development to surface side-effect bugs.
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

