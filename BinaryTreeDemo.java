import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main(String[] args)
    {
        BinaryTree bt = new BinaryTree();

        System.out.println("---- Creating Root ----");
        bt.createRoot(5);
        bt.createRoot(10);

        System.out.println("\n---- Adding Left & Right ----");
        bt.addLeftNode(5, 2);
        bt.addRightNode(5, 8);

        bt.addLeftNode(2, 1);
        bt.addRightNode(2, 3);

        bt.addLeftNode(8, 7);
        bt.addRightNode(8, 9);

        bt.addLeftNode(1, 100);
        bt.addLeftNode(50, 4);

        System.out.println("\n---- Printing Tree ----");
        bt.printTree();

        System.out.println("\n---- Searching Nodes ----");
        bt.findNode(3);
        bt.findNode(50);

        System.out.println("\n---- Height ----");
        bt.height();

        System.out.println("\n---- Total Nodes ----");
        bt.totalNodes();

        System.out.println("\n---- Leaf Nodes ----");
        bt.leafNodes();

        System.out.println("\n---- Sum of All Nodes ----");
        System.out.println("Sum: " + bt.sumOfNodes(bt.root));

        System.out.println("\n---- Adding Existing Child ----");
        bt.addLeftNode(2, 99);

        System.out.println("\n---- Testing on Empty Tree ----");
        BinaryTree empty = new BinaryTree();
        empty.printTree();
        empty.height();
        empty.totalNodes();
        empty.leafNodes();
        empty.findNode(5);

        System.out.println("\n---- Stress Testing Search ----");
        bt.findNode(7);
        bt.findNode(9);
        bt.findNode(999);

        System.out.println("\n---- Stress Testing Heights on Subtrees ----");
        System.out.println("Height of left subtree: " + bt.testHeight(bt.root.left));
        System.out.println("Height of right subtree: " + bt.testHeight(bt.root.right));

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left) {
        this.val = val;
        this.left = left;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

class BinaryTree {
    TreeNode root;

    void createRoot(int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            root = node;
            System.out.println("Root Created");
            return;
        }
        System.out.println("Root already present");
    }

    void findNode(int val) {
        TreeNode found = findNodeHelper(root, val);
        if (found == null) {
            System.out.println("Node not found");
        } else {
            System.out.println("Node found with value: " + found.val);
        }

    }

    private TreeNode findNodeHelper(TreeNode node, int val) {
        if (node == null) return null;
        if (node.val == val) return node;

        TreeNode left = findNodeHelper(node.left, val);
        if (left != null) return left;

        return findNodeHelper(node.right, val);


    }

    void addLeftNode(int parentVal, int val) {
        if (root == null) {
            System.out.println("No Tree Present");
            return;
        }
        TreeNode parent = findNodeHelper(root, parentVal);
        if (parent == null) {
            System.out.println("Parent not found");
            return;
        }
        if (parent.left != null) {
            System.out.println("Left root already exists");
            return;
        }
        TreeNode l = new TreeNode(val);
        parent.left = l;
    }

    void addRightNode(int parentVal, int val) {
        if (root == null) {
            System.out.println("No Tree Present");
            return;
        }
        TreeNode parent = findNodeHelper(root, parentVal);
        if (parent == null) {
            System.out.println("Parent not found");
            return;
        }
        if (parent.right != null) {
            System.out.println("Right root already exists");
            return;
        }
        TreeNode r = new TreeNode(val);
        parent.right = r;
    }
    void printTree() {
        if (root == null) {
            System.out.println("No Tree Present");
            return;
        }
        //printTreeHelper(root);
        printLevelOrderTree(root);
        
        System.out.println("");
    }

    void printTreeHelper(TreeNode node) {
        if (node == null) {
            return;
        }
        //System.out.println(node.val);//PreOrder
        printTreeHelper(node.left);
        //System.out.println(node.val);//InOrder
        printTreeHelper(node.right);
        //System.out.println(node.val);//PostOrder
    }
    
    void printLevelOrderTree(TreeNode node){
        
        if(node==null){
            System.out.println("Nothing to print");
            return;
        }
        
        Queue<TreeNode> list=new LinkedList<>();
        list.offer(node);
        
        
        while(!list.isEmpty()){
            TreeNode curr=list.poll();
            if(curr.left!=null){
                list.offer(curr.left);
            }
            if(curr.right!=null){
                list.offer(curr.right);
            }
            System.out.print(curr.val+" ");
        }
    }
    
    void height(){
        if(root==null){
            System.out.println("No tree present");
            return;
        }
        int height=heightHelper(root);
        System.out.println(height);
    }
    
    private int heightHelper(TreeNode node){
        if(node==null){
            return 0;
        }
        
        //int maxHeight=0;
        int left=heightHelper(node.left);
        //maxHeight=Math.max(left,maxHeight);
        int right=heightHelper(node.right);
        //maxHeight=Math.max(right,maxHeight);
        
        return 1+Math.max(left,right);
    }
    
    void totalNodes(){
        if(root==null){
            System.out.println("No tree present");
            return;
        }
        int nodes=countNodes(root);
        System.out.println("Total Nodes "+nodes);
    }
    
    private int countNodes(TreeNode node){
        if(node==null){
            return 0;
        }
        
        return countNodes(node.left)+countNodes(node.right)+1;
    }

    void leafNodes(){
        if(root==null){
            System.out.println("No tree present");
            return;
        }
        int leaves=leafNodes(root);
        System.out.println("Total Leaf Nodes "+leaves);
    }

    private int leafNodes(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            return 1;
        }
    
        return leafNodes(node.left)+leafNodes(node.right);
    }

    int sumOfNodes(TreeNode node){
        if(node==null)
        return 0;
        
        int left=sumOfNodes(node.left);
        int right=sumOfNodes(node.right);
        
        return left+right+node.val;
    }

    int sumOfNodes(TreeNode node){
        if(node==null)
        return 0;
        
        int left=sumOfNodes(node.left);
        int right=sumOfNodes(node.right);
        
        return left+right+node.val;
    }
}