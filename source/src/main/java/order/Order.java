package order;


public interface Order {
    /**
     * This class follows the Command design pattern.
     * extend this class on each order type.
     */
    public void execute(String menu, int amount);
}
