# Volunteer Hour Tracker

A command-line Java application for logging and tracking volunteer hours toward a personal goal.

---

## Features

- Add volunteer log entries with date, hours, organization, and supervisor
- View all logs with a formatted summary
- View stats including total hours, average hours per month, and top organization
- Filter logs by organization
- Sort logs chronologically by date
- View a monthly summary of hours
- Track progress toward a personal hour goal with a weekly rate calculator
- Automatically save and load logs from a file between sessions

---

## Requirements

- Java 8 or higher
- A terminal or command prompt

---

## How to run

1. Compile all `.java` files:
   ```
   javac *.java
   ```
2. Run the application:
   ```
   java Main
   ```

---

## Usage

When you launch the app, you will be prompted to enter:
- Your name
- Your total hour goal
- The number of weeks until your deadline

You can then choose from the following menu options:

| Option | Description |
|--------|-------------|
| 1 | Add a new volunteer log entry |
| 2 | View all logs |
| 3 | View stats and goal progress |
| 4 | Filter logs by organization |
| 5 | Sort logs by date |
| 6 | View monthly summary |
| 7 | Save and quit |

---

## Data format

Logs are saved to and loaded from `logs.txt` in the same directory as the program. Each line represents one log entry in the following format:

```
YYYY-MM-DD,hours,organization,supervisor
```

**Example:**
```
2026-04-21,3.0,Kaiser,Annie
2026-04-14,3.0,Kaiser,Annie
```

If no `logs.txt` file exists, the program starts fresh with no prior logs.

---

## Project structure

| File | Description |
|------|-------------|
| `Main.java` | Entry point; handles the menu loop and user input |
| `VolunteerLog.java` | Data class for a single log entry |
| `VolunteerManager.java` | Manages the collection of logs; statistics, sorting, filtering, file I/O |
| `GoalTracker.java` | Tracks progress toward a target hour goal with deadline awareness |
| `logs.txt` | Auto-generated data file (created on first save) |

---

## Author

Ryan Leslie
Version 1.0
