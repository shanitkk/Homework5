import java.util.Locale;
import java.util.Scanner;

public class Store {

    public static final String YES = "yes";
    public static final String NO = "no";
    public static final int PASSWORD_LENGTH = 6;
    public static final int REGULAR_WORKER = 1, MANAGER = 2, MANAGEMENT_TEAM = 3;
    public static final int MIN_PERCHES = 1;
    public static final int NUMBERING = 1;
    public static final int PRINT_ALL_CLIENTS = 1, PRINT_ALL_VIP_CLIENTS = 2, CLIENTS_THAT_PERCHES_MORE_THEN_ONCE = 3;
    public static final int HIGHEST_SUM_PERCHES = 4, ADD_NEW_PRODUCT = 5, SET_STATUS_PRODUCT = 6;
    public static final int START_PERCHES = 7, END_THE_PROGRAM = 8;
    public static final boolean IN_STOCK = true, NOT_IN_STOCK = false;
    public static final boolean IS_WORKER = true, IS_CLIENT = false;
    public static final boolean IS_NOT_CLUB_MEMBER = false;

    private Client[] clients;
    private Worker[] workers;
    private Product[] products;

    public Store() {
        this.clients = new Client[0];
        this.workers = new Worker[0];
        this.products = new Product[0];
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String firstName;
        String lastName;
        String username;
        boolean isWorkerOrClient;
        String password = "";
        int rankWorker;
        boolean isVIP;
        System.out.println("Create user \nAre you worker?");
        isWorkerOrClient = answer();
        do {
            System.out.print("\nEnter your first name: ");
            firstName = scanner.nextLine();
        } while (!(isOnlyLetters(firstName)));
        do {
            System.out.print("\nEnter your last name: ");
            lastName = scanner.nextLine();
        } while (!(isOnlyLetters(lastName)));
        do {
            System.out.print("\nEnter new username: ");
            username = scanner.nextLine();
        } while (usernameExist(username));
        do {
            System.out.print("\nEnter password, must be 6 chars: ");
            password = scanner.nextLine();
        } while (password.length() != PASSWORD_LENGTH);
        if (isWorkerOrClient) {
            do {
                System.out.println("\nEnter your rank: \n" +
                        REGULAR_WORKER + ": Regular worker, " + MANAGER + ": Manager, " + MANAGEMENT_TEAM + ": Member of the management team");
                rankWorker = scanner.nextInt();
            } while (rankWorker < REGULAR_WORKER || rankWorker > MANAGEMENT_TEAM);
            addNewWorker(firstName, lastName, username, password, IS_WORKER, IS_NOT_CLUB_MEMBER, rankWorker);
        } else {
            System.out.println("\nAre you member club?");
            isVIP = answer();
            addNewClient(firstName, lastName, username, password, IS_CLIENT, isVIP);
        }
        System.out.println("User created successfully :)");
    }

    private boolean answer() {
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        String userInput;
        do {
            System.out.println("Enter yes or no");
            userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (userInput.equals(YES))
                result = true;
            else if (userInput.equals(NO))
                result = false;
            else
                System.out.println("Invalid value");
        } while (!userInput.equals(YES) && !userInput.equals(NO));
        return result;
    }

    private boolean isOnlyLetters(String name) {
        boolean result = true;
        for (int i = 0; i < name.length(); i++) {
            if (!(Character.isAlphabetic(name.charAt(i))))
                result = false;
        }
        return result;
    }

    private boolean positiveNumber(double number) {
        boolean result = false;
        if (number > 0)
            result = true;
        return result;
    }

    private boolean usernameExist(String username) {
        boolean hasThisUsername = false;
        Client[] users = allUsers();
        for (int i = 0; i < users.length && users[i] != null; i++) {
            if (users[i].getUsername().equals(username)) {
                hasThisUsername = true;
                System.out.println("That user name is exist \n");
                break;
            }
        }
        return hasThisUsername;
    }

