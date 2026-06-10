import {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import {useAuth} from '../App';
import {
    getStudents,
    getStudentsByMajor,
    deleteStudent
} from '../services/studentService';
import StudentForm from '../components/StudentForm';
import styles from './StudentsPage.module.css';

const MAJORS = ['ALL', 'COMPUTER_SCIENCE', 'MATHEMATICS', 'PHYSICS', 'BUSINESS'];

/**
 * REACT CONCEPT: Data-fetching with useEffect
 *
 * useEffect(fn, deps) runs fn after every render where deps changed.
 * An empty deps array [] means "run once after the first render" – like
 * componentDidMount in class components.
 *
 * Pattern used here:
 *   1. Component renders with loading=true and empty list
 *   2. useEffect fires after paint → calls the API
 *   3. API responds → setState → React re-renders with data
 *
 * REACT CONCEPT: Lifting state up
 *   StudentForm is a child component.  When it successfully creates a student
 *   it calls the onCreated prop (a function owned by StudentsPage) to trigger
 *   a re-fetch.  The parent owns the data; children notify it via callbacks.
 */
export default function StudentsPage() {
    const {auth} = useAuth();

    const [students, setStudents] = useState([]);
    const [majorFilter, setMajorFilter] = useState('ALL');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [showForm, setShowForm] = useState(false);
    const [deleteError, setDeleteError] = useState('');

    // ── Fetch students whenever the major filter changes ──────────────────────
    useEffect(() => {
        // Cleanup pattern: ignore stale responses if the component re-renders
        // before the previous fetch completes (cancelled flag trick).
        let cancelled = false;
        setLoading(true);
        setError('');

        const promise = majorFilter === 'ALL'
            ? getStudents()
            : getStudentsByMajor(majorFilter);

        promise
            .then(data => {
                if (!cancelled) setStudents(data);
            })
            .catch(() => {
                if (!cancelled) setError('Failed to load students. Is the backend running?');
            })
            .finally(() => {
                if (!cancelled) setLoading(false);
            });

        // Cleanup function – runs before the next effect or on unmount
        return () => {
            cancelled = true;
        };
    }, [majorFilter]); // ← re-run whenever majorFilter changes

    // ── Delete ────────────────────────────────────────────────────────────────
    async function handleDelete(id) {
        if (!window.confirm('Delete this student?')) return;
        setDeleteError('');
        try {
            await deleteStudent(id, auth);
            // Optimistic UI: remove from local state immediately without refetching
            setStudents(prev => prev.filter(s => s.id !== id));
        } catch (err) {
            setDeleteError(err.response?.data?.error || 'Delete failed.');
        }
    }

    return (
        <div className={styles.page}>
            <div className={styles.header}>
                <h1>Students</h1>
                {/* Conditional rendering – admin-only button */}
                {auth && (
                    <button className={styles.btnAdd} onClick={() => setShowForm(v => !v)}>
                        {showForm ? '✕ Cancel' : '＋ Add Student'}
                    </button>
                )}
            </div>

            {/* REACT CONCEPT: Conditional rendering with && */}
            {showForm && (
                <StudentForm
                    onCreated={(newStudent) => {
                        setStudents(prev => [newStudent, ...prev]);
                        setShowForm(false);
                    }}
                />
            )}

            {/* Major filter tabs */}
            <div className={styles.filters}>
                {MAJORS.map(m => (
                    <button
                        key={m}
                        className={`${styles.filterBtn} ${majorFilter === m ? styles.active : ''}`}
                        onClick={() => setMajorFilter(m)}
                    >
                        {m.replace('_', ' ')}
                    </button>
                ))}
            </div>

            {deleteError && <p className="error-text">{deleteError}</p>}

            {loading && <p className={styles.status}>Loading…</p>}
            {error && <p className="error-text">{error}</p>}

            {/* REACT CONCEPT: List rendering with .map() – every item needs a unique key */}
            {!loading && !error && (
                <div className={styles.grid}>
                    {students.length === 0 && (
                        <p className={styles.status}>No students found.</p>
                    )}
                    {students.map(student => (
                        <div key={student.id} className={styles.card}>
                            <Link to={`/students/${student.id}`} className={styles.cardLink}>
                                <h3>{student.name}</h3>
                                <p className={styles.email}>{student.email}</p>
                                <p>Age: <strong>{student.age}</strong></p>
                                <span className={styles.badge}>{student.major.replace('_', ' ')}</span>
                            </Link>
                            {auth && (
                                <div className={styles.cardActions}>
                                    <Link to={`/students/${student.id}`}>
                                        <button className={styles.btnEdit}>Edit</button>
                                    </Link>
                                    <button
                                        className={styles.btnDelete}
                                        onClick={() => handleDelete(student.id)}
                                    >
                                        Delete
                                    </button>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

