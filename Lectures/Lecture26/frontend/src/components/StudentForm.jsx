import { useState } from 'react';
import { useAuth } from '../App';
import { createStudent, updateStudent } from '../services/studentService';
import styles from './StudentForm.module.css';

const MAJORS = ['COMPUTER_SCIENCE', 'MATHEMATICS', 'PHYSICS', 'BUSINESS'];

const EMPTY = { name: '', email: '', age: '', major: 'COMPUTER_SCIENCE' };

/**
 * REACT CONCEPT: Reusable component with props
 *
 * StudentForm is used in TWO places:
 *   1. StudentsPage  – no `initial` prop  → CREATE mode (POST)
 *   2. StudentDetailPage – receives `initial` & `studentId` → EDIT mode (PUT)
 *
 * Props:
 *   initial    – pre-fills the form fields for edit mode
 *   studentId  – if provided, the form sends PUT instead of POST
 *   onCreated  – callback called after a successful POST with the new student
 *   onUpdated  – callback called after a successful PUT with the updated student
 *
 * REACT CONCEPT: Derived initial state
 *   useState(() => ...) with a function argument runs only once, on mount.
 *   This avoids re-initialising state on every render.
 *
 * REACT CONCEPT: Handling server validation errors
 *   Spring's @Valid returns { field: "message" } JSON on 400.
 *   We map those into per-field error messages under each input.
 */
export default function StudentForm({ initial, studentId, onCreated, onUpdated }) {
  const { auth } = useAuth();

  const [form,       setForm]       = useState(() => initial ? {
    name:  initial.name,
    email: initial.email,
    age:   String(initial.age),
    major: initial.major,
  } : EMPTY);

  const [fieldErrors, setFieldErrors] = useState({});
  const [serverError, setServerError] = useState('');
  const [loading,     setLoading]     = useState(false);

  const isEdit = Boolean(studentId);

  // Generic change handler for all inputs/selects
  function handleChange(e) {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
    // Clear the field error as the user types
    setFieldErrors(prev => ({ ...prev, [name]: '' }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setServerError('');
    setFieldErrors({});
    setLoading(true);

    const payload = { ...form, age: Number(form.age) };

    try {
      if (isEdit) {
        const updated = await updateStudent(studentId, payload, auth);
        onUpdated?.(updated);
      } else {
        const created = await createStudent(payload, auth);
        onCreated?.(created);
        setForm(EMPTY); // reset after create
      }
    } catch (err) {
      if (err.response?.status === 400) {
        // Spring validation errors: { name: "...", email: "...", ... }
        setFieldErrors(err.response.data);
      } else if (err.response?.status === 409) {
        // Duplicate email
        setFieldErrors({ email: err.response.data.error });
      } else if (err.response?.status === 401) {
        setServerError('Unauthorized. Please log in as admin.');
      } else {
        setServerError('An unexpected error occurred.');
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <form className={styles.form} onSubmit={handleSubmit}>
      <h3>{isEdit ? 'Edit Student' : 'New Student'}</h3>

      <div className={styles.field}>
        <label>Name</label>
        <input name="name" value={form.name} onChange={handleChange} placeholder="Alice Johnson" />
        {fieldErrors.name && <p className="error-text">{fieldErrors.name}</p>}
      </div>

      <div className={styles.field}>
        <label>Email</label>
        <input name="email" type="email" value={form.email} onChange={handleChange} placeholder="alice@example.com" />
        {fieldErrors.email && <p className="error-text">{fieldErrors.email}</p>}
      </div>

      <div className={styles.field}>
        <label>Age</label>
        <input name="age" type="number" value={form.age} onChange={handleChange} placeholder="20" />
        {fieldErrors.age && <p className="error-text">{fieldErrors.age}</p>}
      </div>

      <div className={styles.field}>
        <label>Major</label>
        <select name="major" value={form.major} onChange={handleChange}>
          {MAJORS.map(m => (
            <option key={m} value={m}>{m.replace('_', ' ')}</option>
          ))}
        </select>
        {fieldErrors.major && <p className="error-text">{fieldErrors.major}</p>}
      </div>

      {serverError && <p className="error-text">{serverError}</p>}

      <button type="submit" className={styles.btnSubmit} disabled={loading}>
        {loading ? 'Saving…' : isEdit ? 'Save Changes' : 'Add Student'}
      </button>
    </form>
  );
}

