public class Item {
    String name;
    String category;
    int amount;

    /**
     * To string
     *
     * @return String
     */
    public String toString() {
        return this.name + ", " + this.category + ", " + this.amount;
    }

    @Override
    /**
     *
     * Equals
     *
     * @param obj the obj
     * @return boolean
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
