public class Product {

    private int productId;
    private String productName;
    private Category category;
    private double price;
    private int stock;

    // Private constructor
    private Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.category = builder.category;
        this.price = builder.price;
        this.stock = builder.stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // add stock logic
    public void addStock(int quantity) {

        if (quantity <= 0) {
            throw new InventoryException("Stock quantity must be greater than 0");
        }

        stock += quantity;
    }

    // sell product logic
    public void sellProduct(int quantity) {

        if (quantity <= 0) {
            throw new InventoryException("Quantity must be greater than 0");
        }

        if (stock == 0) {
            throw new InventoryException("Product is out of stock");
        }

        if (quantity > stock) {
            throw new InventoryException("stock unavailable");
        }

        stock -= quantity;
    }

    // price updation logic
    public void updatePrice(double newPrice) {

        if (newPrice <= 0) {
            throw new InventoryException("Price must be greater than 0");
        }

        price = newPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "{ ID: %d, Name: %s, Category: %s, Price: %.2f, AvailableStock: %d }",
                productId,
                productName,
                category,
                price,
                stock
        );
    }

    // builder pattern for easy object creation
    public static class Builder {

        private int productId;
        private String productName;
        private Category category;
        private double price;
        private int stock;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
