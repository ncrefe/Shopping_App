import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class File I O
 */
public class FileIO {

    /**
     * Read inventory
     *
     * @return InventoryBag<Item>
     * @throws FileNotFoundException
     */
    public static InventoryBag<Item> readInventory() throws FileNotFoundException {

        Scanner inFile = new Scanner(new File("src/inventory.txt"));
        String line = null;

        Item[] items = new Item[14];
        int index = 0;

        while (inFile.hasNextLine()) {
            line = inFile.nextLine();

            String[] params = line.split(",");

            Item item = new Item();

            item.name = params[0];
            item.category = params[1];
            item.amount = Integer.parseInt(params[2]);

            items[index] = item;
            index++;
        }

        InventoryBag<Item> inventoryBag = new InventoryBag<Item>();
        inventoryBag.setItems(items);
        return inventoryBag;
    }

    public static class InventoryBag<T> {

        @Override
        /**
         *
         * To string
         *
         * @return String
         */
        public String toString() {
            String arrayAsString = "\n";
            for (T t : items) {
                arrayAsString += "\t" + t.toString() + "\n";
            }
            return "InventoryBag{" + "items=" + arrayAsString + "}";
        }

        /**
         * Market list
         */
        public void marketList() {
            for (int i = 0; i < items.length; i++) {
                System.out.println((i + 1) + ")" + items[i]);
            }
        }

        /**
         * Bought
         *
         * @param index the index
         * @return Item
         */
        public Item bought(int index) {
            return (Item) items[index - 1];
        }

        private T[] items;

        /**
         * Gets the items
         *
         * @return the items
         */
        public T[] getItems() {
            return items;
        }

        /**
         * Sets the items
         *
         * @param items the items
         */
        public void setItems(T[] items) {
            this.items = items;
        }
    }


}
