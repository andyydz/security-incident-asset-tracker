package securitytracker;

import java.util.Scanner;

public class SecurityTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AssetDAO assetDAO = new AssetDAO();
        IncidentDAO incidentDAO = new IncidentDAO();

        int choice;
        do {
            System.out.println("\n--- Security Incident & Asset Tracker ---");
            System.out.println("1. Add Asset");
            System.out.println("2. View Assets");
            System.out.println("3. Update Asset Owner");
            System.out.println("4. Delete Asset");
            System.out.println("5. Report Incident");
            System.out.println("6. View All Incidents");
            System.out.println("7. View Open Incidents by Severity");
            System.out.println("8. Resolve Incident");
            System.out.println("9. Delete Incident");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Asset name: ");
                    String name = sc.nextLine();
                    System.out.print("Asset type: ");
                    String type = sc.nextLine();
                    System.out.print("Owner: ");
                    String owner = sc.nextLine();
                    System.out.print("Location: ");
                    String location = sc.nextLine();
                    assetDAO.addAsset(name, type, owner, location);
                    break;

                case 2:
                    assetDAO.viewAssets();
                    break;

                case 3:
                    System.out.print("Asset ID: ");
                    int aid = Integer.parseInt(sc.nextLine());
                    System.out.print("New owner: ");
                    String newOwner = sc.nextLine();
                    assetDAO.updateAssetOwner(aid, newOwner);
                    break;

                case 4:
                    System.out.print("Asset ID to delete: ");
                    int delId = Integer.parseInt(sc.nextLine());
                    assetDAO.deleteAsset(delId);
                    break;

                case 5:
                    System.out.print("Asset ID this incident belongs to: ");
                    int assetIdForIncident = Integer.parseInt(sc.nextLine());
                    System.out.print("Description: ");
                    String description = sc.nextLine();
                    System.out.print("Severity (Low/Medium/High/Critical): ");
                    String severity = sc.nextLine();
                    incidentDAO.reportIncident(assetIdForIncident, description, severity);
                    break;

                case 6:
                    incidentDAO.viewIncidents();
                    break;

                case 7:
                    System.out.print("Enter severity to filter (Low/Medium/High/Critical): ");
                    String filterSeverity = sc.nextLine();
                    incidentDAO.viewOpenBySeverity(filterSeverity);
                    break;

                case 8:
                    System.out.print("Incident ID to resolve: ");
                    int resolveId = Integer.parseInt(sc.nextLine());
                    incidentDAO.resolveIncident(resolveId);
                    break;

                case 9:
                    System.out.print("Incident ID to delete: ");
                    int delIncId = Integer.parseInt(sc.nextLine());
                    incidentDAO.deleteIncident(delIncId);
                    break;

                case 0:
                    System.out.println("Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}