import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Item> userInput = new ArrayList<>();
        userInput.add(new Item(5,10));
        userInput.add(new Item(4,40));
        userInput.add(new Item(6,30));
        userInput.add(new Item(4,50));
        List<Item> itemsWithRatio = assignRatio(userInput);
        List<Item> sortedItems = sortByRatio(itemsWithRatio);
        List<Item> itemsInTheKnapsack = fillKnapsack(sortedItems);
        printItemsInKnapsack(itemsInTheKnapsack);

    }

    private static class Item {
        private int weight;
        private int value;
        private double ratio;

        public Item() {
        }

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getRatio() {
            return ratio;
        }

        public void setRatio(double ratio) {
            this.ratio = ratio;
        }
    }


    public static List<Item> assignRatio(List<Item> items) {
        for (Item item : items) {
            item.setRatio((double)item.getValue() / item.getWeight());
        }
        return items;
    }


    public static List<Item> sortByRatio(List<Item> items) {
        Item tempPosition = new Item();
        int n = items.size();

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(items.get(j-1).getRatio() > items.get(j).getRatio()){
                    tempPosition = items.get(j-1);
                    items.set(j-1,items.get(j));
                    items.set(j,tempPosition);
                }

            }
        }
        return orderInDescendingOrder(items);
    }

    public static List<Item> orderInDescendingOrder(List<Item> items) {
        List<Item> descendingOrderList = new ArrayList<>();
        int i = items.size() - 1;
        do {
            descendingOrderList.add(items.get(i));
            i--;
        } while (i >= 0);
        return descendingOrderList;
    }


    public static List<Item> fillKnapsack(List<Item> orderedItems) {
        List<Item> itemsInKnapsack = new ArrayList<>();
        int knapsackAvailableCapacity = 10;
        for (Item currentItem : orderedItems) {
            if (currentItem.getWeight() < knapsackAvailableCapacity) {
                itemsInKnapsack.add(currentItem);
                knapsackAvailableCapacity = knapsackAvailableCapacity - currentItem.getWeight();
            }
        }
        return itemsInKnapsack;
    }


    public static void printItemsInKnapsack(List<Item> knapsackItems) {
        for (Item item : knapsackItems) {
            System.out.println("{value: "+item.getValue() + ", weight: "+ item.getWeight() + "}");
        }
    }
}
