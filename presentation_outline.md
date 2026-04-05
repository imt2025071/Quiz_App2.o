# Quizora App: Project Presentation Outline

Use this outline to create your PowerPoint presentation. Each slide represents a key point in your presentation.

---

## Slide 1: Title Slide
- **Title:** Quizora - A Comprehensive Mobile Quiz Ecosystem
- **Subtitle:** Connecting Teachers and Students Digitally
- **Team Name / Members:** (List all 5 members)
- **Course / Date:** [Insert details]

## Slide 2: Project Overview
- **What is Quizora?**
  - An Android-based educational application.
  - Features two distinct portals: **Teacher View** and **Student View**.
- **The Goal:**
  - Provide a seamless platform for educators to create custom quizzes (subjects, questions, timers).
  - Enable students to test their knowledge interactively and receive immediate results.

## Slide 3: Problem Statement & Solution
- **The Problem:** Traditional paper-based quizzes are time-consuming to create, distribute, and grade. Existing apps often lack a dedicated configuration module tailored for educators.
- **The Solution (Quizora):**
  - Instant digital quizzes accessible anywhere.
  - Automated grading system saving teacher hours.
  - Intuitive configurations for timers and question counts.

## Slide 4: Tech Stack Overview
- **Frontend / UI:** Android XML (Material Design principles)
- **Programming Language:** Java for Android Development
- **IDE:** Android Studio
- **Database Backend:** SQLite (local storage) or Firebase (cloud integration for sharing quizzes)

---

## Team Task Distribution (The 5-Member Plan)

To ensure equal contribution and smooth development, the workload has been divided into five core pillars.

## Slide 5: Member 1 - UI/UX Designer & Project Lead
- **Role Summary:** Lead the user experience design and manage project timelines.
- **Specific Tasks:**
  - Design intuitive XML layouts (`activity_main.xml`, `activity_role_selection.xml`, etc.).
  - define color palettes (`colors.xml`), themes, and typography.
  - Ensure the app is responsive across different Android screen sizes.
  - Coordinate the final integration of all team members' code.

## Slide 6: Member 2 - Teacher Setup Developer
- **Role Summary:** Build the initial Configuration flow for the Teacher Module.
- **Specific Tasks:**
  - Develop `TeacherConfigActivity.java`.
  - Handle user input for Quiz properties (Subject, Number of Questions, Timer limits).
  - Implement form validation ensuring no fields are left blank.
  - Transition the data safely through Intents to the Question Creation phase.

## Slide 7: Member 3 - Question Creation Logic Developer
- **Role Summary:** Implement the dynamic question and answer input system.
- **Specific Tasks:**
  - Develop `CreateQuestionActivity.java`.
  - Build logic to intake questions one by one based on the teacher's desired question count.
  - Capture 4 multiple-choice options for each question.
  - Capture the correct answer designation and prepare the final quiz package to be saved.

## Slide 8: Member 4 - Student Execution Developer
- **Role Summary:** Build the core quiz-taking interface for students.
- **Specific Tasks:**
  - Develop the `StudentQuizActivity.java` interface.
  - Implement the Countdown Timer based on the teacher's settings.
  - Display questions dynamically with selectable options.
  - Log correct/incorrect choices and generate a final score when the timer ends or the quiz is completed.

## Slide 9: Member 5 - Backend & Database Engineer
- **Role Summary:** Handle data persistence and retrieval.
- **Specific Tasks:**
  - Set up local storage (SQLite/Room) or cloud storage (Firebase Realtime Database) to hold the Quiz Data Models.
  - Write methods to save a newly created quiz from the Teacher Module to the database.
  - Write methods to fetch the saved quiz from the database when a Student opens the app.
  - Ensure data state is maintained (e.g., questions aren't lost if the app is closed abruptly).

---

## Slide 10: Future Enhancements (Roadmap)
- Leaderboard functionality for competitive learning.
- Analytics dashboard for teachers to see which questions were the hardest.
- Exporting Quiz Results to PDF or CSV format.

## Slide 11: Q&A
- Thank the audience.
- Open the floor for questions.
