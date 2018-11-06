package generalpractice.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopher {

	static class ChopSticks{
		private Lock lock;
		public ChopSticks() {
			lock=new ReentrantLock();
		}
		
		public void pickUp() {
			lock.lock();
		}
		
		public void putDown() {
			lock.unlock();
		}
	}
	
	class Philosopher implements Runnable{
		private int bites=10;
		private  ChopSticks left, right;
	
		public Philosopher(ChopSticks left, ChopSticks right) {
		 this.left=left;
		 this.right=right;
		}
		
		public void eat() {
			System.out.println("Starts eating");
			pickUp();
			chew();
			putDown();
		}
		
		public void pickUp() {
			System.out.println("Pick chopsticks");
			left.pickUp();
			right.pickUp();
		}
		
		public void putDown() {
			System.out.println("putdown chopsticks");
			left.putDown();
			right.putDown();
		}
		public void chew() {
			System.out.println("chewing");
		}
		
		public void run() {
			System.out.println("thinking");
			for(int i=0; i<bites;i++) {
				eat();
			}
		}
		
	}
	
	
	public static void main(String args[]) {
		Philosopher[] phils=new Philosopher[5];
		ChopSticks[] chop=new ChopSticks[phils.length];
		
		for(int i=0;i<chop.length;i++) {
			chop[i]= new ChopSticks();
		}
		
		for(int i=0; i<phils.length;i++) {
			ChopSticks left=chop[i];
			ChopSticks right=chop[(i+1)%chop.length];
			
			Thread t =new Thread(phils[i],"Philosopher"+(i+1));
			t.start();
		}
	}
}
