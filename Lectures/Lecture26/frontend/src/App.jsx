import React, { createContext, useContext, useState, useCallback } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import Navbar     from './components/Navbar';
import LoginPage  from './pages/LoginPage';
import StudentsPage from './pages/StudentsPage';
import StudentDetailPage from './pages/StudentDetailPage';
import NotFoundPage from './pages/NotFoundPage';

/**
 * REACT CONCEPT 1 – Context API
 *
 * Context provides a way to share state (here: the logged-in user) across
 * the entire component tree without "prop drilling" (passing props through
 * every intermediate component).
 *
 * useAuth() is a custom hook that any component can call to read/update auth.
 */
export const AuthContext = createContext(null);

export function useAuth() {
  return useContext(AuthContext);
}

/**
 * REACT CONCEPT 2 – App component (root of the component tree)
 *
 * Responsibilities:
 *  • Owns the global auth state
 *  • Wraps the app in AuthContext.Provider so every child can read auth
 *  • Defines all client-side routes with React Router v6
 */
export default function App() {
  // REACT CONCEPT 3 – useState
  // useState returns [currentValue, setterFunction].
  // Calling the setter causes React to re-render the component.
  const [auth, setAuth] = useState(() => {
    // Restore session from sessionStorage on page refresh
    const saved = sessionStorage.getItem('auth');
    return saved ? JSON.parse(saved) : null;
  });

  // REACT CONCEPT 4 – useCallback
  // Memoises the function reference so it doesn't cause unnecessary re-renders
  // in child components that receive it as a prop.
  const login = useCallback((username, password) => {
    const credentials = { username, password };
    setAuth(credentials);
    sessionStorage.setItem('auth', JSON.stringify(credentials));
  }, []);

  const logout = useCallback(() => {
    setAuth(null);
    sessionStorage.removeItem('auth');
  }, []);

  return (
    <AuthContext.Provider value={{ auth, login, logout }}>
      {/*
        REACT CONCEPT 5 – React Router v6
        BrowserRouter uses the HTML5 History API for client-side navigation.
        Routes picks the first <Route> whose path matches the current URL.
        Navigate performs a declarative redirect.
      */}
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/"          element={<Navigate to="/students" replace />} />
          <Route path="/students"  element={<StudentsPage />} />
          <Route path="/students/:id" element={<StudentDetailPage />} />
          <Route path="/login"     element={<LoginPage />} />
          <Route path="*"          element={<NotFoundPage />} />
        </Routes>
      </BrowserRouter>
    </AuthContext.Provider>
  );
}

