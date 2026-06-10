import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../App';
import { verifyCredentials } from '../services/studentService';
import styles from './LoginPage.module.css';

/**
 * REACT CONCEPT: Controlled Form
 *
 * Every <input> is a "controlled component" – its value is driven by React
 * state, not the DOM.  onChange syncs the DOM value back into state so React
 * is always the single source of truth.
 *
 * React integration with Spring Security:
 *   1. User types credentials
 *   2. React calls GET /api/auth/me with Basic Auth header
 *   3. Spring validates and responds 200 or 401
 *   4. On 200 React stores credentials in Context + sessionStorage
 *   5. Subsequent write requests include the same Basic Auth header
 *
 * ⚠️ Educational note: Basic Auth over plain HTTP is only fine for demos.
 *    Production apps should use JWT / OAuth2 + HTTPS.
 */
export default function LoginPage() {
  // REACT CONCEPT: Multiple state variables – each piece of state has its own useState call
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error,    setError]    = useState('');
  const [loading,  setLoading]  = useState(false);

  const { login } = useAuth();
  const navigate  = useNavigate();

  async function handleSubmit(e) {
    // Prevent browser default form submission (page reload)
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      // Call Spring's /api/auth/me – throws on 401
      await verifyCredentials(username, password);
      login(username, password);
      navigate('/students');
    } catch (err) {
      // axios throws for non-2xx responses; status 401 = wrong credentials
      if (err.response?.status === 401) {
        setError('Invalid username or password.');
      } else {
        setError('Could not reach the server. Is the backend running?');
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className={styles.wrapper}>
      <form className={styles.card} onSubmit={handleSubmit}>
        <h2 className={styles.title}>Admin Login</h2>
        <p className={styles.hint}>
          Credentials are verified against Spring Security on the backend.
        </p>

        <label className={styles.label}>Username</label>
        {/* Controlled input – value from state, onChange updates state */}
        <input
          type="text"
          value={username}
          onChange={e => setUsername(e.target.value)}
          placeholder="admin"
          required
        />

        <label className={styles.label}>Password</label>
        <input
          type="password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          placeholder="••••••••"
          required
        />

        {/* Conditional rendering – only shown when there is an error */}
        {error && <p className="error-text">{error}</p>}

        <button type="submit" className={styles.btnSubmit} disabled={loading}>
          {loading ? 'Checking…' : 'Login'}
        </button>
      </form>
    </div>
  );
}

