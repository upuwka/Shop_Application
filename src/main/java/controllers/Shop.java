package controllers;

import entity.Product;
import entity.User;
import java.util.ArrayList;

public class Shop {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<User> users = new ArrayList<User>();

    public String createUser(User newUser) {
        users.add(newUser);
        return "user was added successfully";
    }

    public String removeUser(int userId){
        try {
            users.remove(userId);
        }catch (Exception ex){
            return "Unable to remove user";
        }
        return "User removed successfully!\n";
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String createProduct(Product product) {
        products.add(product);
        return "product created successfully";
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String buyProduct(String productName, String userEmail, Integer noOfProduct) {
        Product productToSell = findProductByName(productName);
        if (productToSell == null) {
            return "product not found";
        }
        if (productToSell.getQuantity() < noOfProduct) {
            return "product out off stock";
        }
        User buyer = findUserByEmail(userEmail);
        if (buyer == null) {
            return "user not found";
        }
        float buyerBalance = buyer.getBalance();
        float totalAmountOfPurchase = productToSell.getPrice() * noOfProduct;
        if (buyerBalance < totalAmountOfPurchase) {
            return "not enough balance on user's account";
        }
        buyer.setBalance(buyerBalance - totalAmountOfPurchase);
        System.out.println("Total to pay: "+ totalAmountOfPurchase + "€\n");
        updateBuyer(buyer);
        productToSell.setQuantity(productToSell.getQuantity() - noOfProduct);
        updateProduct(productToSell);
           return "Thank you for your purchase!\n";
    }

    private Product findProductByName(String productName) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getName().toLowerCase().equals(productName.toLowerCase())) {
                return currentProduct;
            }
        }
        return null;
    }

    private User findUserByEmail(String userEmail) {
        for (User currentUser : this.users) {
            if (currentUser.getEmail().toLowerCase().equals(userEmail.toLowerCase())) {
                return currentUser;
            }
        }
        return null;
    }

    private void updateBuyer(User buyerToUpdate) {
        for (User currentUser : this.users) {
            if (currentUser.getId().equals(buyerToUpdate.getId())) {
                currentUser.setBalance(buyerToUpdate.getBalance());
            }
            System.out.println("Your current balance is now " + buyerToUpdate.getBalance() + "€\n");
        }
    }

    private void updateProduct(Product productToUpdate) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getId().equals(productToUpdate.getId())) {
                currentProduct.setQuantity(productToUpdate.getQuantity());
            }
        }
    }

    public String removeProduct(int getId){
        try {
            products.remove(getId);
        }catch (Exception ex){
            return "Unable to remove product";
        }
        return "Product removed successfully!\n";
    }

}