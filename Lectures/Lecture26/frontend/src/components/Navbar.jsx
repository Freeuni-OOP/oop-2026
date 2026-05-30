import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../App';
import styles from './Navbar.module.css';

/**
 * REACT CONCEPT: Presentational Component
 *
 * Navbar reads the auth context (useAuth) and renders differently for
 * logged-in admins vs. guests.
 *
 * useNavigate() returns an imperative navigation function – useful for
 * redirecting after an action (logout).
 *
 * CSS Modules (*.module.css) scope class names to this component only,
 * preventing global style collisions.
 */
export default function Navbar() {
  const { auth, logout } = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    logout();
    navigate('/students');
  }

  return (
    <nav className={styles.nav}>
      <Link to="/students" className={styles.brand}>
        🎓 Student Manager
        <span className={styles.sub}> – Lecture 26</span>
      </Link>

      <div className={styles.actions}>
        {auth ? (
          <>
            <span className={styles.user}>👤 {auth.username}</span>
            <button className={styles.btnLogout} onClick={handleLogout}>
              Logout
            </button>
          </>
        ) : (
          <Link to="/login">
            <button className={styles.btnLogin}>Admin Login</button>
          </Link>
        )}
      </div>
    </nav>
  );
}

