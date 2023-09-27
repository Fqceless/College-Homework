package algHw3;

import java.util.ArrayList;
import java.util.Random;

public class AlgHw3 {
	
	//these will be set and read over the whole program.
	//they are what times the sorts.
	//the timer is implemented in PrintArray.
	static long start;
	static long end;
	
	public static void main(String[] args) {
		//declare vars
		final Random rand = new Random();
		//both the size of the array and the range of elements (0 to n - 1)
		final int ARRAY_SIZE = 10000000;
		
		//to run multiple tests at once
		for(int num = 0; num < 5; num++) {
			//initialize array to be sorted
			int[] numArray = new int[ARRAY_SIZE];
			for(int i = 0; i < ARRAY_SIZE; i++) {
				numArray[i] = rand.nextInt(ARRAY_SIZE);
			}
			
			//sort and print
			PrintArray(numArray,                        "Unsorted Array:");
			PrintArray(CountingSort(numArray.clone()),  "Counting Sort:");
			PrintArray(RadixSort(numArray.clone()),     "Radix Sort:");
			PrintArray(BucketSort(numArray.clone()),    "Bucket Sort:");
	  		PrintArray(MergeSort(numArray.clone()),     "Merge Sort:");
	  		PrintArray(QuickSort(numArray.clone()),     "Quick Sort:");
	  		PrintArray(HeapSort(numArray.clone()),      "Heap Sort:");
	  		PrintArray(SelectionSort(numArray.clone()), "Selection Sort:");
	  		PrintArray(BubbleSort(numArray.clone()),    "Bubble Sort:");
	  		PrintArray(InsertionSort(numArray.clone()), "Insertion Sort:");
	  		System.out.println("-----------------------------------------------------");
		}
	}
	
	/*
	 * NOTE:
	 * Just in case I don't make it clear elsewhere, I know I only had to implement 3 of the 9 sorts.
	 * I just wanted to implement them all.
	 * Sorry if that makes it harder lol.
	 */
	
	/*
	 * LINEAR SORTS
	 */
	//START COUNTING SORT
	public static int[] CountingSort(int[] numArray) {
		start = System.nanoTime();
		//NOTE:
		//this works because the range of elements is from 0 to MAX_SIZE
		int[] countArray = new int[numArray.length];
		int[] sortedArray = new int[numArray.length];
		for(int i = 0; i < numArray.length; i++) {
			countArray[numArray[i]]++;
		}
		for(int i = 1; i < numArray.length; i++) {
			countArray[i] = countArray[i] + countArray[i - 1];
		}
		for(int i = numArray.length - 1; i >= 0; i--) {
			sortedArray[countArray[numArray[i]] - 1] = numArray[i];
			countArray[numArray[i]]--;
		}
		end = System.nanoTime();
		return sortedArray;
	}
	//END COUNTING SORT
	
	//START RADIX SORT
	public static int[] RadixSort(int[] numArray) {
		start = System.nanoTime();
		for(int i = 0; i < String.valueOf(numArray.length - 1).length(); i++) {
			numArray = RadixCountingSort(numArray, i);
		}
		end = System.nanoTime();
		return numArray;
	}
	private static int[] RadixCountingSort(int[] numArray, int digit) {
		int[] countArray = new int[10];
		int[] sortedArray = new int[numArray.length];
		for(int i = 0; i < numArray.length; i++) {
			countArray[GrabDigit(numArray[i], digit)]++;
		}
		for(int i = 1; i < countArray.length; i++) {
			countArray[i] = countArray[i] + countArray[i - 1];
		}
		for(int i = numArray.length - 1; i >= 0; i--) {
			sortedArray[countArray[GrabDigit(numArray[i], digit)] - 1] = numArray[i];
			countArray[GrabDigit(numArray[i], digit)]--;
		}
		return sortedArray;
	}
	private static int GrabDigit(int num, int digit) {
		//digit = number of digits from the back starting from 0
		//ex num:   83975
		//ex digit: 43210
		String numString = String.valueOf(num);
		int returnNum;
		try {
			returnNum = Integer.parseInt(String.valueOf(numString.charAt((numString.length() - 1) - digit)));
		}
		catch(Exception e){
			return 0;
		}
		return returnNum;
	}
	//END RADIX SORT
	
