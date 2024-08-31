import java.util.*;

public class ListOfNumbers {
    // Initialization
    private Random random = new Random();
    private int size;
    private HashMap<Integer, Integer> occurrences = new HashMap<>();
    private ArrayList<Integer> setOfNumbers = new ArrayList<>();

    // Generate a list of numbers
    public ListOfNumbers(int size) {
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(20) + 1;
            setOfNumbers.add(number);
            occurrences.put(number, occurrences.getOrDefault(number, 0) + 1);
        }
    }

    // Find min value
    public int min() {
        return Collections.min(setOfNumbers);
    }

    public int max() { // Find max value
        return Collections.max(setOfNumbers);
    }

    public void printList() { // Print the list of numbers
        for (int i = 0; i < setOfNumbers.size(); i++) {
            System.out.print(setOfNumbers.get(i) + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> mode() { // Find mode(s)
        ArrayList<Integer> modes = new ArrayList<>();
        int maxOccurrences = 0;
        // loop over entries in the hashmap
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int count = entry.getValue(); // # of occurences of this number
            if (count > maxOccurrences) {
                modes.clear();
                modes.add(entry.getKey()); // value of number
                maxOccurrences = count;
            } else if (count == maxOccurrences) {
                modes.add(entry.getKey());
            }
        }
        return modes;
    }
}
