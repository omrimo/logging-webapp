# Logging Web Application

A lightweight, real-time log monitoring dashboard built with Java, WebSockets, and a JavaScript frontend.

## Features
* **Real-time Streaming:** Uses WebSockets to push logs instantly from server to UI.
* **Persistence:** Integrated H2 database for storing and retrieving historical logs.

---

## Project Dependencies

Managed via Maven, the project utilizes the following core libraries:

| Dependency | Purpose | Version | Scope |
| :--- | :--- | :--- | :--- |
| **Servlet API** | Handles RESTful endpoints (GET/DELETE). | `4.0.1` | `provided` |
| **WebSocket API** | Manages real-time data push. | `1.1` | `provided` |
| **H2 Database** | Fast, disk-based log storage. | `2.2.224` | `compile` |

---

## Getting Started

### Prerequisites
* **JDK 8**
* **Maven 3.6+**
* **Apache Tomcat 9.x** (or any Servlet 4.0 compatible container)

### Build Instructions
1. **Clone the repository:**
   ```bash
   git clone https://github.com/omrimo/logging-webapp.git
   cd logging-webapp
   
2. **Generate WAR file:**
   ```bash
   mvn clean package

3. **Deploy:**

   Locate the logging-webapp.war in the target/ directory and copy it to your server's webapps/ folder. 

4. **Usage:**

   1. Start your server (e.g., Tomcat). 
   2. Navigate to http://localhost:8080/logging-webapp.
   3. UI Interaction:
      1. Click Open Log Viewer to show the popup.
      2. The table starts with empty rows. New logs will fill these slots before the table grows.
      3. Click Clear to wipe the database and reset the UI.

## Project Structure
```text
logging-webapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/engineer/assignment/
│   │   │       ├── dao/                # Data Access Object (H2 Operations)
│   │   │       ├── db/                 # Database connection utilities
│   │   │       ├── generator/          # Logic to simulate/generate log data
│   │   │       ├── listener/           # AppStartupListener for background tasks
│   │   │       ├── model/              # LogMessage entity class
│   │   │       ├── servlet/            # REST API (LogServlet) & H2 Console
│   │   │       └── websocket/          # LogWebSocket for real-time streaming
│   │   └── webapp/
│   │       ├── index.html              # Main dashboard UI
│   │       ├── css/
│   │       │   └── style.css           # Popup and table styling
│   │       └── js/
│   │           └── app.js              # WebSocket handler & ghost-row logic
│   └── test/                           # Unit and integration tests
└── pom.xml                             # Maven build configuration                         # Maven dependencies (H2, Servlet, WS)