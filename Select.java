import java.util.Arrays;
import java.util.Random;

/*
 * A class that presents four methods of finding the 
 * kth-smallest value in the array.
 */
public class Select 
{
	/*
	 * Find the kth-smallest value using mergersort. Given an array, 
	 * beginning index, ending index, and the kth index inside, 
	 * perfrom sorting and then return the kth index since it 
	 * will automatically be the kth-smallest value in the array.
	 */
	public int select_1(int inputArray[], int beginningIndex, int endIndex, int k) 
	{
	    if (k > 0 && k <= endIndex - beginningIndex + 1) 
	    { 
	    	inputArray = mergeSort(inputArray);
	    	return inputArray[k - 1];
	    } else
		    return -1; 
	}
	
	/*
	 * Given an array, sort it using Mergersort.
	 */
	private int[] mergeSort(int[] inputArray) 
    {
		int inputArraySize = inputArray.length;
        if (inputArraySize <= 1) 
        	return inputArray;
        
        int[] firstHalfOfInputArray = new int[inputArraySize / 2];
        int[] secondHalfOfInputArray = new int[inputArraySize - (inputArraySize / 2)];
        
        for (int i = 0; i < firstHalfOfInputArray.length; i++)
            firstHalfOfInputArray[i] = inputArray[i];
        
        for (int i = 0; i < secondHalfOfInputArray.length; i++)
            secondHalfOfInputArray[i] = inputArray[i + (inputArraySize / 2)];
        
        return merge(mergeSort(firstHalfOfInputArray), mergeSort(secondHalfOfInputArray));
    }
    
	/*
	 * Helper method for Mergesort. Combines two arrays accordingly.
	 */
	private int[] merge(int[] firstArray, int[] secondArray) 
	{
		int[] combinedArray = new int[firstArray.length + secondArray.length];
        int firstArrayCounter = 0, secondArrayCounter = 0;
	        
        for (int k = 0; k < combinedArray.length; k++) 
        {
        	if (firstArrayCounter >= firstArray.length) 
            	combinedArray[k] = secondArray[secondArrayCounter++];
            else if (secondArrayCounter >= secondArray.length) 
            	combinedArray[k] = firstArray[firstArrayCounter++];
            else if (firstArray[firstArrayCounter] <= secondArray[secondArrayCounter])  
            	combinedArray[k] = firstArray[firstArrayCounter++];
            else
            	combinedArray[k] = secondArray[secondArrayCounter++];
        }
        return combinedArray;
    }
	
	
	
	/*
	 * Find the kth-smallest value using the partition procedure of 
	 * Quicksort iteratively. Given an array, beginning index, ending index, 
	 * and the kth index inside, return the kth-smallest value.
	 */
	public int select_2(int inputArray[], int beginningIndex, int endIndex, int k) 
	{
		if (k >= 0 && k <= endIndex - beginningIndex + 1)
		{	
			int pivot;
			while(true)
			{
				if (beginningIndex == endIndex) 
					return inputArray[beginningIndex];
				
				pivot = inputArray[endIndex];
				pivot = partition(inputArray, beginningIndex, endIndex, pivot);
				
				if (k == pivot) 
					return inputArray[k];
				else if (k < pivot) 
					endIndex = pivot - 1;
				else
					beginningIndex = pivot + 1;
			}
		} else
		    return -1; 
	}
	
	
	
	/*
	 * Find the kth-smallest value using the partition procedure of 
	 * Quicksort recursively. Given an array, beginning index, ending index, 
	 * and the kth index inside, return the kth-smallest value.
	 */	
	public int select_3(int inputArray[], int beginningIndex, int endIndex, int k) 
	{
		if (k >= 0 && k <= endIndex - beginningIndex + 1)
		{ 
			if (beginningIndex == endIndex) 
				return inputArray[beginningIndex];
			
			int pivot = inputArray[endIndex];
			pivot = partition(inputArray, beginningIndex, endIndex, pivot);
			    
			 if (k == pivot) 
				return inputArray[k];
			 else if (k < pivot) 
				return select_3(inputArray, beginningIndex, pivot - 1, k);
			 else 
				return select_3(inputArray, pivot + 1, endIndex, k);	
		} else
		    return -1; 
	}	
	
	
	
