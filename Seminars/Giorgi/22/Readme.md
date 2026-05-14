## TypeScript - Book App

პატარა აპლიკაცია წიგნებისთვის. უნდა შეგვეძლოს ახალი წიგნის დამატება და წიგნის მოძებნა აიდით.

- **ფრონტენდი:** TypeScript
- **ბექენდი:** Java (Servlet, Tomcat 10.1)
- **მონაცემთა ბაზა:** MySQL
- **ქეში:** Redis

---

### არქიტექტურა

```
Browser → BookServlet → Redis (ქეში)
                      ↓ (თუ ქეშში არ არის)
                      MySQL → Redis-ში შენახვა
```

### სტრუქტურა (Backend)

```
src/main/java/
├── servlet/
│   └── BookServlet.java   ← HTTP layer
├── dao/
│   └── BookDao.java       ← SQL queries
├── db/
│   └── DataSource.java    ← კავშირი DB-სთან (Singleton)
└── model/
    └── Book.java          ← მოდელი
```

### სტრუქტურა (Frontend)

```
src/
├── index.ts               ← UI logic
├── api/bookApi.ts         ← HTTP requests
├── service/bookService.ts ← business logic
└── model/book.ts          ← interface
```

---

### გაშვება

```bash
docker-compose up --build
```

აპი გაიხსნება: [http://localhost:8080](http://localhost:8080)

### API

| Method | URL | აღწერა |
|--------|-----|--------|
| GET | `/book?id=1` | წიგნის მოძებნა ID-ით |
| POST | `/book?title=Clean+Code` | ახალი წიგნის დამატება |

### შენიშვნა

პირველი მოთხოვნა MySQL-იდან მოდის, შემდეგი კი Redis-იდან (ქეში).
ბრაუზერში `Source: MYSQL` ან `Source: REDIS` გამოჩნდება.

-------------

### Debugger-ის დაკავშირება IntelliJ-ში (პორტი 5005)

1. **Run → Edit Configurations**
2. **+** → **Remote JVM Debug**
3. შეავსეთ მონაცემები:
    - **Name:** Docker Debug
    - **Host:** `localhost`
    - **Port:** `5005`
4. **OK**
5. დოკერის პარალელურად, გაუშვით შექმნილი Debug კონფიგურაცია და დასვით ბრეიქფოინთები კოდში.

-------------
