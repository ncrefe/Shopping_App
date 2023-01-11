import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Group 14: Muhammed Efe İncir
 * <p>
 * About our shopping app: We tried to make our app as realistic as we could.
 * When you are given the command to buy a product, it allows you to buy a
 * product as long as there is space in your shopping basket instead of
 * returning to the menu. In this way, it offers time savings and increased
 * reality for the user. If you give up a product, you can drop that product
 * from your shopping basket with the instructions specified or empty your
 * entire basket at once. It provides a direct transition to home when you fill
 * your shopping basket or want to go home. Here, instead of an automatic
 * filling process, we have enabled the user to select and fill the products
 * purchased by the user. Because we wanted to offer the user with limited
 * refrigerators the choice of which product to waive. In addition, we added the
 * feature of listing the products in the refrigerator separately so that the
 * user can understand their needs more accurately.
 **/

public class ShoppingApp {

    /**
     * Main
     *
     * @param args the args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        // Create a fridge
        VegetablesFruitsCompartment vegetables = new VegetablesFruitsCompartment();
        MeatsCompartment meats = new MeatsCompartment();
        BevaragesCompartment bevarages = new BevaragesCompartment();
        SnacksCompartment snacks = new SnacksCompartment();
        // Create a home basket
        ShoppingBasket homeBasket = new ShoppingBasket();

        System.out.println("Welcome to the shopping app!");
        System.out.println("Everything is just a click away\n---------------------------");
        boolean loop = true;
        while (loop) {
            System.out.println("\n1)Go shopping");
            System.out.println("2)Go home");
            System.out.println("3)Exit from app");
            System.out.print("Choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    boolean shop = true;
                    FileIO.InventoryBag<Item> listInv = FileIO.readInventory();
                    ShoppingBasket basket = new ShoppingBasket();

                    while (shop) {
                        System.out.println("\n1)Buy product");
                        System.out.println("2)Drop product");
                        System.out.println("3)Check basket");
                        System.out.println("4)Drop everything");
                        System.out.println("5)End shopping");
                        System.out.print("Choice: ");
                        int decision = input.nextInt();

                        switch (decision) {
                            case 1:
                                System.out.println("\nTo buy a product, enter its number.\n" + "Enter 0 to check the basket.\n"
                                        + "To end shopping, enter -1.\n" + "----------------------------------");
                                while (true) {
                                    listInv.marketList();
                                    System.out.print("Choice: ");
                                    int buy = input.nextInt();
                                    if (buy == 0) {
                                        if (basket.isEmpty()) {
                                            System.out.println("\nBasket is empty");
                                        } else {
                                            System.out.println("\nProducts which are in the basket:");
                                            basket.displayItems();
                                            System.out.println();
                                        }
                                    } else if (buy == -1) {

                                        for (int i = basket.getItemCount(); i == 0; i--) {
                                            Item willTransfer = basket.removeByIndex(i);
                                            basket.transferTo(homeBasket, willTransfer);
                                        }

                                        break;
                                    } else if (buy >= 1 && buy <= 14) {
                                        Item selected = listInv.bought(buy);

                                        if (basket.add(selected)) {
                                            if (basket.isFull()) {
                                                System.out.println("\n" + selected.name + " added to basket");
                                                basket.displayItems();
                                                System.out.println("Basket is full so, shopping ended");

                                                for (int i = basket.getItemCount(); i != 0; i--) {
                                                    Item willTransfer = basket.removeByIndex(i);
                                                    basket.transferTo(homeBasket, willTransfer);
                                                }
                                                shop = false;
                                                break;
                                            } else {
                                                System.out.println("\n" + selected.name + " added to basket");
                                                basket.displayItems();
                                                System.out.println("Total basket weight is " + basket.totalWeight + " gram\n");
                                            }
                                        } else {
                                            System.out.println("\nNo enough space in your basket");
                                        }
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("\nTo drop a product, enter its number.\n" + "To end dropping, enter -1.\n"
                                        + "----------------------------------");
                                while (true) {
                                    if (basket.isEmpty()) {
                                        System.out.println("Basket is already empty");
                                        break;
                                    }
                                    basket.displayItems();
                                    System.out.print("Drop choice: ");
                                    int drop = input.nextInt();
                                    System.out.println();
                                    System.out.println();
                                    if (drop == -1) {
                                        break;
                                    } else if (basket.getItemCount() >= drop && drop > 0) {
                                        basket.removeByIndex(drop);
                                    } else {
                                        System.out.println("\nInvalid input.Please try again.");
                                    }
                                }
                                break;
                            case 3:
                                if (basket.isEmpty()) {
                                    System.out.println("\nBasket is empty");
                                } else {
                                    System.out.println("\nProducts which are in the basket:");
                                    basket.displayItems();
                                }
                                break;
                            case 4:
                                basket.dump();
                                System.out.println("\nBasket is empty now");
                                break;
                            case 5:
                                for (int i = basket.getItemCount(); i != 0; i--) {
                                    Item willTransfer = basket.removeByIndex(i);
                                    basket.transferTo(homeBasket, willTransfer);
                                }
                                shop = false;
                                break;
                        }
                    }

                case 2:
                    boolean home = true;
                    System.out.println("\nWelcome to the home");
                    while (home) {

                        System.out.println("\n1)Put the products in the fridge");
                        System.out.println("2)Check basket");
                        System.out.println("3)Check the fridge's compartments capacities");
                        System.out.println("4)Look at the list of products in the fridge");
                        System.out.println("5)Back to the main menu");
                        System.out.print("Choice: ");
                        int decision = input.nextInt();
                        switch (decision) {
                            case 1:
                                System.out.println("\nEnter -1 to choice for finishing putting products ");
                                while (true) {
                                    if (vegetables.isFull() && meats.isFull() && snacks.isFull() && bevarages.isFull()) {
                                        System.out.println("Fridge is full!");
                                        System.out.println("Thanks for using. See you soon ^^");
                                        loop = false;
                                        home = false;
                                        break;
                                    }
                                    if (homeBasket.getItemCount() == 0) {
                                        System.out.println("Basket is empty!");
                                        break;
                                    }
                                    System.out
                                            .println("\n1)Vegetables and Fruits:\t" + vegetables.totalWeight + "/" + vegetables.limitCompartment);
                                    System.out.println("2)Meats:                \t" + meats.totalWeight + "/" + meats.limitCompartment);
                                    System.out
                                            .println("3)Beverages:            \t" + bevarages.totalWeight + "/" + bevarages.limitCompartment);
                                    System.out.println("4)Snacks:               \t" + snacks.totalWeight + "/" + snacks.limitCompartment);

                                    System.out.println("\nProducts which are in the basket:");
                                    homeBasket.displayItems();
                                    System.out.print("Choice: ");
                                    int putItem = input.nextInt();
                                    if (putItem == -1) {
                                        break;
                                    } else if (homeBasket.getItemViaIndex(putItem) != null) {
                                        String itemCategory = homeBasket.getItemViaIndex(putItem).category;

                                        if (itemCategory.equals("vegetables and fruits")) {
                                            if (vegetables.add(homeBasket.getItemViaIndex(putItem))) {
                                                homeBasket.removeByIndex(putItem);
                                            } else {
                                                System.out.println("\nNo enough space in the compartment");
                                            }

                                        } else if (itemCategory.equals("meats")) {
                                            if (meats.add(homeBasket.getItemViaIndex(putItem))) {
                                                homeBasket.removeByIndex(putItem);
                                            } else {
                                                System.out.println("\nNo enough space in the compartment");
                                            }

                                        } else if (itemCategory.equals("beverages")) {
                                            if (bevarages.add(homeBasket.getItemViaIndex(putItem))) {
                                                homeBasket.removeByIndex(putItem);
                                            } else {
                                                System.out.println("\nNo enough space in the compartment");
                                            }

                                        } else {
                                            if (snacks.add(homeBasket.getItemViaIndex(putItem))) {
                                                homeBasket.removeByIndex(putItem);
                                            } else {
                                                System.out.println("\nNo enough space in the compartment");
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid input please try again");
                                    }
                                }
                                break;
                            case 2:
                                if (homeBasket.isEmpty()) {
                                    System.out.println("\nBasket is empty");
                                } else {
                                    System.out.println("\nProducts which are in the basket:");
                                    homeBasket.displayItems();
                                }
                                break;
                            case 3:
                                System.out.println("\nFridges compartments: ");
                                System.out
                                        .println("1)Vegetables and Fruits:\t" + vegetables.totalWeight + "/" + vegetables.limitCompartment);
                                System.out.println("2)Meats:                \t" + meats.totalWeight + "/" + meats.limitCompartment);
                                System.out.println("3)Beverages:            \t" + bevarages.totalWeight + "/" + bevarages.limitCompartment);
                                System.out.println("4)Snacks:               \t" + snacks.totalWeight + "/" + snacks.limitCompartment);
                                break;

                            case 4:
                                System.out.println("\n-Vegetables and Fruits:");
                                if (vegetables.isEmpty()) {
                                    System.out.println("There is no product");
                                } else {
                                    vegetables.displayItems();
                                }
                                System.out.println("-------------------------------\n-Meats:");
                                if (meats.isEmpty()) {
                                    System.out.println("There is no product");
                                } else {
                                    meats.displayItems();
                                }
                                System.out.println("-------------------------------\n-Beverages:");
                                if (bevarages.isEmpty()) {
                                    System.out.println("There is no product");
                                } else {
                                    bevarages.displayItems();
                                }
                                System.out.println("-------------------------------\n-Snacks:");
                                if (snacks.isEmpty()) {
                                    System.out.println("There is no product");
                                } else {
                                    snacks.displayItems();
                                }
                                break;
                            case 5:
                                homeBasket.dump();
                                home = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Thanks for using. See you soon ^^");
                    loop = false;
                    break;
                default:
                    System.out.println("\nInvalid input.Please try again.");
            }
        }
        input.close();
    }
}