    private void addNewClient(String firstName, String lastName, String username, String password, boolean isWorker, boolean isClubMember) {
        Client[] copyClients = new Client[this.clients.length + 1];
        for (int i = 0; i < this.clients.length; i++) {
            copyClients[i] = this.clients[i];
        }
        Client clientToAdd = new Client(firstName, lastName, username, password, isWorker, isClubMember);
        copyClients[this.clients.length] = clientToAdd;
        this.clients = copyClients;
    }

    private void addNewWorker(String firstName, String lastName, String username, String password, boolean isWorker, boolean isClubMember, int rank) {
        Worker[] copyWorkers = new Worker[this.workers.length + 1];
        for (int i = 0; i < this.workers.length; i++) {
            copyWorkers[i] = this.workers[i];
        }
        Worker workerToAdd = new Worker(firstName, lastName, username, password, isWorker, isClubMember, rank);
        copyWorkers[this.workers.length] = workerToAdd;
        this.workers = copyWorkers;
    }

    public Client[] allUsers() {
        Client[] users = new Client[this.workers.length + this.clients.length];
        for (int i = 0, j = 0; i < users.length; i++) {
            if (i < this.workers.length)
                users[i] = this.workers[i];
            else
                users[this.workers.length + j] = this.clients[j];
        }
        return users;
    }

