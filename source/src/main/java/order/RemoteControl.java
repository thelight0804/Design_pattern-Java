package order;

import lombok.NoArgsConstructor;

public class RemoteControl {
    /**
     * This class works as a invoker in command pattern.
     */
    // Constants
    private static final int MAX_SLOTS = 7;

    // Command -> Order
    private Order order;

    // List for concrete commands - InputOrder, CancelOrder
    private Order[] InputOrder = new Order[MAX_SLOTS];
    private Order[] CancelOrder = new Order[MAX_SLOTS];

    // Order setter which receives index
    public void setOrder(int index, Order InputOrder, Order CancelOrder) {
        this.InputOrder[index] = InputOrder;
        this.CancelOrder[index] = CancelOrder;
    }

    // Invoke InputOrder
    public void InputOrder(int index, String menu, int amount) {
        InputOrder[index].execute(menu, amount);
    }

    // Invoke CancelOrder
    public void CancelOrder(int index, String menu, int amount) {
        CancelOrder[index].execute(menu, amount);
    }

    // Empty the specific slot
    public void emptySlot(int index) {
        InputOrder[index] = new RemoteControl.NoOrder();
        CancelOrder[index] = new RemoteControl.NoOrder();
    }

    @NoArgsConstructor
    public class NoOrder implements Order{
        /**
         * This class works as a empty command in command pattern.
         */
        public void execute(String menu, int amount){
            System.out.println("No order");
        }
    }
}
