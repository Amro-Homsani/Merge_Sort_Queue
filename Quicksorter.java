package quicksortpackage;
import java.util.Random;

public class Quicksorter {
	Queue<Integer> q = new Queue<Integer>();
	Random r = new Random();
	public Quicksorter() {
		
	}
	
	public void printQ(Queue<Integer> q) {
	    Queue<Integer> temp = new Queue<Integer>();
	    while (!q.isEmpty()) {
	    	System.out.println(q.front());
	    	temp.enqueue(q.front());		//fill temp with q
	    	q.dequeue();
	    }
	    
	    while (!temp.isEmpty()) {		//restore q
	    	q.enqueue(temp.front());
	    	temp.dequeue();
	    }
	    System.out.println("");
	}
	
	public void createRandQ(int length) {
		for (int i = 0; i < length; i++) {
			q.enqueue(r.nextInt(100 - 1 + 1) + 1);
		}
	}
	
	public Queue<Integer> mergeSort(Queue<Integer> unsortedQueue){
		if (unsortedQueue.isEmpty()) {
			return new Queue<Integer>();  //returns empty queue
		}
		Queue<Integer> unsortedQueue_storage = new Queue<Integer>(); //hold unsortedQueue in a storage queue to not loose initial queue
		unsortedQueue_storage = unsortedQueue;
		int pivot = unsortedQueue.front();
		unsortedQueue.dequeue();
		Queue<Integer> bigQ = new Queue<Integer>();
		Queue<Integer> smallQ = new Queue<Integer>();
		while(!unsortedQueue.isEmpty()) {
			if(unsortedQueue.front() > pivot){
				bigQ.enqueue(unsortedQueue.front());
				unsortedQueue.dequeue();
			} else {
				smallQ.enqueue(unsortedQueue.front());
				unsortedQueue.dequeue();
			}
		}
		smallQ = mergeSort(smallQ);
		bigQ = mergeSort(bigQ);
		smallQ.enqueue(pivot);				//add pivot to middle
		while(!bigQ.isEmpty()){				//merge both queues
			smallQ.enqueue(bigQ.front());
			bigQ.dequeue();
		}
		while (!unsortedQueue_storage.isEmpty()) {		//restore q
	    	unsortedQueue.enqueue(unsortedQueue_storage.front());
	    	unsortedQueue_storage.dequeue();
	    }
		return smallQ;
	}
	
	public static void main(String[] args) {
		Quicksorter obj = new Quicksorter();
		obj.createRandQ(20);
		obj.printQ(obj.q);
		obj.printQ(obj.mergeSort(obj.q));
	}
}
