import java.util.Scanner;

public class InventoryManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static InventoryService inventoryService = new InventoryService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n========== INVENTORY MANAGEMENT ==========");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Update Product Price");
            System.out.println("4. Add Stock");
            System.out.println("5. Sell Product");
            System.out.println("6. Show All Products");
            System.out.println("7. Show Low Stock Products");
            System.out.println("8. Delete Product");
            System.out.println("9. Exit");

            int choice;

            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
                continue;
            }

            try {

                switch (choice) {

                    case 1:

                        inventoryService.addProduct(sc);
                        break;

                    case 2:
                            System.out.print("Enter Product ID: ");
                            int searchId = Integer.parseInt(sc.nextLine());

                            Product foundProduct =
                                    inventoryService.searchProduct(searchId);

                            System.out.println(foundProduct);
                            break;

                    case 3:
                            System.out.print("Enter Product ID: ");
                            int updateId = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter New Price: ");
                            double newPrice = Double.parseDouble(sc.nextLine());

                            inventoryService.updatePrice(
                                    updateId,
                                    newPrice);

                        break;

                    case 4:
                            System.out.print("Enter Product ID: ");
                            int stockId = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter Quantity: ");
                            int quantity = Integer.parseInt(sc.nextLine());

                            inventoryService.addStock(
                                    stockId,
                                    quantity);
                        break;

                    case 5:
                            System.out.print("Enter Product ID: ");
                            int sellId = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter Quantity: ");
                            int sellQuantity = Integer.parseInt(sc.nextLine());

                            inventoryService.sellProduct(
                                    sellId,
                                    sellQuantity);
                        break;

                    case 6:

                        inventoryService.showAllProducts();
                        break;

                    case 7:

                        inventoryService.showLowStockProducts();
                        break;

                    case 8:
                            System.out.print("Enter Product ID: ");
                            int deleteId = Integer.parseInt(sc.nextLine());
                            inventoryService.deleteProduct(deleteId);
                            break;

                    case 9:

                        System.out.println("Thank you for using Inventory Management System.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InventoryException |
                     NumberFormatException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }
}