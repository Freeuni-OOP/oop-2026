# 26 — React Book App

წინა სემინარის (Spring Boot + MySQL) გაგრძელება.  
Backend-ს დაუმატეთ React Frontend (Parcel bundler).

---

## პროექტის სტრუქტურა

```
26/
├── src/
│   ├── backend/                  ← Spring Boot REST API
│   │   └── main/java/...
│   └── frontend/
│       ├── index.html            ← მინიმალური HTML (მხოლოდ <div id="root">)
│       ├── package.json
│       └── src/
│           ├── index.jsx         ← React entry point
│           ├── App.jsx           ← მთავარი კომპონენტი
│           └── components/
│               ├── BookList.jsx  ← წიგნების სია
│               └── BookItem.jsx  ← ერთი წიგნი
├── compose.yaml                  ← MySQL Docker კონტეინერი
└── pom.xml
```

---

## გაშვება

### 1. MySQL Docker-ით

```bash
docker compose up -d
```

> MySQL გაეშვება `localhost:3307`-ზე, Database: `book_db`

---

### 2. Backend (Spring Boot)

```bash
cd src/backend
mvn spring-boot:run
```

> API: `http://localhost:8084`

#### Endpoints:
| Method | URL          | აღწერა           |
|--------|--------------|-------------------|
| GET    | `/book`      | ყველა წიგნი      |
| GET    | `/book/{id}` | კონკრეტული წიგნი |

---

### 3. Frontend

```bash
cd src/frontend
npm install
npm start
```

> Frontend: `http://localhost:1234`

---

## ტექნოლოგიები

| ტიპი     | ტექნოლოგია                           |
|----------|--------------------------------------|
| Backend  | Java 25, Spring Boot |
| Database | MySQL 8 (Docker)                     |
| Frontend | React 18, Parcel                |
