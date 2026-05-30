# Lecture 26 – React + Spring Boot Full-Stack Integration

Builds on **Lecture 25** (Spring Boot REST API) by replacing Thymeleaf with a **React** SPA.  
The frontend and backend run on different ports and communicate over HTTP using **CORS + HTTP Basic Auth**.

---

## Architecture

```
┌──────────────────────────────────┐        ┌──────────────────────────────────┐
│   React Frontend                 │        │   Spring Boot Backend            │
│   localhost:3000                 │◄──────►│   localhost:8082                 │
│                                  │  HTTP  │                                  │
│  • React Router v6 (SPA routing) │  JSON  │  • REST API  /api/students       │
│  • useState / useEffect hooks    │        │  • Spring Security (Basic Auth)  │
│  • Context API  (auth state)     │        │  • JPA + MySQL (Docker)          │
│  • axios  (HTTP client)          │        │  • CORS configured for port 3000 │
│  • CSS Modules (scoped styles)   │        │  • Global exception handler      │
└──────────────────────────────────┘        └──────────────────────────────────┘
                                                        │
                                            ┌───────────▼──────────────┐
                                            │  MySQL  (Docker)          │
                                            │  localhost:3308           │
                                            └──────────────────────────┘
```

---

## What's New Compared to Lecture 25

| Feature | Lecture 25 | Lecture 26 |
|---|---|---|
| UI | Thymeleaf (server-rendered HTML) | React (client-side SPA) |
| Routing | Spring MVC `/ui/**` | React Router v6 |
| Auth check | Form login → server session | `GET /api/auth/me` with Basic Auth header |
| Session | HTTP session cookie | `sessionStorage` in browser |
| CORS | Not needed (same origin) | `CorsConfigurationSource` bean in `SecurityConfig` |
| State management | N/A | `useState`, `useContext`, `useCallback` |
| Spring Boot version | 3.4.5 | 4.0.6 |

---

## React Concepts Covered

| Concept | Where | Notes |
|---|---|---|
| **JSX** | All `.jsx` files | HTML-like syntax compiled to `React.createElement()` |
| **Components** | `Navbar`, `StudentForm` | Reusable, composable UI building blocks |
| **Props** | `StudentForm` | Pass data & callbacks from parent → child |
| **useState** | `StudentsPage`, `LoginPage`, `StudentForm` | Local component state |
| **useEffect** | `StudentsPage`, `StudentDetailPage` | Side effects: data fetching; cleanup with cancelled flag |
| **useContext / Context API** | `App.jsx`, `useAuth()` | Share auth state globally without prop drilling |
| **useCallback** | `App.jsx` | Memoize `login`/`logout` so children don't re-render unnecessarily |
| **useParams** | `StudentDetailPage` | Read the `:id` URL segment from React Router |
| **useNavigate** | `LoginPage`, `Navbar` | Programmatic navigation after actions |
| **Controlled inputs** | `LoginPage`, `StudentForm` | `value` + `onChange` – React is the single source of truth |
| **Conditional rendering** | All pages | `{condition && <JSX />}` and ternary `? :` |
| **List rendering** | `StudentsPage` | `.map()` with a unique `key` prop on every item |
| **CSS Modules** | `*.module.css` | Scoped class names – no global style collisions |
| **Lifting state up** | `StudentsPage` + `StudentForm` | Child notifies parent via callback prop (`onCreated`) |
| **Client-side routing** | `App.jsx` | `BrowserRouter`, `Routes`, `Route`, `Navigate` |

---

## Spring Boot Changes from Lecture 25

| Change | Reason |
|---|---|
| `spring-boot-starter-web` added | Embedded Tomcat + REST support (provides `jakarta.servlet`) |
| Thymeleaf removed | React replaces server-rendered HTML entirely |
| `SessionCreationPolicy.STATELESS` | No server sessions; every request carries its own credentials |
| `CorsConfigurationSource` bean | Allows React (port 3000) to call the API (port 8082) |
| `GET /api/auth/me` endpoint | React calls this to verify credentials — 200 = valid, 401 = wrong |
| CSRF disabled | Safe for stateless REST APIs (no session cookie to hijack) |
| `formLogin()` removed | Login UI is now React, not a Spring-generated HTML page |

---

## How CORS Works

```
Browser (React @ 3000)               Spring Boot (@ 8082)
        │                                     │
        │  OPTIONS /api/students              │  ← preflight (browser-sent automatically)
        │─────────────────────────────────────►│
        │  Access-Control-Allow-Origin: *      │
        │◄─────────────────────────────────────│
        │                                     │
        │  GET /api/students                  │  ← actual request
        │─────────────────────────────────────►│
        │  200 [ ... ]                         │
        │◄─────────────────────────────────────│
```

Without `CorsConfigurationSource`, the browser blocks **every** cross-origin request before it reaches the server.

---

## How Basic Auth Works in This Integration