	/*
	 * Find the kth-smallest value using the median of medians
	 * technique recursively. Given an array, beginning index, 
	 * ending index, and the kth index inside, return the kth-smallest value.
	 */  
    public int select_4(int inputArray[], int beginningIndex, int endIndex, int k) 
    {
        if (k > 0 && k <= endIndex - beginningIndex + 1) 
        {
            int inputArraySize = endIndex - beginningIndex + 1;
            int tempLocator, median[] = new int[(inputArraySize + 4) / 5];

            for (tempLocator = 0; tempLocator < median.length - 1; tempLocator++) 
                median[tempLocator] = findMedian(Arrays.copyOfRange(inputArray, 5 * tempLocator + beginningIndex, 
                								5 * tempLocator + beginningIndex + 4), 5);

            if (inputArraySize % 5 == 0) 
            {
                median[tempLocator] = findMedian(Arrays.copyOfRange(inputArray, 5 * tempLocator + beginningIndex, 5 * tempLocator + beginningIndex + 4), 5);
                tempLocator++;
            } else 
            {
                median[tempLocator] = findMedian(Arrays.copyOfRange(inputArray, 5 * tempLocator + beginningIndex, 5 * tempLocator + beginningIndex + (inputArraySize % 5)), inputArraySize % 5);
                tempLocator++;
            }

            int medianOfMedians;
            if (tempLocator == 1)
            	medianOfMedians = median[tempLocator - 1];
            else
            	medianOfMedians = select_4(median, 0, tempLocator - 1, tempLocator / 2);
            

            int partitionLocator = partition(inputArray, beginningIndex, endIndex, medianOfMedians);

            if (partitionLocator - beginningIndex == k - 1)
                return inputArray[partitionLocator];
            if (partitionLocator - beginningIndex < k - 1) 
            	return select_4(inputArray, partitionLocator + 1, endIndex, k - (partitionLocator + 1) + beginningIndex);
            
            return select_4(inputArray, beginningIndex, partitionLocator - 1, k);
        } else
        	return -1;
    }

    /*
     * Returns the median element of the array. Given the array,
     * and an index, return a median value of the array.
     */
    private int findMedian(int inputArray[], int inputArraySize) 
    {
        Arrays.sort(inputArray);
        return inputArray[inputArraySize / 2];
    } 
	
    /*
     * Given array, index i, index j, 
     * swap i and j. 
     */
	private int[] swap(int[] inputArray, int i, int j) 
	{ 
	    int temp = inputArray[i]; 
	    inputArray[i] = inputArray[j]; 
	    inputArray[j] = temp; 
	    return inputArray; 
	} 
	
	/*
	 * Perform partition given the array, beginning index, end index, and arbitrary pivot.
	 */
    private int partition(int[] inputArray, int beginningIndex, int endIndex, int pivot) 
    {
        for (int i = 0; i < inputArray.length; i++) 
        {
            if (inputArray[i] == pivot) 
            {
                swap(inputArray, i, endIndex);
                break;
            }
        }
        
        int tempCounter = beginningIndex;
        int index = beginningIndex - 1;

        while (tempCounter < endIndex) 
        {
            if (inputArray[tempCounter] < pivot) 
            {
                index++;
                swap(inputArray, tempCounter, index);
            }
            tempCounter++;
        }
        index++;
        swap(inputArray, index, endIndex);
        
        return index;
    }   
    
    /*
     * Given an array, print the contents of the array
     */
	public void display(int[] inputArray)
	{
		// print the array contents
        System.out.println("The array contains: ");
        for(int i = 0; i < inputArray.length; i++)
        {
        	System.out.print(inputArray[i]);
            if(i == inputArray.length - 1) 
            	continue;
            System.out.print(", ");
        }
        System.out.println("");
	}
	
	/*
	 * Given a size n, generate unsorted array with distinct values
	 */
	public int[] generate(int arraySize) 
	{
	    int arr[] = new int[arraySize];
	    Random randomInteger = new Random();
	    
	    for(int i = 0; i < arraySize; i++)
	    {
    		boolean included = true;
    		
    		while(included)
    		{
    			int temp = randomInteger.nextInt(Math.abs(2*arraySize)) + 1;
    			boolean contains = contains(arr, temp);
    			if (!contains)
    			{
    				included = false;
    				arr[i] = temp;
    			}
    		}
	    }
	    
	    return arr;
	}
	
	/*
	 * Given an array and a value, search for the value 
	 * in the array and return
	 */
	private boolean contains(int[] inputArray, int targetValue)
	{
		for (int temp : inputArray)
		{
			if (temp == targetValue)
				return true;
		}
		
		return false;
	}
    
    
    
	
}



