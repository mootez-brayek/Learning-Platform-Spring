# Interactive Learning Platform

An interactive educational platform designed for children, based on the Tunisian school curriculum.

The platform focuses primarily on **learning and pedagogical progression**, with gamification used only as a complementary mechanism to make learning more engaging.

> **التعلم أولاً، والتلعيب ثانياً**

## 🚧 Project Status

**In development**

The current version focuses on building the backend architecture and curriculum management system.

### Current scope

* Preschool
* Primary 1
* Primary 2

The architecture is designed to allow the addition of Primary 3–6 later without restructuring the curriculum model.

---

## 🎯 Project Goals

The platform aims to:

* Structure educational content according to school levels and subjects.
* Organize learning progressively from chapters to competencies and learning objectives.
* Provide lessons and interactive activities.
* Generate varied exercises using predefined pedagogical exercise templates.
* Use AI to generate exercise variations while keeping the educational structure controlled by the platform.
* Track student performance.
* Identify learning difficulties.
* Provide adaptive learning and remediation.
* Add gamification features to encourage engagement.

---

## 🏗️ Architecture

The project uses a **Modular Monolith** architecture.

The current business domains are:

```text
com.education.learningplatform
│
├── User
│   ├── controller
│   ├── dto
│   ├── model
│   ├── repository
│   └── service
│
├── curriculum
│   ├── controller
│   ├── dto
│   ├── models
│   ├── repository
│   └── service
│
├── auth
├── Security
└── exception
```

`User` and `curriculum` are the two main business modules.

`auth`, `Security`, and `exception` are supporting technical components.

---

## 📚 Curriculum Structure

The educational model is organized hierarchically:

```text
EducationLevel
      │
      ▼
   Subject
      │
      ▼
 LevelSubject
      │
      ▼
   Chapter
      │
      ▼
 Competency
      │
      ▼
LearningObjective
      │
      ▼
    Lesson
      │
      ▼
   Activity
      │
      ▼
   Exercise
      │
      ▼
  Assessment
      │
      ▼
 Remediation
```

### Current implementation

* `EducationLevel` ✅
* `Subject` ✅
* `LevelSubject` ✅
* `Chapter` ✅
* `Competency` ✅
* `LearningObjective` ✅
* `Lesson` 🚧
* `Activity` ⏳
* `Exercise` ⏳
* `Assessment` ⏳
* `Remediation` ⏳

---

## 🤖 AI Exercise Generation

The platform will not store thousands of generated questions in the database.

Instead, the database will contain **pedagogical exercise templates**.

Examples include:

* Multiple choice questions (QCM)
* Comparing numbers
* Comparing words
* Matching
* Completing a sentence
* Entering the correct answer
* Ordering
* Other curriculum-specific exercise types

The general process will be:

```text
Learning Objective
       │
       ▼
Exercise Template
       │
       ▼
       AI
       │
       ▼
Generated Exercise
       │
       ▼
Student Answer
       │
       ▼
Correction
       │
       ▼
Performance
```

The AI is used to generate **variations of exercises**, while the platform remains responsible for the curriculum structure and pedagogical objectives.

For deterministic exercises such as numerical calculations, the backend can handle answer validation directly instead of relying entirely on AI.

---

## 🧠 Adaptive Learning

Student performance will eventually be associated with learning objectives.

For example:

```text
Learning Objective
        │
        ▼
    Exercises
        │
        ▼
 Student Results
        │
        ▼
 Performance
        │
        ├── Mastered
        ├── In Progress
        └── Needs Remediation
```

This information will later be used to provide additional activities or exercises when a student has difficulty with a specific objective.

---

## 🎮 Gamification

Gamification is planned as a secondary feature.

Potential features include:

* XP
* Coins
* Badges
* Levels
* Streaks
* Daily missions
* Avatar
* Virtual pet
* Rewards

The goal is to encourage learning rather than turn the platform into a traditional game.

---

## 🔐 Authentication & Security

The backend uses:

* Spring Security
* JWT authentication
* BCrypt password hashing
* Role-based authorization

Current roles:

```text
ADMIN
STUDENT
```

Examples of protected operations:

```text
ADMIN
 ├── Create curriculum content
 ├── Assign subjects to levels
 ├── Create chapters
 ├── Create competencies
 ├── Create learning objectives
 └── Deactivate curriculum content

Authenticated users
 └── Read curriculum content
```

Public authentication endpoints are available under:

```text
/api/auth/**
```

---

## 🌍 Multilingual Error Handling

The backend supports localized error messages in:

* English
* French
* Arabic

Message files:

```text
messages.properties
messages_fr.properties
messages_ar.properties
```

The API uses a centralized exception handler to provide consistent error responses.

Example:

```json
{
  "code": "RESOURCE_NOT_FOUND",
  "message": "Chapter not found",
  "fields": null,
  "timestamp": "..."
}
```

---

## 🛠️ Technologies

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* JWT
* Lombok
* Bean Validation
* REST API

### Planned

* Swagger / OpenAPI
* Docker
* AI API integration
* Angular or React frontend

---

## 📁 Current Backend Structure

```text
src/main/java/com/education/learningplatform
│
├── Security/
│
├── auth/
│   ├── controller/
│   ├── dto/
│   ├── exception/
│   └── service/
│
├── User/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   └── service/
│
├── curriculum/
│   ├── controller/
│   ├── dto/
│   ├── models/
│   ├── repository/
│   └── service/
│
└── exception/
```

---

## 🚀 Development Roadmap

### Phase 1 — Foundation

* [x] Project setup
* [x] Database configuration
* [x] User management
* [x] JWT authentication
* [x] Role-based authorization
* [x] Global exception handling
* [x] Multilingual error messages

### Phase 2 — Curriculum

* [x] Education levels
* [x] Subjects
* [x] Level-subject associations
* [x] Chapters
* [x] Competencies
* [x] Learning objectives
* [ ] Lessons
* [ ] Activities
* [ ] Exercise templates
* [ ] AI exercise generation
* [ ] Assessments
* [ ] Remediation

### Phase 3 — Student Learning

* [ ] Student progress
* [ ] Objective mastery
* [ ] Exercise history
* [ ] Adaptive learning
* [ ] Personalized learning paths

### Phase 4 — Gamification

* [ ] XP
* [ ] Rewards
* [ ] Badges
* [ ] Streaks
* [ ] Levels
* [ ] Missions
* [ ] Avatar / virtual pet

### Phase 5 — Frontend

* [ ] Student interface
* [ ] Curriculum navigation
* [ ] Interactive lessons
* [ ] Exercise interface
* [ ] Progress dashboard
* [ ] Admin curriculum management

---

## 📌 Development Principle

The platform follows one main principle:

> **Learning first, gamification second.**

The curriculum, competencies, learning objectives and pedagogical progression remain the foundation of the system.

AI and gamification are designed to support this educational structure rather than replace it.
