package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
	
	static int MAX_SIZE = 10;
	static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(MAX_SIZE);

	
	public static void main(String[] args) {
		
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		
		p.start();
		c.start();
	}
}

class Producer extends Thread
{
	private BlockingQueue<Integer> sharedQueue;

	public Producer(BlockingQueue<Integer> aQueue) {
		super("PRODUCER");
		this.sharedQueue = aQueue;
	}
		
	@Override
	public void run() {
	
		sharedQueue.clear();
		while(true)
		{
			for(int i = 1; i <= 10; i++)
			{
				System.out.println(getName() + " Produced " + i);
				try {
					sharedQueue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Consumer extends Thread
{
	private BlockingQueue<Integer> sharedQueue;

	public Consumer(BlockingQueue<Integer> aQueue) {
		super("CONSUMER");
		this.sharedQueue = aQueue;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			try {
				System.out.println(getName() + " Consumes " + sharedQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}