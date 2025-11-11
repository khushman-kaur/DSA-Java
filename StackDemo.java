import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args)
	{    DynamicStack stack = new DynamicStack();
    
    System.out.println("---- Initial State ----");
    System.out.println("Is empty? " + (stack.isEmpty() ? "Yes" : "No"));
    System.out.println("Current size: " + stack.size());
    stack.printValFromTop();
    System.out.println();

    System.out.println("\n---- Trying to pop/peek on empty ----");
    stack.pop();
    System.out.println("Peek: " + stack.peek());

    System.out.println("\n---- Pushing elements ----");
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.push(40);
    stack.printValFromTop();
    System.out.println("\nCurrent size: " + stack.size());
    System.out.println("Peek (top element): " + stack.peek());

    System.out.println("\n---- Popping elements ----");
    stack.pop();
    stack.printValFromTop();
    System.out.println("\nPeek after pop: " + stack.peek());
    System.out.println("Current size: " + stack.size());

    System.out.println("\n---- Adding more elements ----");
    stack.push(50);
    stack.push(60);
    stack.printValFromTop();
    System.out.println("\nPeek: " + stack.peek());
    System.out.println("Size: " + stack.size());

    System.out.println("\n---- Emptying the stack completely ----");
    while (!stack.isEmpty()) {
        System.out.println("Popping: " + stack.peek());
        stack.pop();
    }

    System.out.println("Final size: " + stack.size());
    stack.printValFromTop();
	}
	
	
}

class DynamicStack{

    private class Node{
        int val;
        Node next;
        
        public Node(int val){
            this.val=val;
        }
        public Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
    }
    
    Node top;
    int size=0;
    
    void push(int val){
        if(size==0){
            Node node=new Node(val);
            top=node;
            size++;
            return;
        }
        Node node=new Node(val,top);
        top=node;
        size++;
        
    }
    
    void pop(){
        if(size==0){
            System.out.println("Nothing to pop");
            return;
        }
        top=top.next;
        size--;
    }
    
    int peek(){
        if(top==null){
            System.out.println("Nothing to take a peek at");
            return -1;
        }
        return top.val;
    }
    
    int size(){
        return size;
    }
    
    void printValFromTop(){
        if(size==0){
            System.out.println("The Stack is Empty");
            return;
        }
        Node curr=top;
        while(curr!=null){
            System.out.print(curr.val);
            if(curr.next!=null){
               System.out.print(" -> "); 
            }
            curr=curr.next;
        }        
    }
    
    boolean isEmpty(){
        if(size==0){
            return true;
        }else{
           return false;
        }
    }
    
    
}
