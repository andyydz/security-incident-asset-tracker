cat > ~/security-incident-asset-tracker/schema.sql << 'EOF'
CREATE DATABASE SecurityDB;
USE SecurityDB;

CREATE TABLE Assets (
    asset_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_name VARCHAR(100) NOT NULL,
    asset_type VARCHAR(50),
    owner VARCHAR(100),
    location VARCHAR(100)
);

CREATE TABLE Incidents (
    incident_id INT AUTO_INCREMENT PRIMARY KEY,
    asset_id INT,
    description VARCHAR(255),
    severity VARCHAR(20),
    status VARCHAR(20) DEFAULT 'Open',
    reported_date DATE,
    resolved_date DATE,
    FOREIGN KEY (asset_id) REFERENCES Assets(asset_id)
);
EOF
