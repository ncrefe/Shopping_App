public class ShoppingBasket implements IBag<Item> {
    Item[] basket = new Item[30];
    int limitBasket = 2000;
    int totalWeight = 0;
    int indexCount = 0;

    @Override
    /**
     *
     * Add
     *
     * @param newItem the new item
     * @return boolean
     */
    public boolean add(Item newItem) {
        int weight = newItem.amount;
        if (weight + totalWeight <= limitBasket) {
            totalWeight += weight;
            basket[indexCount] = newItem;
            indexCount++;
            return true;
        } else
            return false;
    }

    @Override
    /**
     *
     * Is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (basket[0] == null);
    }

    @Override
    /**
     *
     * Is full
     *
     * @return boolean
     */
    public boolean isFull() {
        return totalWeight == 2000;
    }

    @Override
    /**
     *
     * Remove by index
     *
     * @param index the index
     * @return Item
     */
    public Item removeByIndex(int index) {
        if (basket[index - 1] != null) {
            Item removedItem = basket[index - 1];
            basket[index - 1] = null;
            totalWeight -= removedItem.amount;

            for (int a = index - 1; (a - 1) != basket.length - 2; a++) {
                Item temp = basket[a];
                basket[a] = basket[a + 1];
                basket[a + 1] = temp;
            }
            indexCount--;
            return removedItem;
        } else
            return null;

    }

    @Override
    /**
     *
     * Remove
     *
     * @return Item
     */
    public Item remove() {
        return null;
    }

    @Override
    /**
     *
     * Remove
     *
     * @param item the item
     * @return Item
     */
    public Item remove(Item item) {
        return null;
    }

    @Override
    /**
     *
     * Gets the item count
     *
     * @return the item count
     */
    public int getItemCount() {
        return indexCount;
    }

    @Override
    /**
     *
     * Gets the index of
     *
     * @param item the item
     * @return the index of
     */
    public int getIndexOf(Item item) {
        for (int i = 0; i < basket.length; i++) {
            if (basket[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    /**
     *
     * Contains
     *
     * @param item the item
     * @return boolean
     */
    public boolean contains(Item item) {
        for (Item value : basket) {
            if (value.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    /**
     *
     * Display items
     *
     */
    public void displayItems() {
        int index = 1;
        for (Item item : basket) {
            if (item != null) {
                System.out.println(index + ") " + item);
                index++;
            }
        }
    }

    @Override
    /**
     *
     * Dump
     *
     */
    public void dump() {
        for (int i = 0; i < basket.length; i++) {
            basket[i] = null;
        }
        totalWeight = 0;
        indexCount = 0;
    }

    @Override
    /**
     *
     * Transfer to
     *
     * @param targetBag the target bag
     * @param item      the item
     * @return boolean
     */
    public boolean transferTo(IBag<Item> targetBag, Item item) {
        targetBag.add(item);
        return true;
    }

    /**
     * Gets the item via index
     *
     * @param index the index
     * @return the item via index
     */
    public Item getItemViaIndex(int index) {
        return basket[index - 1];
    }
}