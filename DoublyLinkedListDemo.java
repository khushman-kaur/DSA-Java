import java.util.*;
import java.lang.*;
import java.io.*;

class DoublyLinkedListDemo
{
	public static void main (String[] args)
	{
        DoublyLinkedList dll = new DoublyLinkedList();

        // Test 1: addFirst
        System.out.println("=== Test 1: addFirst ===");
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);
        dll.printFromFront(); // Expected: 30 -> 20 -> 10
        dll.printFromLast();  // Expected: 10 -> 20 -> 30

        // Test 2: addLast
        System.out.println("\n=== Test 2: addLast ===");
        dll.addLast(40);
        dll.addLast(50);
        dll.printFromFront(); // Expected: 30 -> 20 -> 10 -> 40 -> 50
        dll.printFromLast();  // Expected: 50 -> 40 -> 10 -> 20 -> 30

        // Test 3: insertAfter
        System.out.println("\n=== Test 3: insertAfter ===");
        dll.insertAfter(10, 15);
        dll.insertAfter(50, 60);
        dll.insertAfter(999, 70); // Should print: does not exist
        dll.printFromFront(); // Expected: 30 -> 20 -> 10 -> 15 -> 40 -> 50 -> 60
        dll.printFromLast();  // Expected: 60 -> 50 -> 40 -> 15 -> 10 -> 20 -> 30

        // Test 4: insertBefore
        System.out.println("\n=== Test 4: insertBefore ===");
        dll.insertBefore(30, 25); // Before head (acts like addFirst)
        dll.insertBefore(10, 9);
        dll.insertBefore(999, 70); // Should print: does not exist
        dll.printFromFront(); // Expected: 25 -> 30 -> 20 -> 9 -> 10 -> 15 -> 40 -> 50 -> 60
        dll.printFromLast();  // Expected: 60 -> 50 -> 40 -> 15 -> 10 -> 9 -> 20 -> 30 -> 25
        
        // Test 5: removeFirst
        System.out.println("\n=== Test 5: removeFirst ===");
        dll.removeFirst(); // Remove 25
        dll.printFromFront(); // Expected: 30 -> 20 -> 9 -> 10 -> 15 -> 40 -> 50 -> 60
        dll.removeFirst();
        dll.removeFirst();
        dll.printFromFront(); // Expected: 9 -> 10 -> 15 -> 40 -> 50 -> 60

        // Test 6: removeLast
        System.out.println("\n=== Test 6: removeLast ===");
        dll.removeLast(); // Remove 60
        dll.printFromFront(); // Expected: 9 -> 10 -> 15 -> 40 -> 50
        dll.removeLast();
        dll.printFromFront(); // Expected: 9 -> 10 -> 15 -> 40
        dll.printFromLast();  // Expected: 40 -> 15 -> 10 -> 9

        // Test 7: remove from single node list
        System.out.println("\n=== Test 7: single node removal ===");
        DoublyLinkedList single = new DoublyLinkedList();
        single.addFirst(100);
        single.printFromFront(); // Expected: 100
        single.removeFirst(); // should clear list
        single.printFromFront(); // Expected: Empty Linked List
        single.removeLast(); // should print empty message
        
        // Test 8: remove specific value
        System.out.println("\n=== Test 8: remove(int val) ===");
        DoublyLinkedList list = new DoublyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        
        list.printFromFront(); // Expected: 10 -> 20 -> 30 -> 40 -> 50
        
        list.remove(10); // Remove head
        list.printFromFront(); // Expected: 20 -> 30 -> 40 -> 50
        
        list.remove(50); // Remove tail
        list.printFromFront(); // Expected: 20 -> 30 -> 40
        
        list.remove(30); // Remove middle
        list.printFromFront(); // Expected: 20 -> 40
        
        list.remove(999); // Not in list
        // Expected: "This val does not exist"
        
        list.remove(20);
        list.remove(40);
        list.remove(70); // Empty case
        // Expected: "Empty list nothing to remove"
	}
}

class DoublyLinkedList{
    private class Node{
        int val;
        Node prev;
        Node next;
        
        public Node(int val){
            this.val=val;
        }
        
        public Node(int val,Node next, Node prev){
            this.val=val;
            this.next=next;
            this.prev=prev;
        }
    }
    
    Node head;
    Node tail;
    
    void addFirst(int val){
        if(head==null && tail==null){
            Node node = new Node(val);
            head=node;
            tail=node;
            return;
        }
        Node node=new Node(val,head,null);
        head.prev=node;
        head=node;
    }
    
    void addLast(int val){
        if(head==null && tail==null){
            Node node = new Node(val);
            head=node;
            tail=node;
            return;
        }
        Node node=new Node(val,null,tail);
        tail.next=node;
        tail=node;
        
    }
    
    void insertAfter(int val ,int dataToInsert){
        if(head==null){
            System.out.println(val+" does not exist in this list");
            return;
        }
        Node curr =head;
        while(curr!=null && curr.val!=val){
            curr=curr.next;
        }
        if(curr==null){
                System.out.println(val+" does not exist in this list");
                return;
            }
        if(curr==tail){
            addLast(dataToInsert);
            return;
        }
        Node node=new Node(dataToInsert,curr.next,curr);
        curr.next.prev=node;
        curr.next=node;
    }
    
    void insertBefore(int val ,int dataToInsert){
        if(head==null){
            System.out.println(val+" does not exist in this list");
            return;
        }
        Node curr =head;
        while(curr!=null && curr.val!=val){
            curr=curr.next;
        }
        if(curr==null){
                System.out.println(val+" does not exist in this list");
                return;
            }
        if(curr==head){
            addFirst(dataToInsert);
            return;
        }
        Node node=new Node(dataToInsert,curr,curr.prev);
        curr.prev.next=node;
        curr.prev=node;
    }
    
    void removeFirst(){
        if(head==null){
            System.out.println("Empty list nothing to remove");
            return;
        }
        if (head == tail) {
            head = tail = null;
            return;
        }
        head=head.next;
        head.prev=null;
    }
    
    void removeLast(){
        if(tail==null){
            System.out.println("Empty list nothing to remove");
            return;
        }
        if (head == tail) { 
            head = tail = null;
            return;
        }
        tail=tail.prev;
        tail.next=null;
    }
    
    void remove(int val){
        if(head==null){
            System.out.println("Empty list nothing to remove");
            return;
        }
        Node curr=head;
        while(curr!=null && curr.val!=val){
           curr=curr.next;
        }
        if(curr==null){
            System.out.println("This val does not exist");
            return;
        }
        if(curr==head){
            removeFirst();
            return;
        }
        if(curr==tail){
            removeLast();
            return;
        }
        curr.prev.next=curr.next;
        curr.next.prev=curr.prev;
    }
    void printFromFront(){
        if(head==null){
            System.out.println("Empty Linked List");
            return;
        }
        System.out.println("from head");
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
        
    }
    
    void printFromLast(){
        if(tail==null){
            System.out.println("Empty Linked List");
            return;
        }
        System.out.println("from tail");
        Node curr=tail;
        while(curr!=null){
            System.out.print(curr.val);
            if (curr.prev != null) System.out.print(" -> ");
            curr = curr.prev;
        }
        System.out.println();
    }
    
}