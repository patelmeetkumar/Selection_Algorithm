/*
 * Driver class to test the Select-kth algorithms
 */
public class Test 
{
	/*
	 * Main method to test out the average time for each algorithm for same problem.
	 */
	public static void main(String[] args) 
	{
	    Select Select_kth = new Select();    

		final int REPEAT_TIMES = 5;
		long totalTimeSelect1 = 0, totalTimeSelect2 = 0, totalTimeSelect3 = 0, totalTimeSelect4 = 0;
	        
		for (int i = 10; i > 0; i*=2) 
		{
			int[] nums = Select_kth.generate(i);
	        int[] kthSmallest = {1, nums.length/4, nums.length/2, (3*nums.length)/4, nums.length};
		        
	        int n = nums.length;
	        Select_kth.display(nums);
		        
			long startTime, endTime;
				
			for (int j = 0; j < REPEAT_TIMES; j++) 
			{
				startTime = System.nanoTime();
				int mergeSortResult = Select_kth.select_1(nums, 0, n - 1, kthSmallest[j]);
				endTime = System.nanoTime();
				System.out.println("The " + kthSmallest[j] + " smallest value in the array found using Select-kth 1 is: " + mergeSortResult);
				totalTimeSelect1 += endTime - startTime;
				
				startTime = System.nanoTime();
				int partitionQuicksortIterativeResult = Select_kth.select_2(nums, 0, n - 1, kthSmallest[j] - 1);
				endTime = System.nanoTime();
		        System.out.println("The " + kthSmallest[j] + " smallest value in the array found using Select-kth 2 is: " + partitionQuicksortIterativeResult);
				totalTimeSelect2 += endTime - startTime;
					
				startTime = System.nanoTime();
			    int partitionQuicksortRecursiveResult = Select_kth.select_3(nums, 0, n - 1, kthSmallest[j] - 1);
				endTime = System.nanoTime();
			    System.out.println("The " + kthSmallest[j] + " smallest value in the array found using Select-kth 3 is: " + partitionQuicksortRecursiveResult);
				totalTimeSelect3 += endTime - startTime;
					
				startTime = System.nanoTime();
			    int medianOfMediansResult = Select_kth.select_4(nums, 0, n - 1, kthSmallest[j]);
				endTime = System.nanoTime();
			    System.out.println("The " + kthSmallest[j] + " smallest value in the array found using Select-kth 4 is: " + medianOfMediansResult);
				totalTimeSelect4 += endTime - startTime;
					
			}
				
			totalTimeSelect1 /= REPEAT_TIMES;
			totalTimeSelect2 /= REPEAT_TIMES;
			totalTimeSelect3 /= REPEAT_TIMES;
			totalTimeSelect4 /= REPEAT_TIMES;
				
			System.out.println("For array size = " + n + ": " + 
					"\n\tSelect-kth 1 time: " + totalTimeSelect1 + " nanoseconds." + 
					"\n\tSelect-kth 2 time: " + totalTimeSelect2 + " nanoseconds." +
					"\n\tSelect-kth 3 time: " + totalTimeSelect3 + " nanoseconds." +
					"\n\tSelect-kth 4 time: " + totalTimeSelect4 + " nanoseconds.\n");	
			
			System.out.println("\n \n \n");
			
		} 
	 

		
	}
	
}

