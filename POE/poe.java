import java.util.*;
import java.io.*;

public class poe
{
  public static final String[] punctuation = {".", ",", ";", ":", "?", "!", "—", "'", "\"", "(", ")", "..."
  };
  public static void main(String[] args) throws FileNotFoundException{
    Scanner sc = new Scanner(new File("poe.txt"));
    int numOfWords = 0;
    int uniqueWords = 0;
    int valMostFrequentWord = 0;
    String mostFrequentWord = "";
    long startTime = System.currentTimeMillis();
    HashMap<String, Integer> wordCounts = new HashMap<>();
    List<String> listOfUniqueWords = new ArrayList<>();

    while(sc.hasNext()){
      String str = sc.next().toLowerCase();
      for (String punct : punctuation) {
          if (str.contains(punct)) {
              //remove punctuation from the word
              str = str.replaceAll(punct, "");
          }
      }
      
      if (wordCounts.containsKey(str)){
        wordCounts.put(str, wordCounts.get(str)+1);
      }

      else{
        wordCounts.put(str, 1);
        uniqueWords++;
        listOfUniqueWords.add(str);
      }

      numOfWords++;
      if(valMostFrequentWord < wordCounts.get(str)){
        valMostFrequentWord = wordCounts.get(str);
        mostFrequentWord = str;
        
      }
    }

    sc.close();
    System.out.println("--------------------------------------");
    System.out.println("Total Number of Words in poe.txt is: " + numOfWords);
    System.out.println("Total Number of Unique Words is: " + uniqueWords);
    System.out.println(mostFrequentWord + " is the most frequent word and it occurs: " + valMostFrequentWord + " times");
    System.out.println("--------------------------------------");
    System.out.println("SORTING");

    Scanner reader = new Scanner(System.in);
    long endTime = System.currentTimeMillis();

    String[] bubbleSort = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    startTime = System.nanoTime();
    for(int i = 0; i< (bubbleSort.length - 1); i++){
        for(int x = 0; x < ((bubbleSort.length-1)-i); x++){
            if(bubbleSort[x].compareTo(bubbleSort[x+1]) > 0){
                String temp = bubbleSort[x];
                bubbleSort[x] = bubbleSort[x+1];
                bubbleSort[x+1] = temp;
            }
        }
    }
    endTime = System.nanoTime();
    System.out.println("Bubble Sort Time Elapsed: " + (endTime-startTime) + " nanoseconds");
    startTime = System.nanoTime();
    
    
    String[] mergeSort = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    Arrays.sort(mergeSort);
    endTime = System.nanoTime();
    System.out.println("Default Array Sort Time Elapsed: "+ (endTime-startTime) + " nanoseconds");
    System.out.println("--------------------------------------");
    System.out.print("Enter the Word to Search: ");
    String chosenWord = reader.nextLine().toLowerCase();
    reader.close();
    System.out.println("--------------------------------------");
    System.out.println("Hashmap Search");
    System.out.println(chosenWord + " WORD FOUND and the amount of times it occurs is: " + wordCounts.get(chosenWord)); 
    System.out.println("Elapsed Time is: " + (endTime - startTime));
    System.out.println("--------------------------------------");
    
    String[] sequenstialSearch = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    startTime = System.nanoTime();
    Boolean wordFound = false;
    System.out.println("Sequential Search");
    for(String str: sequenstialSearch){
        if(str.equals(chosenWord)){
            System.out.println(chosenWord +" WORD FOUND and the amount of times it occurs is: " + wordCounts.get(chosenWord));
            wordFound = true;
            break;
        }
    }
    if(wordFound == false){
        System.out.println(chosenWord + " WORD NOT FOUND");
    }
    endTime = System.nanoTime();
    System.out.println("Sequential Search Time Elapsed: "+ (endTime-startTime) + " nanoseconds");
    System.out.println("--------------------------------------");

    String[] binarySearch = listOfUniqueWords.toArray(new String[listOfUniqueWords.size()]);
    startTime = System.nanoTime();
    Arrays.sort(binarySearch);
    int right = binarySearch.length - 1;
    int left = 0;
    wordFound = false;
    System.out.println("Binary Search");
    while(left<=right){
        int middle = (left + right)/2;
        if(binarySearch[middle].equals(chosenWord)){
            System.out.println(chosenWord + " WORD FOUND and the amount of times it occurs is: " + wordCounts.get(chosenWord));
            wordFound = true;
            break;
        }

        else if(chosenWord.compareTo(binarySearch[middle])>0){
            left=middle+1;
        }

        else{
            right=middle-1;
        }
    }
    if(wordFound == false){
        System.out.println(chosenWord + " WORD NOT FOUND");
    }
    endTime = System.nanoTime();
    System.out.println("Binary Search Time Elapsed: "+ (endTime-startTime) + " nanoseconds");
    System.out.println("--------------------------------------");
  }
}
