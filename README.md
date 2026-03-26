# 🖥️ Heads Up Display

A modular productivity suite built from the ground up to centralize daily workflows. This application serves as a unified interface for time management, goal tracking, and digital journaling, emphasizing modular code architecture and local data persistence.

## 🚀 Current Features (v1.0 - Desktop)
The current iteration focuses on a clean, high-utility "Command Center" layout:

* **Time & Context Engine:** Displays the current date, week of the year, and a customizable "Event Countdown" (currently configured for a personal milestone).
* **Task Management:** Integrated To-Do and Calendar lists for managing short-term and mid-term deadlines.
* **Persistent Journaling:** A fully functional journal module that captures user thoughts and archives them to local `.txt` files using File I/O.
* **Modular Design:** Built with a decoupled architecture, allowing individual widgets to be updated or replaced without breaking the main interface.

## 🛠 Technical Implementation
* **Language:** [Insert Language, e.g., Java/JavaFX]
* **Data Storage:** Local filesystem integration for persistent journal entries.
* **State Management:** Handles real-time clock updates and countdown calculations without UI lag.

## 🗺️ The Roadmap (v2.0 Redesign)
I am currently transitioning this project from a local desktop application to a **Full-Stack JavaScript web application**. 

### Phase 1: Enhanced Data Recovery
* **Journal Parsing:** Implementing a custom parser to read archived `.txt` files and populate a dynamic dropdown menu, allowing users to revisit and edit past entries.

### Phase 2: Platform Expansion
* **JS Migration:** Rebuilding the frontend in JavaScript/React to enable cross-platform access.
* **Database Integration:** Moving from flat-file storage (`.txt`) to a relational database (SQL) for more complex querying and data security.