	//START BUCKET SORT
	public static int[] BucketSort(int[] numArray) {
		start = System.nanoTime();
		//NOTE:
		//Book implements this with doubles, I wanted to use ints.
		//90% sure I didn't do it very well lol
		ArrayList<Integer>[] bucketList;
		if(numArray.length > 10) {
			bucketList = new ArrayList[numArray.length/10];
		}
		else {
			bucketList = new ArrayList[10];
		}
		
		for(int i = 0; i < bucketList.length; i++) {
			bucketList[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < numArray.length; i++) {
			bucketList[numArray[i]/10].add(numArray[i]);
		}
		for(int i = 0; i < bucketList.length; i++) {
			InsertionSort(bucketList[i]);
		}
		for(int i = 1; i < bucketList.length; i++) {
			bucketList[0].addAll(bucketList[i]);
		}
		for(int i = 0; i < numArray.length; i++) {
			numArray[i] = (int) bucketList[0].get(i);
		}
		
		end = System.nanoTime();
		return numArray;
	}
	//END BUCKET SORT
	
	/*
	 * NLGN SORTS
	 */
	// START MERGE SORT
	public static int[] MergeSort(int[] numArray) {
		start = System.nanoTime();
		numArray = MergeSort(numArray, 0, numArray.length - 1);
		end = System.nanoTime();
		return numArray;
	}
	private static int[] MergeSort(int[] numArray, int frontIndex, int endIndex) {
		if(frontIndex < endIndex) {
			if(frontIndex < endIndex) {
				int middleIndex = (frontIndex + endIndex)/2;
				MergeSort(numArray, frontIndex, middleIndex);
				MergeSort(numArray, middleIndex + 1, endIndex);
				Merge(numArray, frontIndex, middleIndex, endIndex);
			}
		}
		return numArray;
	}
	private static void Merge(int[] numArray, int frontIndex, int middleIndex, int endIndex) {
		int leftArrayLength = middleIndex - frontIndex + 1;
		int rightArrayLength = endIndex - middleIndex;
		int[] leftArray = new int[leftArrayLength + 1];
		int[] rightArray = new int[rightArrayLength + 1];
		
		for (int i = 0; i < leftArrayLength; i++) {
			leftArray[i] = numArray[frontIndex + i];
		}
		for (int i = 0; i < rightArrayLength; i++) {
			rightArray[i] = numArray[middleIndex + i + 1];
		}
		leftArray[leftArrayLength] = Integer.MAX_VALUE;
		rightArray[rightArrayLength] = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		for(int k = frontIndex; k <= endIndex; k++) {
			if(leftArray[i] <= rightArray[j]) {
				numArray[k] = leftArray[i];
				i++;
			}
			else {
				numArray[k] = rightArray[j];
				j++;
			}
		}
	}
	//END MERGE SORT
	
	//START QUICK SORT
	public static int[] QuickSort(int[] numArray) {
		start = System.nanoTime();
		numArray = QuickSort(numArray, 0, numArray.length - 1);
		end = System.nanoTime();
		return numArray;
	}
	private static int[] QuickSort(int[] numArray, int frontIndex, int endIndex) {
		if (frontIndex < endIndex) {
			int partitionIndex = Partition(numArray, frontIndex, endIndex);
			QuickSort(numArray, frontIndex, partitionIndex - 1);
			QuickSort(numArray, partitionIndex + 1, endIndex);
		}
		return numArray;
	}
	private static int Partition(int[] numArray, int frontIndex, int endIndex) {
		int x = numArray[endIndex];
		int i = frontIndex - 1;
		for (int j = frontIndex; j < endIndex; j++) {
			if(numArray[j] <= x) {
				i++;
				int temp = numArray[i];
				numArray[i] = numArray[j];
				numArray[j] = temp;
			}
		}
		int temp = numArray[i + 1];
		numArray[i + 1] = numArray[endIndex];
		numArray[endIndex] = temp;
		return i + 1;
	}
	//END QUICK SORT
	
	//START HEAP SORT
	public static int[] HeapSort(int[] numArray) {
		start = System.nanoTime();
		int heapSize = numArray.length;
		BuildMaxHeap(numArray, heapSize);
		for(int i = numArray.length - 1; i >= 1; i--) {
			int temp = numArray[i];
			numArray[i] = numArray[0];
			numArray[0] = temp;
			heapSize--;
			MaxHeapify(numArray, 0, heapSize);
		}
		end = System.nanoTime();
		return numArray;
	}
	private static int[] MaxHeapify(int[] numArray, int i, int heapSize) {
		int left = HeapLeft(i);
		int right = HeapRight(i);
		int largest = Integer.MIN_VALUE;
			if (left <= heapSize - 1 && numArray[left] > numArray[i]) {
				largest = left;
			}
			else {
				largest = i;
			}
			if(right <= heapSize - 1 && numArray[right] > numArray[largest]) {
				largest = right;
			}
			if(largest != i) {
				int temp = numArray[i];
				numArray[i] = numArray[largest];
				numArray[largest] = temp;
				MaxHeapify(numArray, largest, heapSize);
			}
		return numArray;
		}
	private static int[] BuildMaxHeap(int[] numArray, int heapSize) {
		for(int i = (numArray.length - 1) / 2; i >= 0; i--) {
			MaxHeapify(numArray, i, heapSize);
		}
		return numArray;
	}
	private static int HeapParent(int i) {
		return (i - 1) / 2;
	}
	private static int HeapLeft(int i) {
		return (2 * i) + 1;
	}
	private static int HeapRight(int i) {
		return (2 * i) + 2;
	}
	//END HEAP SORT
	
	/*
	 * N^2 SORTS
	 */
	//START SELECTION SORT
	public static int[] SelectionSort(int[] numArray) {
		start = System.nanoTime();
		for(int i = 0; i < numArray.length - 1; i++) {
			int min = i;
			for(int j = i; j < numArray.length; j++) {
				if(numArray[j] < numArray[min]) {
					min = j;
				}
			}
			int temp = numArray[min];
			numArray[min] = numArray[i];
			numArray[i] = temp;
		}
		end = System.nanoTime();
		return numArray;
	}
	//END SELECTION SORT
	
	//START BUBBLE SORT
	public static int[] BubbleSort(int[] numArray) {
		start = System.nanoTime();
		for(int i = 0; i < numArray.length; i++) {
			for(int j = 0; j < numArray.length - i - 1; j++) {
				if(numArray[j] > numArray[j+1]) {
					int temp = numArray[j+1];
					numArray[j+1] = numArray[j];
					numArray[j] = temp;
				}
			}
		}
		end = System.nanoTime();
		return numArray;
	}
	//END BUBBLE SORT
	
	//START INSERTION SORT
	public static int[] InsertionSort(int[] numArray) {
		start = System.nanoTime();
		for(int i = 1; i < numArray.length; i++) {
			for(int j = i; j > 0; j--) {
				if(numArray[j] < numArray[j-1]) {
					int temp = numArray[j-1];
					numArray[j-1] = numArray[j];
					numArray[j] = temp;
				}
			}
		}
		end = System.nanoTime();
		return numArray;
	}
	//END INSERTION SORT
	
	/*
	 * OTHER METHODS
	 */
	public static void PrintArray(int[] numArray, String firstLine) {
		boolean isFirst = true;
		System.out.println(firstLine);
//		for(int i : numArray) {
//			if(!isFirst) {
//				System.out.print(", " + i);
//			}
//			else {
//				System.out.print(i);
//				isFirst = false;
//			}
//		}
		System.out.println("Am I Sorted? " + isSorted(numArray));
		System.out.println("How long did I take?: " + (end - start) + " ns\n");
	}
	
	public static boolean isSorted(int[] numArray) {
		for(int i = 0; i < numArray.length - 1; i++) {
			if(numArray[i] > numArray[i+1]) {
				return false;
			}
		}
		return true;
	}
	//this is here to make bucket sort happy
	private static ArrayList<Integer> InsertionSort(ArrayList<Integer> numArray) {
		for(int i = 1; i < numArray.size(); i++) {
			for(int j = i; j > 0; j--) {
				if(numArray.get(j) < numArray.get(j-1)) {
					int temp = numArray.get(j-1);
					numArray.set(j-1, numArray.get(j));
					numArray.set(j, temp);
				}
			}
		}
		return numArray;
	}
}
