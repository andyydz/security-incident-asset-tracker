-- Create the database if it does not already exist.
CREATE DATABASE IF NOT EXISTS SecurityDB;

USE SecurityDB;

-- Create the Assets table if it does not already exist.
CREATE TABLE IF NOT EXISTS Assets (
    asset_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_name VARCHAR(100) NOT NULL,
    asset_type VARCHAR(50),
    owner VARCHAR(100),
    location VARCHAR(100)
);

-- Create the Incidents table if it does not already exist.
CREATE TABLE IF NOT EXISTS Incidents (
    incident_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_id INT,
    description VARCHAR(255),
    severity VARCHAR(20),
    status VARCHAR(20) DEFAULT 'Open',
    reported_date DATE,
    resolved_date DATE,
    FOREIGN KEY (asset_id) REFERENCES Assets(asset_id)
);
