public class Product {

    private String productName;
    private double price;
    private int discountPercentage; // אחוז הנחה
    private boolean isInStock;

    public Product(String productName, double price, int discountPercentage) {
        this.productName = productName;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.isInStock = true;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isInStock() {
        return this.isInStock;
    }

    public void setInStock(boolean inStock) {
        this.isInStock = inStock;
    }

    public String toString() {
        return ("product name: " + this.productName + " , " + this.price + "₪" + (this.isInStock ? "is in stock" : "is not in Stock"));
    }
}