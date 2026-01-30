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
   git clone [https://github.com/your-username/logging-webapp.git](https://github.com/your-username/logging-webapp.git)
   cd logging-webapp