    public void login() {
        Client userToCheck = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAre you worker?");
        boolean isWorker = answer();
        System.out.print("\nEnter your user name: ");
        String username = scanner.nextLine();
        System.out.print("\n Enter your password: ");
        String password = scanner.nextLine();
        Client[] users = allUsers();
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                userToCheck = users[i];
                break;
            }
        }
        if (userToCheck == null) {
            System.out.println("That user is not exist");
        } else {
            if (isWorker)
                workerProgram(userToCheck);
///****************//////////**********************************************************************************************
        }
    }

    private void workerProgram(Client worker) {
        int userInput;
        System.out.println(worker);
        do {
            userInput = menuWorker();
            switch (userInput) {
                case PRINT_ALL_CLIENTS: {
                    printClient(allClient());
                    break;
                }
                case PRINT_ALL_VIP_CLIENTS: {
                    printClient(VIPClients());
                    break;
                }
                case CLIENTS_THAT_PERCHES_MORE_THEN_ONCE: {
                    printClient(productMoreThenOnce());
                    break;
                }
                case HIGHEST_SUM_PERCHES: {
                    highestPerchesPriceSum();
                    break;
                }
                case ADD_NEW_PRODUCT: {
                    createProduct();
                    break;
                }
                case SET_STATUS_PRODUCT: {
                    setProductStock();
                    break;
                }
                case START_PERCHES: { //////////////////////////////////***************************************************
                }
                case END_THE_PROGRAM:
                    break;
            }
        } while (userInput != END_THE_PROGRAM);
    }

    private int menuWorker() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        do {
            System.out.println("\n" + PRINT_ALL_CLIENTS + ": Print a list of all customers. \n" +
                    PRINT_ALL_VIP_CLIENTS + ": Print all VIP customers. \n" +
                    CLIENTS_THAT_PERCHES_MORE_THEN_ONCE + ": Print all customers purchases more than once  \n" +
                    HIGHEST_SUM_PERCHES + ": Print customer that paid highest price \n" +
                    ADD_NEW_PRODUCT + ": Add new product \n" +
                    SET_STATUS_PRODUCT + ": Change status of product \n" +
                    START_PERCHES + ": start purchases \n" +
                    END_THE_PROGRAM + ": Logout -> return to the main menu \n" + "Enter your answer: ");
            userChoice = scanner.nextInt();
        } while (userChoice < PRINT_ALL_CLIENTS || userChoice > END_THE_PROGRAM);
        return userChoice;
    }

    private Client[] allClient() // הדפסת כל הלקוחות + עובדים שביצעו רכישה אחת.
    {
        int index = 0;
        Client[] allUsers = allUsers();
        Client[] onlyClient = new Client[allUsers.length];
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i].getCounterPurchases() >= MIN_PERCHES || (!(allUsers[i].isWorker()))) {
                onlyClient[index] = allUsers[i];
                index++;
            }
        }
        Client[] newOnlyClient = removeNull(onlyClient, index);
        return newOnlyClient;
    }

    private Client[] VIPClients() { // לקוחות VIP
        int index = 0;
        Client[] allClients = allClient();
        Client[] vipClients = new Client[allClients.length];
        for (int i = 0; i < allClients.length; i++) {
            if (allClients[i].isClubMember()) {
                vipClients[index] = allClients[i];
                index++;
            }
        }
        Client[] newVipClients = removeNull(vipClients, index);
        return newVipClients;
    }

    private Client[] productMoreThenOnce() { // לקוחות שביצעו מעל רכישה אחת
        int index = 0;
        Client[] allClients = allClient();
        Client[] productsMoreThenOnce = new Client[allClients.length];
        for (int i = 0; i < allClients.length; i++) {
            if (allClients[i].getCounterPurchases() >= MIN_PERCHES) {
                productsMoreThenOnce[index] = allClients[i];
                index++;
            }
        }
        Client[] newProducts = removeNull(productsMoreThenOnce, index);
        return newProducts;
    }

    public Client[] removeNull(Client[] Clients, int index) { // הגדרת מערך חדש בגודל המתאים ללא NULL
        Client[] newClients = new Client[index];
        for (int i = 0; i < newClients.length; i++) {
            newClients[i] = Clients[i];
        }
        return newClients;
    }

    private void printClient(Client[] clients) {
        for (int i = 0; i < clients.length; i++) {
            System.out.println((i + NUMBERING) + ". " + clients[i]);
        }
    }

    private void highestPerchesPriceSum() {
        int sum = 0;
        Client[] clients = productMoreThenOnce();
        Client highestClient = new Client(clients[0]);
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getShoppingCart().getPerchesPrice() > sum) {
                sum = clients[i].getShoppingCart().getPerchesPrice();
                highestClient = new Client(clients[i]);
            }
        }
        System.out.println(highestClient);
    }

    public void createProduct() {
        Scanner scanner = new Scanner(System.in);
        String productName;
        double price;
        int discountPercentage;
        do {
            System.out.print("\nEnter product name: ");
            productName = scanner.nextLine();
        } while (!isOnlyLetters(productName));
        do {
            System.out.print("\nEnter price product: ");
            price = scanner.nextInt();
        } while (!positiveNumber(price));
        do {
            System.out.print("\nEnter price product: ");
            discountPercentage = scanner.nextInt();
        } while (!positiveNumber(discountPercentage));
        addNewProduct(productName, price, discountPercentage);
    }

    public void addNewProduct(String productName, double price, int discountPercentage) {
        Product[] copyProducts = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            copyProducts[i] = this.products[i];
        }
        Product productToAdd = new Product(productName, price, discountPercentage);
        copyProducts[this.products.length] = productToAdd;
        this.products = copyProducts;
        System.out.println("Product created successfully :)");
    }

    public void setProductStock() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        int indexProductStock;
        if (this.products.length == 0) {
            System.out.println("There is no available product.");
        } else {
            printAllProducts();
            do {
                System.out.println("Enter product number to set stock: ");
                indexProductStock = scanner.nextInt() - NUMBERING;
            } while (indexProductStock < 0 || indexProductStock > this.products.length); //////
            if (this.products[indexProductStock].isInStock()) {
                this.products[indexProductStock].setInStock(NOT_IN_STOCK);
                System.out.println("Status of product: " + this.products[indexProductStock] + " changed to not available.");
            } else {
                this.products[indexProductStock].setInStock(IN_STOCK);
                System.out.println("Status of product: " + this.products[indexProductStock] + " changed to available.");
            }
        }
    }

    public void printAllProducts() {
        for (int i = 0; i < this.products.length; i++) {
            System.out.println((i + NUMBERING) + ". " + this.products[i]);
        }
    }

    private void addProductsToPerches(Client client) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        int index = NUMBERING;
        do {
            for (int i = 0; i < this.products.length; i++) {
                if (this.products[i].isInStock()) {
                    System.out.println(index + ". " + this.products[i]);
                    index++;
                }
            }
            userChoice = scanner.nextInt() - NUMBERING;
        } while (!(userChoice == -1 || (userChoice > 0 && userChoice < index))); //////
// הודפסת המוצרים שנבחרו לעגלה של הלקוח.

    }


}