# JournalApp
A CRUD application that lets us write our journal entries. Also implemented authentication and authorization with Spring Security. 
A **Spring Boot-based CRUD application** for managing personal journal entries with **authentication and authorization**.  
The app uses **MongoDB Atlas** for persistence, integrates a **Weather API**, and explores **email service with cron jobs** for future newsletter-like features.  

This project was mainly built as a **learning exercise** to understand how Spring Boot works end-to-end.

---

## 🚀 Features

- ✅ User authentication & authorization (**Spring Security**)
- ✅ CRUD operations for **Journal Entries**
- ✅ One-to-Many relationship: **1 User → Many Journal Entries**
- ✅ Weather API integration (sample usage in greetings)
- ✅ Email service integration (for sending emails to followers/newsletter use cases)
- ✅ Scheduled jobs (cron jobs)
- ✅ MongoDB Atlas integration for cloud storage

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **MongoDB & MongoDB Atlas**
- **Spring Data MongoDB**
- **Spring Mail**
- **RestTemplate (for API calls)**
- **Lombok**

---

## 📂 Project Structure
src/main/java/com/example/journalApp
│
├── Controller
│ ├── JournalEntryController # CRUD operations for journals
│ ├── PublicController # Public endpoints (signup, health check)
│ └── UserController # User management + Weather API integration
│
├── entities
│ ├── JournalEntry # Journal entity
│ └── Users # User entity
│
├── repository
│ ├── JournalEntryRepo # MongoRepository for journals
│ ├── UserRepo # MongoRepository for users
│ └── UserRepoImpl # Custom queries using MongoTemplate
│
├── services
│ ├── EmailService # Email integration
│ ├── JournalEntryService # Journal business logic
│ ├── UserService # User business logic
│ ├── UserDetailsServiceImpl # Spring Security integration
│ └── WeatherService # Weather API integration

 
---

## 📚 What I Learned

1. Setting up **Spring Boot projects** from scratch.
2. Implementing **CRUD** operations in Spring Boot.
3. Using **Spring Security** for Authentication & Authorization.
4. Working with the **MVC pattern**.
5. Applying **Spring annotations & conventions**.
6. Integrating **MongoDB + MongoDB Atlas** with Spring.
7. Calling **external APIs** (Weather API).
8. Sending emails via **Spring Mail**.
9. Setting up and experimenting with **Cron jobs**.

---

## 🔮 Features to Add (Future Work)

- [ ] **Following & Followers** → Users can follow each other and view journals.
- [ ] **Rich Editor API** → Write and save formatted entries.
- [ ] **Likes, Comments, Saves** → Social interactions for journals.
- [ ] **Newsletter System** → Convert email service into periodic newsletters.

---

## 📂 API Endpoints

### 🔓 Public
- `POST /public/create-user` → Register a new user.
- `GET /public/health-check` → Simple health check endpoint.

### 📒 Journal
- `GET /journal` → Get all journals of the authenticated user.
- `POST /journal` → Create a new journal entry.
- `GET /journal/id/{id}` → Get a specific journal entry (if owned by the user).
- `PUT /journal/id/{id}` → Update a journal entry.
- `DELETE /journal/id/{id}` → Delete a journal entry.

### 👤 User
- `GET /user` → Get greeting with weather details.
- `PUT /user` → Update user details.
- `DELETE /user` → Delete current user.

---

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/journal-app.git
   cd journal-app


2. Configure MongoDB (local or Atlas) in application.properties:
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster-url/db_name


3.Add your Weather API key:
weather.api.key=YOUR_API_KEY


4. Run the application:

./mvnw spring-boot:run

Test APIs using Postman or cURL.
📌 Example Entities
User
{
  "username": "megha",
  "password": "password123",
  "email": "megha@example.com",
  "sentimentAnalysis": true
}

Journal Entry
{
  "title": "My first journal",
  "content": "Today I learned Spring Boot!",
  "dateTime": "2025-09-23T14:00:00"
} 

📝 License

This project is for learning and documentation purposes.
Feel free to fork and extend it 🚀

