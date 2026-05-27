# MindMate AI

MindMate AI is an Android GenAI app prototype designed to support students and professionals with note capture, AI-style assistance, summarisation, rewriting, and study planning.

This project was developed for Task 8.2 as a working Android Studio prototype to demonstrate the core user flow and intended LLM-based functionality of the proposed application.

## Project Overview

MindMate AI acts as a personal academic productivity assistant. The app allows users to capture study notes, process them through AI-style modes, generate structured study plans, and view previous AI interactions through a local history screen.

The final concept is designed around on-device Llama 3.2 inference for privacy and offline usage. In this prototype version, the LLM workflow is simulated locally to clearly demonstrate the intended prompt-to-response behaviour inside Android Studio.

## Key Features

- Capture and save study notes locally
- Generate AI-style explanations
- Summarise long notes into key points
- Rewrite content in a clearer academic style
- Generate structured study plans
- Reuse saved notes in Assist and Plan screens
- View AI interaction history
- Clear local history
- Privacy and model information screen
- Basic unsafe input filtering
- Offline-style workflow demonstration

## Screens Included

### 1. Home Screen
The main dashboard provides navigation to all major features: Capture Notes, AI Assist, Plan Generator, AI History, and Privacy Information.

### 2. Capture Notes Screen
Users can type or paste study notes and save them locally using SharedPreferences.

### 3. AI Assist Screen
Users can enter a prompt or load saved notes, then choose from different AI modes:
- Explain
- Summarise
- Rewrite Clearly

### 4. Plan Generator Screen
Users can enter a goal or reuse saved notes to generate a structured study plan.

### 5. AI History Screen
The app stores previous AI interactions locally so the user can review generated outputs.

### 6. Privacy and Model Info Screen
This screen explains the model approach, local data handling, prototype limitations, and future on-device Llama 3.2 direction.

## Technology Stack

- Android Studio
- Java
- XML Layouts
- SharedPreferences for local storage
- Simulated local GenAI engine
- Android Emulator for testing

## LLM / GenAI Integration Approach

The proposed final system is designed to use Llama 3.2 for on-device inference. This would allow user notes and prompts to be processed locally without sending data to an external cloud service.

For this implementation stage, a simulated local AI engine is used. This engine demonstrates how the LLM feature will behave in the final version by accepting user prompts and generating structured outputs such as summaries, explanations, rewrites, and study plans.

This approach was chosen to keep the prototype stable and demonstrable inside Android Studio while still showing the intended GenAI workflow.

## Privacy Approach

MindMate AI is designed with privacy as a core feature.

In this prototype:
- No external API is used
- No user data is sent to a cloud server
- Notes are saved locally using SharedPreferences
- AI history is stored locally on the device
- The app demonstrates an offline-style workflow

In the future version, the simulated engine can be replaced with a real on-device Llama 3.2 model.

## Safety Handling

The prototype includes basic safety controls. If a user enters unsafe or harmful content, the app returns a refusal-style message instead of generating a response.

The app is mainly restricted to:
- academic support
- study planning
- note summarisation
- productivity assistance
- simple explanations

## Data Flow

User Input  
→ Capture / Assist / Plan Screen  
→ MindMateAIEngine  
→ Structured AI-style Output  
→ UI Display  
→ Optional Local History Storage

## Project Structure

```text
MindMateAI/
├── app/
│   └── src/main/
│       ├── java/com/example/mindmateai/
│       │   ├── MainActivity.java
│       │   ├── CaptureActivity.java
│       │   ├── AssistActivity.java
│       │   ├── PlanActivity.java
│       │   ├── HistoryActivity.java
│       │   ├── PrivacyActivity.java
│       │   ├── MindMateAIEngine.java
│       │   └── LocalStorageHelper.java
│       │
│       └── res/layout/
│           ├── activity_main.xml
│           ├── activity_capture.xml
│           ├── activity_assist.xml
│           ├── activity_plan.xml
│           ├── activity_history.xml
│           └── activity_privacy.xml
│
├── screenshots/
├── videos/
└── README.md
