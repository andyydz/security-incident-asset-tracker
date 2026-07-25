package securitytracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetDAO {

    // CREATE - add a new asset
    public void addAsset(String name, String type, String owner, String location) {
        String sql = "INSERT INTO Assets (asset_name, asset_type, owner, location) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, owner);
            ps.setString(4, location);
            ps.executeUpdate();
            System.out.println("Asset added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding asset: " + e.getMessage());
        }
    }

    // READ - view all assets
    public void viewAssets() {
        String sql = "SELECT * FROM Assets";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("asset_id") + " | "
                        + rs.getString("asset_name") + " | "
                        + rs.getString("asset_type") + " | "
                        + rs.getString("owner") + " | "
                        + rs.getString("location")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching assets: " + e.getMessage());
        }
    }

    // UPDATE - update asset owner
    public void updateAssetOwner(int assetId, String newOwner) {
        String sql = "UPDATE Assets SET owner = ? WHERE asset_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newOwner);
            ps.setInt(2, assetId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Asset updated." : "Asset ID not found.");

        } catch (SQLException e) {
            System.out.println("Error updating asset: " + e.getMessage());
        }
    }

    // DELETE - remove an asset
    public void deleteAsset(int assetId) {
        String sql = "DELETE FROM Assets WHERE asset_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assetId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Asset deleted." : "Asset ID not found.");

        } catch (SQLException e) {
            System.out.println("Error deleting asset: " + e.getMessage());
        }
    }
}
