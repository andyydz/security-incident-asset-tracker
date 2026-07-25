# Security Incident & Asset Tracker

A Java + JDBC + MariaDB console application for tracking IT assets and security incidents — built as a BCA minor project, modeled on real-world SOC (Security Operations Center) asset and incident management workflows.

![Java](https://img.shields.io/badge/Java-JDK%2017+-orange?logo=openjdk)
![MariaDB](https://img.shields.io/badge/Database-MariaDB-003545?logo=mariadb)
![JDBC](https://img.shields.io/badge/Connectivity-JDBC-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)

---

## About

Organizations rely on a large number of IT assets — laptops, servers, routers, firewalls — that require continuous monitoring to stay secure. When a security incident occurs, it needs to be logged, classified, and resolved efficiently.

This project is a lightweight, database-backed system that lets a security team manage an asset inventory and log incidents against those assets, classify them by severity, and track resolution status — the same core workflow used by real SOC teams to maintain visibility over their infrastructure.

Built as part of my J2EE/JDBC coursework, with a deliberate focus on a **cybersecurity-relevant problem**, since I'm working toward a SOC Analyst (L1) role.

---

## Features

| Feature | Description |
|---|---|
| Asset Management | Add, view, update, and delete IT assets |
| Incident Reporting | Log new security incidents linked to specific assets |
| Severity Classification | Low / Medium / High / Critical |
| Status Tracking | Open / Resolved, with resolution date tracking |
| Filtered Views | View only Open incidents by severity — surface what's urgent first |
| Secure Queries | All database operations use `PreparedStatement` to prevent SQL injection |

---

## Tools & Technologies

| Category | Tool / Technology |
|---|---|
| Language | Java (JDK 17+) |
| Database | MariaDB |
| Database Connectivity | JDBC — MariaDB Connector/J |
| IDE | Apache NetBeans |
| Operating System | Kali Linux |
| Version Control | Git & GitHub |
| Design Pattern | DAO (Data Access Object) |

---

##  Database Schema

The database (`SecurityDB`) consists of two related tables, linked via a foreign key:

**Assets**
| Field | Type | Key |
|---|---|---|
| asset_id | INT (Auto Increment) | Primary Key |
| asset_name | VARCHAR(100) | |
| asset_type | VARCHAR(50) | |
| owner | VARCHAR(100) | |
| location | VARCHAR(100) | |

**Incidents**
| Field | Type | Key |
|---|---|---|
| incident_id | INT (Auto Increment) | Primary Key |
| asset_id | INT | Foreign Key → Assets |
| description | VARCHAR(255) | |
| severity | VARCHAR(20) | |
| status | VARCHAR(20) | Default: 'Open' |
| reported_date | DATE | |
| resolved_date | DATE | |

Full definition: [`schema.sql`](./schema.sql)

---

##  Project Structure

```
security-incident-asset-tracker/
├── DBConnection.java     # Handles the database connection
├── AssetDAO.java         # CRUD operations for the Assets table
├── IncidentDAO.java      # CRUD operations for the Incidents table
├── SecurityTracker.java  # Console menu / application entry point
├── schema.sql            # Database schema (tables + relationships)
├── LICENSE                # MIT License
└── README.md
```

---

##  How to Run

**1. Set up the database**
```bash
mysql -u root -p < schema.sql
```

**2. Configure credentials**

Update the database URL, username, and password in `DBConnection.java` to match your local MariaDB setup.

**3. Compile and run**
```bash
javac *.java
java -cp .:/path/to/mariadb-java-client.jar SecurityTracker
```

**4. Use the console menu**
```
--- Security Incident & Asset Tracker ---
1. Add Asset
2. View Assets
3. Update Asset Owner
4. Delete Asset
5. Report Incident
6. View All Incidents
7. View Open Incidents by Severity
8. Resolve Incident
9. Delete Incident
0. Exit
```

---

##  Why This Project

Most academic JDBC projects default to generic problems like library or student management systems. I chose to build something that directly reflects the domain I'm working toward — security operations — so it doubles as both coursework and a genuine portfolio piece relevant to SOC Analyst roles.

---

## Author

**Andrew Vinston D Souza (Andyy)**
BCA Final Year — St. Aloysius University, Mangaluru
Aspiring SOC Analyst (L1) | Threat Detection & Incident Response

-  [LinkedIn](https://linkedin.com/in/andrew-vinston-d-souza-41699330a)
-  [GitHub](https://github.com/andyydz)
-  [TryHackMe](https://tryhackme.com/p/andyydz57)

---

##  License

This project is licensed under the MIT License — see the [LICENSE](./LICENSE) file for details.

---

*Developed with guidance from an AI assistant (Claude) for debugging, structuring, and documentation. Implementation, testing, and understanding of the code are my own.*
