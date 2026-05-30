import axios from 'axios';

/**
 * REACT CONCEPT: Service / API layer
 *
 * All HTTP calls to the Spring Boot backend live here.
 * Components never call fetch/axios directly – they import these functions.
 * This separates concerns: components handle UI, services handle data fetching.
 *
 * axios is a popular HTTP client that:
 *  • Automatically serialises JS objects to JSON (Content-Type: application/json)
 *  • Automatically parses JSON responses into JS objects
 *  • Has a cleaner API than the native fetch()
 *
 * The CRA "proxy" field in package.json forwards /api/* to http://localhost:8082
 * during development, so we only write relative paths here.
 */

const BASE = '/api/students';

/** Build the Authorization: Basic ... header from stored credentials */
function authHeader(auth) {
  if (!auth) return {};
  const encoded = btoa(`${auth.username}:${auth.password}`);
  return { Authorization: `Basic ${encoded}` };
}

// ── READ (public – no auth needed) ────────────────────────────────────────────

export const getStudents = () =>
  axios.get(BASE).then(r => r.data);

export const getStudent = (id) =>
  axios.get(`${BASE}/${id}`).then(r => r.data);

export const getStudentsByMajor = (major) =>
  axios.get(`${BASE}/major/${major}`).then(r => r.data);

// ── WRITE (requires Basic Auth) ───────────────────────────────────────────────

export const createStudent = (data, auth) =>
  axios.post(BASE, data, { headers: authHeader(auth) }).then(r => r.data);

export const updateStudent = (id, data, auth) =>
  axios.put(`${BASE}/${id}`, data, { headers: authHeader(auth) }).then(r => r.data);

export const deleteStudent = (id, auth) =>
  axios.delete(`${BASE}/${id}`, { headers: authHeader(auth) });

// ── AUTH ──────────────────────────────────────────────────────────────────────

/**
 * Verify credentials by calling GET /api/auth/me with Basic Auth.
 * Spring returns 200 + { username, role } if valid, 401 if not.
 */
export const verifyCredentials = (username, password) => {
  const encoded = btoa(`${username}:${password}`);
  return axios.get('/api/auth/me', {
    headers: { Authorization: `Basic ${encoded}` }
  }).then(r => r.data);
};

