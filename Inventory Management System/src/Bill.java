public class Bill {

    private int productId;
    private String name;
    private int quantity;
    private double pricePerEach;
    private double totalPrice;

    private Bill(Builder builder) {
        this.name = builder.name;
        this.productId = builder.productId;
        this.totalPrice = builder.totalPrice;
        this.quantity = builder.quantity;
        this.pricePerEach = builder.pricePerEach;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerEach() {
        return pricePerEach;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // generating bill based on product and quantity
    public void displayBill() {
        System.out.println("\n======= Bill Details ========");
        System.out.println("Product Id: " + productId);
        System.out.println("Product Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price Per Each: %.2f\n", pricePerEach);
        System.out.printf("Total Price: %.2f", totalPrice);
    }

    public static class Builder {

        private int productId;
        private String name;
        private int quantity;
        private double pricePerEach;
        private double totalPrice;

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder pricePerEach(double pricePerEach) {
            this.pricePerEach = pricePerEach;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Bill build() {
            return new Bill(this);
        }
    }
}
