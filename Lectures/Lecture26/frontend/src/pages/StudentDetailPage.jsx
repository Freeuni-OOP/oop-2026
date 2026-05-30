import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useAuth } from '../App';
import { getStudent, deleteStudent } from '../services/studentService';
import StudentForm from '../components/StudentForm';
import styles from './StudentDetailPage.module.css';

/**
 * REACT CONCEPT: URL Parameters with useParams
 *
 * useParams() reads the dynamic ":id" segment from the URL.
 * e.g. if the URL is /students/3, then params.id === "3".
 *
 * This page shows the full student record and, for admins, an edit form.
 */
export default function StudentDetailPage() {
  const { id }    = useParams();           // read :id from URL
  const navigate  = useNavigate();
  const { auth }  = useAuth();

  const [student,  setStudent]  = useState(null);
  const [loading,  setLoading]  = useState(true);
  const [error,    setError]    = useState('');
  const [editing,  setEditing]  = useState(false);

  // Fetch student when the id param changes
  useEffect(() => {
    setLoading(true);
    getStudent(id)
      .then(setStudent)
      .catch(() => setError('Student not found.'))
      .finally(() => setLoading(false));
  }, [id]);

  async function handleDelete() {
    if (!window.confirm('Delete this student permanently?')) return;
    try {
      await deleteStudent(id, auth);
      navigate('/students');
    } catch {
      setError('Delete failed.');
    }
  }

  if (loading) return <p className={styles.status}>Loading…</p>;
  if (error)   return <p className={`error-text ${styles.status}`}>{error}</p>;
  if (!student) return null;

  return (
    <div className={styles.page}>
      <button className={styles.back} onClick={() => navigate('/students')}>
        ← Back to list
      </button>

      {editing ? (
        <>
          <h2>Edit Student</h2>
          <StudentForm
            initial={student}
            studentId={student.id}
            onUpdated={(updated) => {
              setStudent(updated);
              setEditing(false);
            }}
          />
          <button className={styles.btnCancel} onClick={() => setEditing(false)}>
            Cancel
          </button>
        </>
      ) : (
        <div className={styles.card}>
          <h2>{student.name}</h2>
          <table className={styles.table}>
            <tbody>
              <tr><td>ID</td>    <td>{student.id}</td></tr>
              <tr><td>Email</td> <td>{student.email}</td></tr>
              <tr><td>Age</td>   <td>{student.age}</td></tr>
              <tr><td>Major</td> <td>{student.major.replace('_', ' ')}</td></tr>
            </tbody>
          </table>

          {auth && (
            <div className={styles.actions}>
              <button className={styles.btnEdit}   onClick={() => setEditing(true)}>Edit</button>
              <button className={styles.btnDelete} onClick={handleDelete}>Delete</button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

