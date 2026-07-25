package securitytracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncidentDAO {

    // CREATE - report a new incident
    public void reportIncident(int assetId, String description, String severity) {
        String sql = "INSERT INTO Incidents (asset_id, description, severity, status, reported_date) " +
                     "VALUES (?, ?, ?, 'Open', CURDATE())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assetId);
            ps.setString(2, description);
            ps.setString(3, severity);
            ps.executeUpdate();
            System.out.println("Incident reported successfully.");

        } catch (SQLException e) {
            System.out.println("Error reporting incident: " + e.getMessage());
        }
    }

    // READ - view all incidents
    public void viewIncidents() {
        String sql = "SELECT * FROM Incidents";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("incident_id") + " | Asset:" + rs.getInt("asset_id") +
                    " | " + rs.getString("description") +
                    " | Severity: " + rs.getString("severity") +
                    " | Status: " + rs.getString("status") +
                    " | Reported: " + rs.getDate("reported_date")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching incidents: " + e.getMessage());
        }
    }

    // READ (filtered) - view only Open incidents by severity
    public void viewOpenBySeverity(String severity) {
        String sql = "SELECT * FROM Incidents WHERE status = 'Open' AND severity = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, severity);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("incident_id") + " | " + rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error filtering incidents: " + e.getMessage());
        }
    }

    // UPDATE - mark an incident as Resolved
    public void resolveIncident(int incidentId) {
        String sql = "UPDATE Incidents SET status = 'Resolved', resolved_date = CURDATE() WHERE incident_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, incidentId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Incident marked as resolved." : "Incident ID not found.");

        } catch (SQLException e) {
            System.out.println("Error resolving incident: " + e.getMessage());
        }
    }

    // DELETE - remove an incident record
    public void deleteIncident(int incidentId) {
        String sql = "DELETE FROM Incidents WHERE incident_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, incidentId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Incident deleted." : "Incident ID not found.");

        } catch (SQLException e) {
            System.out.println("Error deleting incident: " + e.getMessage());
        }
    }
}