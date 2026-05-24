# Online Academy — O'quv Markazi Platformasi

Spring Boot yordamida qurilgan REST API — o'quv markazi uchun backend platforma.

## Texnologiyalar

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Swagger (OpenAPI)
- Maven

## Imkoniyatlar

- Foydalanuvchi ro'yxatdan o'tishi va kirishi (JWT token)
- Role asosida cheklov (STUDENT, TEACHER, ADMIN)
- Kurslar yaratish va boshqarish
- Darslar yaratish
- Kursga yozilish
- Baho qo'yish va ko'rish
- Global xato ushlash
- Ma'lumot validatsiyasi
- Swagger UI orqali API dokumentatsiya

## API Endpointlar

### Auth
| Method | URL | Tavsif |
|--------|-----|--------|
| POST | /api/auth/register | Ro'yxatdan o'tish |
| POST | /api/auth/login | Kirish |

### Users
| Method | URL | Tavsif |
|--------|-----|--------|
| GET | /api/users | Barcha userlar |
| GET | /api/users/{id} | Id bo'yicha user |
| DELETE | /api/users/{id} | User o'chirish |

### Courses
| Method | URL | Tavsif |
|--------|-----|--------|
| GET | /api/courses | Barcha kurslar |
| GET | /api/courses/{id} | Id bo'yicha kurs |
| POST | /api/courses | Kurs yaratish (TEACHER) |
| DELETE | /api/courses/{id} | Kurs o'chirish (TEACHER) |

### Lessons
| Method | URL | Tavsif |
|--------|-----|--------|
| GET | /api/lessons/course/{id} | Kurs darslari |
| GET | /api/lessons/{id} | Id bo'yicha dars |
| POST | /api/lessons | Dars yaratish (TEACHER) |
| DELETE | /api/lessons/{id} | Dars o'chirish (TEACHER) |

### Enrollments
| Method | URL | Tavsif |
|--------|-----|--------|
| POST | /api/enrollments | Kursga yozilish |
| GET | /api/enrollments/my | Mening kurslarim |
| GET | /api/enrollments/course/{id} | Kurs studentlari |

### Grades
| Method | URL | Tavsif |
|--------|-----|--------|
| POST | /api/grades | Baho qo'yish (TEACHER) |
| GET | /api/grades/my | Mening baholarim |
| GET | /api/grades/student/{id} | Student baholari (TEACHER) |
| GET | /api/grades/lesson/{id} | Dars baholari (TEACHER) |

## Ishga tushirish

### Talablar
- Java 17+
- PostgreSQL
- Maven

### Sozlash

`application.properties` da o'zgartiring:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/online_academy
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Ishga tushirish
```bash
mvn spring-boot:run
```

### Swagger UI

## Muallif
Sardor Ergashev