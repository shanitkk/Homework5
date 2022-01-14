public class Client {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isWorker;
    private boolean isClubMember;
    private ShoppingCart shoppingCart;
    private int counterPurchases;


    public Client(String firstName, String lastName, String username, String password, boolean isWorker, boolean isClubMember) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isWorker = isWorker;
        if (this.isWorker)
            this.isClubMember = isClubMember;
        else
            this.isClubMember = false;
        this.shoppingCart = new ShoppingCart();
        this.counterPurchases = 0;
    }

    public Client(Client other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.username = other.username;
        this.password = other.password;
        this.isWorker = other.isWorker;
        if (other.isWorker)
            this.isClubMember = other.isClubMember;
        else
            this.isClubMember = other.isClubMember;
        this.shoppingCart = other.getShoppingCart();
        this.counterPurchases = other.counterPurchases;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWorker() {
        return this.isWorker;
    }

    public void setWorker(boolean worker) {
        this.isWorker = worker;
    }

    public boolean isClubMember() {
        return this.isClubMember;
    }

    public void setClubMember(boolean clubMember) {
        this.isClubMember = clubMember;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getCounterPurchases() {
        return this.counterPurchases;
    }

    public void setCounterPurchases(int counterPurchases) {
        this.counterPurchases = counterPurchases;
    }

    public String toString() {
        return this.firstName + " " + this.lastName + (this.isClubMember ? "VIP" : " ");
    }
}