import java.util.*;
import java.lang.*;
import java.io.*;

class QueueDemo
{
	public static void main (String[] args) 
	{
        LinkedListQueue q = new LinkedListQueue(); 
    
        System.out.println("Enqueueing elements 10, 20, 30:");
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.printElements();
    
        System.out.println("\nDequeuing one element:");
        System.out.println("Removed: " + q.dequeue());
        q.printElements();
    
        System.out.println("\nPeeking front element: " + q.peek());
        q.printElements();
    
        System.out.println("\nDequeuing all remaining elements:");
        System.out.println("Removed: " + q.dequeue());
        System.out.println("Removed: " + q.dequeue());
        System.out.println("Trying to dequeue again:");
        q.dequeue();  // should show no element to remove
    
        System.out.println("\nQueue empty? " + q.isEmpty());
        System.out.println("Current size: " + q.size());
    
        System.out.println("\nEnqueueing 40 and 50:");
        q.enqueue(40);
        q.enqueue(50);
        q.printElements();
    
        System.out.println("\nPeeking front element: " + q.peek());
        System.out.println("Final size: " + q.size());

	}
}

class LinkedListQueue{
    private class Node{
        int val;
        Node next;
        
        public Node(int val){
            this.val=val;
        }
        
        public Node(int val,Node next){
            this.val=val;
            this .next=next;
        }
    }
    
    int size=0;
    Node front;
    Node last;
    
    void enqueue(int val){
        if(size==0){
            Node node=new Node(val);
            front=last=node;
            size++;
            return;
        }
        Node node=new Node(val);
        last.next=node;
        last=node;
        size++;
    }
    
    int dequeue(){
        if(size==0){
            System.out.println("No element to remove");
            return -1;
        }
        
        int removed=front.val;
        front=front.next;
        size--;
        
        if(size==0){
            last=null;
        }

        return removed;
    }
    
    void printElements(){
        if(front==null){
            System.out.println("No elements to print");
            return;
        }
        Node curr=front;
        while(curr!=null){
            System.out.print(curr.val);
            if(curr.next!=null){
                System.out.print(" -> ");
            }
            curr=curr.next;
        }
        System.out.println("");
    }
    
    int size(){
        return size;
    }
    
    boolean isEmpty(){
        return size==0;
    }
    
    int peek(){
        if(front==null){
            System.out.println("No elements to peek at");
            return -1;
        }
        return front.val;
        
    }
	    
}