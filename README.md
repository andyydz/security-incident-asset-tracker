# Security Incident & Asset Tracker

A Java + JDBC + MariaDB console application for tracking IT assets and security incidents — built as a BCA minor project, modeled on real SOC (Security Operations Center) asset and incident management workflows.

## About

Organizations rely on numerous IT assets (laptops, servers, routers, firewalls) that need continuous monitoring. When a security incident occurs, it needs to be logged, tracked, and resolved efficiently. This project is a lightweight, database-backed system that lets you manage an asset inventory and log security incidents against those assets — classifying them by severity and tracking resolution status.

Built as part of my J2EE/JDBC coursework, with a deliberate focus on a cybersecurity-relevant problem since I'm working toward a SOC Analyst role.

## Features

- Add, view, update, and delete IT assets
- Report security incidents linked to specific assets
- Classify incidents by severity (Low / Medium / High / Critical)
- Track incident status (Open / Resolved)
- Filter and view only Open incidents by severity
- All queries use PreparedStatement to prevent SQL injection

## Tech Stack

- **Language:** Java (JDK 17+)
- **Database:** MariaDB
- **Connectivity:** JDBC (MariaDB Connector/J)
- **IDE:** Apache NetBeans
- **Architecture:** DAO (Data Access Object) pattern

## Database Schema

Two related tables — `Assets` and `Incidents` — linked via a foreign key (`asset_id`). See [`schema.sql`](./schema.sql) for the full definition.

## How to Run

1. Install MariaDB and create the database:
   ```bash
   mysql -u root -p < schema.sql
