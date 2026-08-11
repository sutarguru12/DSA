class Selection_sort {
    void selectionSort(int[] arr) {
        // code here
        for(int i = 0; i < arr.length; i++){
            int min = i;
            for(int j = i; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                    
                }
            }
            //swap
                    int temp = arr[min];
                    arr[min] = arr[i];
                    arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        Selection_sort solution = new Selection_sort();
        int[] arr = {64, 25, 12, 22, 11};
        solution.selectionSort(arr);
        System.out.println("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}


