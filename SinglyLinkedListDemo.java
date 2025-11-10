/*
 * SinglyLinkedListDemo.java
 * A complete Java implementation of a singly linked list with 
 * insertion, deletion, and traversal operations.
 *
 * Author: Khushman
 * Date: November 2025
 */

class SinglyLinkedListDemo
{
	public static void main (String[] args) throws java.lang.Exception
	{
		SinglyLinkedList list = new SinglyLinkedList();

    System.out.println("Printing empty list:");
    list.printList();

    System.out.println("Adding elements at the beginning:");
    list.addFirst(5);
    list.addFirst(3);
    list.addFirst(1);
    list.printList(); // Expected: 1 -> 3 -> 5

    System.out.println("Adding elements at the end:");
    list.addLast(7);
    list.addLast(9);
    list.printList(); // Expected: 1 -> 3 -> 5 -> 7 -> 9

    System.out.println("Inserting after specific value:");
    list.insertAfter(5, 6);
    list.insertAfter(9, 10);
    list.insertAfter(100, 11); // Should print "Target Not Found"
    list.printList(); // Expected: 1 -> 3 -> 5 -> 6 -> 7 -> 9 -> 10

    System.out.println("Removing first element:");
    list.removeFirst();
    list.printList(); // Expected: 3 -> 5 -> 6 -> 7 -> 9 -> 10

    System.out.println("Removing last element:");
    list.removeLast();
    list.printList(); // Expected: 3 -> 5 -> 6 -> 7 -> 9

    System.out.println("Removing specific element:");
    list.remove(6);
    list.printList(); // Expected: 3 -> 5 -> 7 -> 9

    System.out.println("Trying to remove a value not in list:");
    list.remove(999); // Should print "Target Not Found"

    System.out.println("Removing all to test empty behavior:");
    list.remove(3);
    list.remove(5);
    list.remove(7);
    list.remove(9);
    list.printList(); // Expected: "List is empty"

    System.out.println("Removing from empty list (should not crash):");
    list.removeFirst();
    list.removeLast();
    list.remove(10);
    list.printList(); // Expected: "List is empty"

	}
}



 class SinglyLinkedList{
    private class Node{
        int val;
        Node next;
        
        public Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
        
        public Node(int val){
            this.val=val;
            this.next=null;
        }
    }
    Node head;
    
    void addFirst(int val){
        if(head==null){
            Node node=new Node(val);
            head=node;
        }
        else{
             Node node=new Node(val,head);
             head=node;
        }
       
    }
    void addLast(int val){
        if(head==null){
            Node node=new Node(val);
            head=node;
        }
        else{
            Node curr=head;
            while(curr.next!=null){
                curr=curr.next;
            }
             Node node=new Node(val);
             curr.next=node;
             
        }
    }
    void insertAfter(int target, int val){
        if(head==null){
            Node node=new Node(val);
            head=node;
        }else{
            Node curr=head;
            while(curr!=null){
                if(curr.val==target){
                    Node node=new Node(val,curr.next);
                    curr.next=node;
                   return;
                }
                curr=curr.next;
            }
            
            System.out.println("Target Not Found");
            
        }
    }
    
    void removeFirst(){
        if(head!=null){
            head=head.next;
        }
    }
    
    void removeLast(){
        if(head==null) return;
        if(head.next==null){
            head=null;
            return;
        }
        
        Node curr=head;
        while(curr.next.next!=null){
            curr=curr.next;
        }
        curr.next=null;
    
    }
    
    void remove(int val){
        if (head == null) return;
    
        if (head.val == val) {
            removeFirst();
            return;
        }
    
        Node curr = head;
    
        while (curr.next != null && curr.next.val != val) {
            curr = curr.next;
        }
    
        if (curr.next == null) {
            System.out.println("Target Not Found");
            return;
        }
    
        curr.next = curr.next.next;
    }
    
    void printList(){
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }
}
