public class ShoppingCart {

    private Product[] products;
    private int perchesPrice;

    public ShoppingCart() {
        this.products = new Product[0];
        this.perchesPrice = 0;
    }

    public Product[] getProducts() { //*
        return this.products;
    }

    public void setProducts(Product[] products) { //*
        this.products = products;
    }

    public int getPerchesPrice() {
        return this.perchesPrice;
    }

    public void setPerchesPrice(int perchesPrice) {
        this.perchesPrice = perchesPrice;
    }
}