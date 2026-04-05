from fpdf import FPDF

class PDF(FPDF):
    def header(self):
        self.set_font('Arial', 'B', 15)
        self.cell(0, 10, 'Quizora App Project Presentation', 0, 1, 'C')
        self.ln(5)

    def footer(self):
        self.set_y(-15)
        self.set_font('Arial', 'I', 8)
        self.cell(0, 10, f'Page {self.page_no()}', 0, 0, 'C')

slides = [
    {
        "title": "Slide 1: Title Slide",
        "content": [
            "Title: Quizora - A Comprehensive Mobile Quiz Ecosystem",
            "Subtitle: Connecting Teachers and Students Digitally",
            "Team Members: [Insert 5 members here]",
            "Course / Date: [Insert details]"
        ]
    },
    {
        "title": "Slide 2: Project Overview",
        "content": [
            "What is Quizora?",
            "- An Android-based educational application.",
            "- Features two distinct portals: Teacher View and Student View.",
            "",
            "The Goal:",
            "- Provide a seamless platform for educators to create custom quizzes",
            "  (subjects, questions, timers).",
            "- Enable students to test their knowledge interactively",
            "  and receive immediate results."
        ]
    },
    {
        "title": "Slide 3: Problem Statement & Solution",
        "content": [
            "The Problem:",
            "- Traditional paper-based quizzes are time-consuming.",
            "- Existing apps lack a dedicated configuration module for educators.",
            "",
            "The Solution (Quizora):",
            "- Instant digital quizzes accessible anywhere.",
            "- Automated grading system saving teacher hours.",
            "- Intuitive configurations for timers and question counts."
        ]
    },
    {
        "title": "Slide 4: Tech Stack Overview",
        "content": [
            "- Frontend / UI: Android XML (Material Design)",
            "- Programming Language: Java for Android Development",
            "- IDE: Android Studio",
            "- Database Backend: SQLite (local) or Firebase (cloud)"
        ]
    },
    {
        "title": "Slide 5: Member 1 - UI/UX Designer & Project Lead",
        "content": [
            "Role Summary: Lead the user experience design and manage timelines.",
            "Specific Tasks:",
            "- Design intuitive XML layouts (role selection, main activity).",
            "- Define color palettes, themes, and typography.",
            "- Ensure the app is responsive across different screen sizes.",
            "- Coordinate the final integration of code."
        ]
    },
    {
        "title": "Slide 6: Member 2 - Teacher Setup Developer",
        "content": [
            "Role Summary: Build the Configuration flow for the Teacher Module.",
            "Specific Tasks:",
            "- Develop TeacherConfigActivity.java.",
            "- Handle user input for Quiz properties (Subject, Q Count, Timer).",
            "- Implement form validation.",
            "- Transition the data safely through Intents."
        ]
    },
    {
        "title": "Slide 7: Member 3 - Question Creation Developer",
        "content": [
            "Role Summary: Implement the dynamic question and answer inputs.",
            "Specific Tasks:",
            "- Develop CreateQuestionActivity.java.",
            "- Build logic to intake questions one by one.",
            "- Capture 4 multiple-choice options for each question.",
            "- Capture the correct answer designation and prepare data."
        ]
    },
    {
        "title": "Slide 8: Member 4 - Student Execution Developer",
        "content": [
            "Role Summary: Build the core quiz-taking interface for students.",
            "Specific Tasks:",
            "- Develop StudentQuizActivity.java interface.",
            "- Implement the Countdown Timer based on settings.",
            "- Display questions dynamically with selectable options.",
            "- Log choices and generate a final score."
        ]
    },
    {
        "title": "Slide 9: Member 5 - Backend & Database Engineer",
        "content": [
            "Role Summary: Handle data persistence and retrieval.",
            "Specific Tasks:",
            "- Set up local storage (SQLite) or cloud (Firebase).",
            "- Write methods to save a newly created quiz.",
            "- Write methods to fetch the saved quiz for students.",
            "- Ensure data state is maintained."
        ]
    },
    {
        "title": "Slide 10: Future Enhancements (Roadmap)",
        "content": [
            "- Leaderboard functionality for competitive learning.",
            "- Analytics dashboard for teachers.",
            "- Exporting Quiz Results to PDF or CSV format."
        ]
    },
    {
        "title": "Slide 11: Q&A",
        "content": [
            "- Thank the audience.",
            "- Open the floor for questions."
        ]
    }
]

pdf = PDF()
pdf.add_page()
pdf.set_auto_page_break(auto=True, margin=15)

for slide in slides:
    pdf.set_font('Arial', 'B', 14)
    pdf.cell(0, 10, slide['title'], 0, 1)
    pdf.set_font('Arial', '', 12)
    for line in slide['content']:
        pdf.cell(0, 8, line, 0, 1)
    pdf.ln(10)  # Add space between slides

pdf.output('Quizora_Presentation.pdf')
print("PDF created successfully!")