```
React (LoginPage)                    Spring Boot (SecurityConfig)
       │                                        │
       │  GET /api/auth/me                      │
       │  Authorization: Basic base64(user:pass)│
       │───────────────────────────────────────►│
       │                                        │  BCrypt.verify(password, hash)
       │  200 { username, role }   OR  401      │
       │◄───────────────────────────────────────│
       │                                        │
  credentials saved to sessionStorage           │
       │                                        │
       │  POST /api/students                    │
       │  Authorization: Basic ...  (same)      │  ← header re-sent on every write
       │───────────────────────────────────────►│
```

> **Note:** Basic Auth over plain HTTP is fine for demos.  
> Production apps should use **JWT / OAuth2 + HTTPS**.

---

## Project Structure

```
Lecture26/
├── README.md
├── pom.xml                          ← Spring Boot 4.0.6, Java 25
├── docker-compose.yml               ← MySQL on port 3308
│
├── src/
│   └── main/
│       ├── java/ge/edu/freeuni/lecture26/
│       │   ├── Lecture26Application.java   ← @SpringBootApplication + data seeder
│       │   ├── config/
│       │   │   └── SecurityConfig.java     ← CORS + Basic Auth + STATELESS sessions
│       │   ├── controller/
│       │   │   ├── StudentController.java  ← REST CRUD endpoints
│       │   │   └── AuthController.java     ← GET /api/auth/me (credential check)
│       │   ├── dto/
│       │   │   ├── StudentRequest.java     ← Validated input DTO
│       │   │   └── StudentResponse.java    ← Output DTO (never expose the entity directly)
│       │   ├── exception/
│       │   │   ├── GlobalExceptionHandler.java  ← 400/404/409 → structured JSON
│       │   │   ├── ResourceNotFoundException.java
│       │   │   └── DuplicateEmailException.java
│       │   ├── model/Student.java          ← JPA Entity
│       │   ├── repository/StudentRepository.java
│       │   └── service/StudentService.java ← Business logic
│       └── resources/
│           └── application.properties     ← port 8082, MySQL config, CORS origin
│
└── frontend/                        ← React app (port 3000)
    ├── package.json                 ← "proxy": "http://localhost:8082"
    └── src/
        ├── index.jsx                ← ReactDOM.createRoot() entry point
        ├── index.css                ← Global reset & base styles
        ├── App.jsx                  ← AuthContext provider + React Router routes
        ├── services/
        │   └── studentService.js   ← All axios calls + Basic Auth header builder
        ├── components/
        │   ├── Navbar.jsx           ← Top nav bar (login/logout, brand link)
        │   ├── Navbar.module.css
        │   ├── StudentForm.jsx      ← Reusable create/edit form (props: initial, onCreated, onUpdated)
        │   └── StudentForm.module.css
        └── pages/
            ├── LoginPage.jsx        ← Controlled form → verifyCredentials → sessionStorage
            ├── LoginPage.module.css
            ├── StudentsPage.jsx     ← List, major filter, optimistic delete, add form toggle
            ├── StudentsPage.module.css
            ├── StudentDetailPage.jsx← View + edit + delete one student by :id
            ├── StudentDetailPage.module.css
            ├── NotFoundPage.jsx     ← 404 catch-all route (path="*")
            └── NotFoundPage.module.css
```

---

## Running the Project

### Step 1 – Start MySQL
```bash
# From the Lecture26 root folder
docker compose up -d
```

### Step 2 – Start the Spring Boot backend
Open `Lecture26Application.java` in IntelliJ and click **Run**, or:
```bash
# From the Lecture26 root folder
mvn spring-boot:run
```
Backend starts on **http://localhost:8082**  
4 demo students are seeded automatically on first run.

### Step 3 – Start the React frontend
```bash
cd frontend
npm install        # first time only
npm start
```
React dev server starts on **http://localhost:3000**

### Step 4 – Open the app
- Navigate to **http://localhost:3000**
- Students list loads publicly (no login needed)
- Click **Admin Login** → enter `admin` / `admin123`
- Add, edit, and delete students as admin

---

## API Reference

| Method | Path | Auth required | Description |
|---|---|---|---|
| GET | `/api/students` | ❌ | List all students |
| GET | `/api/students/{id}` | ❌ | Get one student |
| GET | `/api/students/major/{major}` | ❌ | Filter by major |
| POST | `/api/students` | ✅ | Create student |
| PUT | `/api/students/{id}` | ✅ | Update student |
| DELETE | `/api/students/{id}` | ✅ | Delete student |
| GET | `/api/auth/me` | ✅ | Verify credentials (React login check) |
| GET | `/actuator/health` | ❌ | Health check |

---

## Default Credentials

| Username | Password | Role |
|---|---|---|
| `admin` | `admin123` | ADMIN |

Override via environment variables before starting the backend:
```powershell
$env:ADMIN_USERNAME="admin"
$env:ADMIN_PASSWORD="admin123"
```
