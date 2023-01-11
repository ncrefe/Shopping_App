public class VegetablesFruitsCompartment implements IBag<Item> {
    Item[] vegetables = new Item[30];
    int limitCompartment = 3000;
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
        if (weight + totalWeight <= limitCompartment) {
            totalWeight += weight;
            vegetables[indexCount] = newItem;
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
        return vegetables[0] == null;
    }

    @Override
    /**
     *
     * Is full
     *
     * @return boolean
     */
    public boolean isFull() {
        return totalWeight == 3000;
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
        if (vegetables[index - 1] != null) {
            Item removedItem = vegetables[index - 1];
            vegetables[index - 1] = null;
            totalWeight -= removedItem.amount;

            for (int a = index - 1; (a - 1) != vegetables.length - 2; a++) {
                Item temp = vegetables[a];
                vegetables[a] = vegetables[a + 1];
                vegetables[a + 1] = temp;
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
        for (int i = 0; i < vegetables.length; i++) {
            if (vegetables[i].equals(item)) {
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
        for (Item value : vegetables) {
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
        for (Item item : vegetables) {
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
        for (int i = 0; i < vegetables.length; i++) {
            vegetables[i] = null;
        }
        indexCount = 0;
        totalWeight = 0;

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
}
