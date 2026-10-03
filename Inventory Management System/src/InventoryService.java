import java.util.*;

public class InventoryService {

    // to store products data
    private static Map<Integer, Product> products = new HashMap<>();

    // 1. to add product
    public void addProduct(Scanner sc) {

        // product id is generated randomly
        int id = new Random().nextInt(Integer.MAX_VALUE) + 1;

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        if(name.isBlank())
            throw new InventoryException("Name cannot be blank");

        System.out.println("Select Category:");
        System.out.println("1. LAPTOP");
        System.out.println("2. MOBILE");
        System.out.println("3. ACCESSORY");
        System.out.println("4. HOME_APPLIANCE");

        int categoryChoice;

        try {
            System.out.print("Enter category: ");
            categoryChoice = Integer.parseInt(sc.nextLine());
        }
        catch (NumberFormatException e) {
            throw new InventoryException("select valid category");
        }

        Category category;

        switch (categoryChoice) {

            case 1:
                category = Category.LAPTOP;
                break;

            case 2:
                category = Category.MOBILE;
                break;

            case 3:
                category = Category.ACCESSORY;
                break;

            case 4:
                category = Category.HOME_APPLIANCE;
                break;

            default:
                throw new InventoryException("Invalid category");
        }
            double price; int stock;
        try {
            System.out.print("Enter Price: ");
            price = Double.parseDouble(sc.nextLine());
            if(price <= 0)
                throw new InventoryException("Price cannot be negative or zero");
            System.out.print("Enter Stock: ");
            stock = Integer.parseInt(sc.nextLine());
            if(stock <= 0)
                throw new InventoryException("Stock cannot be negative or zero");

        }
        catch (NumberFormatException e) {
             throw new InventoryException("enter valid input");
        }

        Product product = new Product.Builder()
                .productId(id)
                .productName(name)
                .category(category)
                .price(price)
                .stock(stock)
                .build();

        if (products.containsKey(product.getProductId())) {
            System.out.println("Product ID already exists.");
            return;
        }

        products.put(product.getProductId(), product);

        System.out.println("Product added successfully.");
    }

    // 2. for searching product
    public Product searchProduct(int productId) {

        Product product = products.get(productId);

        if (product == null) {
            throw new InventoryException("Product not found");
        }

        return product;
    }

    // 3. to update price
    public void updatePrice(int productId, double newPrice) {

        Product product = searchProduct(productId);

        product.updatePrice(newPrice);

        System.out.println("Price updated successfully.");
    }

    // 4. adding stock
    public void addStock(int productId, int quantity) {

        Product product = searchProduct(productId);

        product.addStock(quantity);

        System.out.println("Stock added successfully.");
    }

    // 5. product selling
    public  void sellProduct(int productId, int quantity) {

        Product product = searchProduct(productId);

        double total = product.getPrice() * quantity;

        product.sellProduct(quantity);

        double totalPrice = product.getPrice() * quantity;

       Bill productBill = new Bill.Builder().productId(product.getProductId())
               .name(product.getProductName()).pricePerEach(product.getPrice()).quantity(quantity).totalPrice(totalPrice).build();

       System.out.println("Product sold successfully.");
       productBill.displayBill();

    }

    // 6. viewing all products
    public void showAllProducts() {

        if (products.isEmpty()) {
            throw new InventoryException("No products available.");
        }

       for (Product product : products.values()) {
           System.out.println(product);
           }
    }

    // 7. to show products with stock less than 5 items
    public void showLowStockProducts() {

        boolean found = false;

        for (Product product : products.values()) {

            if (product.getStock() < 5) {
                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            throw new InventoryException("No products with stock below 5.");
        }
    }

    // 8. product deletion
    public void deleteProduct(int productId) {

        Product product = products.remove(productId);

        if (product == null) {
            throw new InventoryException("Product not found.");
        } else {
            System.out.println("Product deleted successfully.");
        }
    }
}
