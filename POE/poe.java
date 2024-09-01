import java.util.*;
import java.io.*;

public class poe {
  public static final String[] punctuation = {".", ",", ";", ":", "?", "!", "—", "'", "\"", "(", ")", "..."};

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("poe.txt"));
    int numOfWords = 0;
    int uniqueWords = 0;
    int valMostFrequentWord = 0;
    String mostFrequentWord = "";
    HashMap<String, Integer> wordCounts = new HashMap<>();
    List<String> listOfUniqueWords = new ArrayList<>();
    long startTime, endTime;

    while (sc.hasNext()) {
      String str = sc.next().toLowerCase();
      for (String punct : punctuation) {
        if (str.contains(punct)) {
          str = str.replaceAll(punct, "");  // Remove punctuation
        }
      }

      if (wordCounts.containsKey(str)) {
        wordCounts.put(str, wordCounts.get(str) + 1);  //Word Repeats
      } else {
        wordCounts.put(str, 1);  //Unique Words
        uniqueWords++;
        listOfUniqueWords.add(str);
      }

      numOfWords++;  //Total Number of Words
      if (valMostFrequentWord < wordCounts.get(str)) {
        valMostFrequentWord = wordCounts.get(str);
        mostFrequentWord = str;  //Finds the Most Frequent Word
      }
    }

    sc.close();

    System.out.println("--------------------------------------");
    System.out.println("Total Number of Words in poe.txt is: " + numOfWords);
    System.out.println("Total Number of Unique Words is: " + uniqueWords);
    System.out.println(mostFrequentWord + " is the most frequent word and it occurs: " + valMostFrequentWord + " times");
    System.out.println("--------------------------------------");
    System.out.println("SORTING");

    // Bubble Sort
    String[] bubbleSort = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    startTime = System.nanoTime();
    for (int i = 0; i < bubbleSort.length - 1; i++){
      Boolean swapped = false;
      for (int x = 0; x < bubbleSort.length - 1 - i; x++) {
        if (bubbleSort[x].compareTo(bubbleSort[x + 1]) > 0) {
          String temp = bubbleSort[x];
          bubbleSort[x] = bubbleSort[x + 1];
          bubbleSort[x + 1] = temp;
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }

    
    endTime = System.nanoTime();
    System.out.println("Bubble Sort Time Elapsed: " + (endTime - startTime) + " nanoseconds");

    // Default Array Sort (Merge Sort)
    String[] mergeSort = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    startTime = System.nanoTime();
    Arrays.sort(mergeSort);
    endTime = System.nanoTime();
    System.out.println("Default Array Sort Time Elapsed: " + (endTime - startTime) + " nanoseconds");

    System.out.println("--------------------------------------");
    Scanner reader = new Scanner(System.in);
    System.out.print("Enter the Word to Search: ");
    String chosenWord = reader.nextLine().toLowerCase();
    reader.close();

    System.out.println("--------------------------------------");

    // HashMap Search
    System.out.println("HashMap Search");
    startTime = System.nanoTime();
    Integer count = wordCounts.get(chosenWord);
    endTime = System.nanoTime();
    if (count != null) {
      System.out.println(chosenWord + " WORD FOUND and the amount of times it occurs is: " + count);
    } else {
      System.out.println(chosenWord + " WORD NOT FOUND");
    }
    System.out.println("HashMap Search Time Elapsed: " + (endTime - startTime) + " nanoseconds");
    System.out.println("--------------------------------------");

    // Sequential Search
    System.out.println("Sequential Search");
    String[] sequentialSearch = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    boolean wordFound = false;
    startTime = System.nanoTime();
    for (String str : sequentialSearch) {
      if (str.equals(chosenWord)) {
        System.out.println(chosenWord + " WORD FOUND and the amount of times it occurs is: " + wordCounts.get(chosenWord));
        wordFound = true;
        break;
      }
    }
    endTime = System.nanoTime();
    if (wordFound == false) {
      System.out.println(chosenWord + " WORD NOT FOUND");
    }
    System.out.println("Sequential Search Time Elapsed: " + (endTime - startTime) + " nanoseconds");
    System.out.println("--------------------------------------");

    // Binary Search
    System.out.println("Binary Search");
    String[] binarySearch = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    Arrays.sort(binarySearch);
    int left = 0, right = binarySearch.length - 1;
    wordFound = false;
    startTime = System.nanoTime();
    while (left <= right) {
      int middle = (left + right) / 2;
      if (binarySearch[middle].equals(chosenWord)) {
        System.out.println(chosenWord + " WORD FOUND and the amount of times it occurs is: " + wordCounts.get(chosenWord));
        wordFound = true;
        break;
      } else if (chosenWord.compareTo(binarySearch[middle]) > 0) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }
    endTime = System.nanoTime();
    if (wordFound == false) {
      System.out.println(chosenWord + " WORD NOT FOUND");
    }
    System.out.println("Binary Search Time Elapsed: " + (endTime - startTime) + " nanoseconds");
    System.out.println("--------------------------------------");
  }
}
