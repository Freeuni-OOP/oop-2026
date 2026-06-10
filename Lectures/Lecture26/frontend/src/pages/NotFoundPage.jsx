import { Link } from 'react-router-dom';
import styles from './NotFoundPage.module.css';

/**
 * REACT CONCEPT: Catch-all route
 * Rendered when no other <Route> matches.  Defined as path="*" in App.jsx.
 */
export default function NotFoundPage() {
  return (
    <div className={styles.wrapper}>
      <h1 className={styles.code}>404</h1>
      <p className={styles.msg}>Page not found.</p>
      <Link to="/students"><button className={styles.btn}>Go Home</button></Link>
    </div>
  );
}

