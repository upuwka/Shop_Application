package controllers;
import entity.Product;
import entity.User;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;
import types.UserType;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Shop shop = new Shop();

    public void showMenu(){
        String option;
        System.out.print("Welcome to Shop, please choose activity:\n"
                +"\n1. Add user \t\t\t5. View all products"
                +"\n2. View all users \t\t6. Buy product"
                +"\n3. Remove user \t\t\t7. Remove product"
                +"\n4. Add product"
                +"\nEnter Q to End Programm\n");
        System.out.print("\nEnter your choice:\n");
        option = scanner.nextLine();

        switch (option){
            case "1":
                addUserInfo();
                break;
            case "2":
                showAllUsers();
                break;
            case "3":
                removeUser();
                break;
            case "4":
                addProductInfo();
                break;
            case "5":
                showAllProducts();
                break;
            case "6":
                buyProduct();
                break;
            case "7":
                removeProduct();
            default:
                break;
        }
        this.showMenu();
    }

    private void showAllProducts() {
        for (Product currentProduct: shop.getProducts()){
            System.out.println(currentProduct.getId() +
                    " | " + currentProduct.getName() +
                    " | " + currentProduct.getPrice() +
                    " | " + currentProduct.getQuantity() +
                    " | " + currentProduct.getCreatedAt());
        }
    }

    private void addProductInfo() {
        Product product = new Product();
        System.out.println("Enter Product Name:");
        product.setName(scanner.nextLine());

        System.out.println("Product Price: ");
        product.setPrice(Float.parseFloat(scanner.nextLine()));

        System.out.println("Enter Product Quantity:");
        product.setQuantity(Integer.parseInt(scanner.nextLine()));

        product.setId(UUID.randomUUID());
        product.setCreatedAt(LocalDate.now());

        System.out.println(shop.createProduct(product));
    }

    private void addUserInfo() {
        System.out.println("Enter User's Name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter balance:");
        float balance = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter User Type (owner / buyer):");
        UserType userType = (scanner.nextLine().trim().toUpperCase().equals("OWNER"))
                ? UserType.OWNER : UserType.BUYER;
        LocalDate createdAt = LocalDate.now();

        User user = new User(name, email, balance, userType, createdAt);
        System.out.println(shop.createUser(user));
    }

    private void showAllUsers(){
        for (User currentUser: shop.getUsers()){
            System.out.println(currentUser.getName() +
                    " | " + currentUser.getEmail() +
                    " | " + currentUser.getBalance() +
                    " | " + currentUser.getType() +
                    " | " + currentUser.getCreatedAt());
        }
    }

    private void removeUser(){
        System.out.println("\nRemove user\n");
        System.out.println("Enter user ID: ");

        int userId = scanner.nextInt();
        String message = shop.removeUser(userId);
        System.out.println(message);
    }

    private void buyProduct() {
        System.out.println("Enter registered email address:");
        String userEmail = scanner.nextLine();
        showAllProducts();
        System.out.println("Enter product name:");
        String nameOfProduct = scanner.nextLine();
        System.out.println("Enter quantity of product:");
        Integer noOfItems = Integer.parseInt(scanner.nextLine());
        System.out.println(shop.buyProduct(nameOfProduct, userEmail, noOfItems));
    }

    private void removeProduct(){
        System.out.println("\nRemove product\n");
        System.out.println("Enter product's ID: ");

        int getId = scanner.nextInt();
        String message = shop.removeProduct(getId);
        System.out.println(message);

    }

}

