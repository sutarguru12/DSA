import java.util.*;

class MedianFinder {
    private PriorityQueue<Integer> lowerHalf;
    private PriorityQueue<Integer> upperHalf;

    public MedianFinder() {
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        upperHalf = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        lowerHalf.offer(num);

        upperHalf.offer(lowerHalf.poll());

        if(upperHalf.size() > lowerHalf.size()){
            lowerHalf.offer(upperHalf.poll());
        }
    }
    
    public double findMedian() {
        if(lowerHalf.size() > upperHalf.size()){
            return lowerHalf.peek();
        }
        return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */