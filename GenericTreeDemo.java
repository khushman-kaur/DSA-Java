import java.util.*;
import java.lang.*;
import java.io.*;

class GenericTreeDemo
{
	public static void main (String[] args)
	{
    	GenericTree tree = new GenericTree();
    
        // basic tests
        tree.addNode(1);   // creates root
        tree.addNode(2);   // adds child
        tree.addNode(3);   // adds another child
        tree.addNode(4);   // adds another child
    
        System.out.println("\nTree structure:");
        tree.printTree(tree.root);  // should print 1, 2, 3, 4
    
        System.out.println("\nFind node results:");
        System.out.println(tree.findNode(1, tree.root)); // true
        System.out.println(tree.findNode(3, tree.root)); // true
        System.out.println(tree.findNode(5, tree.root)); // false
        System.out.println(tree.findNode(4, tree.root)); // true
        System.out.println(tree.findNode(10, tree.root)); // false
    }
}

class GenericTree{
    public class TreeNode{
        int val;
        List<TreeNode> children;
        
        public TreeNode(int val){
            this.val=val;
            this.children=new ArrayList<>();
        }
        
    }
    
    TreeNode root;
    
    void addNode(int val){
        if(root==null){
            System.out.println("No root present, creating a new root");
            TreeNode node= new TreeNode(val);
            root = node;
            return;
        }
        TreeNode node=new TreeNode(val);
        root.children.add(node);

    }
    
    boolean findNode(int val,TreeNode node){
        if(node==null) return false;
        if(node.val==val){
            return true;
        }
        for(TreeNode nod:node.children){
            if (findNode(val, nod)) 
            return true;            
        }
        return false;
    }
    
    void printTree(TreeNode node){
        if(node==null){
            //System.out.println("No Tree present");
            return;
        }
        System.out.println(node.val);
        for(TreeNode nod:node.children){
            printTree(nod);
        }
    }
    
    void addNodeUnderParent(int parentVal, int childVal){
        if(root==null){
            System.out.println("Tree not present");
            return;
        }
        
        boolean added=addChildToParent(root,parentVal,childVal);
        if(!added) System.out.println("parent is not present to add the child");
        
        
    }
    
    boolean addChildToParent(TreeNode node,int parentVal, int childVal){
        if(node==null) return false;
        
        if(node.val==parentVal){
            TreeNode newNode =new TreeNode(childVal);
            node.children.add(newNode);
            System.out.println("Value added");
            return true;
        }
        
        for(TreeNode curr:node.children){
            if (addChildToParent(curr,parentVal,childVal))
                return true;
        }
    
        return false;
    }
    
    void removeNode(int val){
        if(root==null){
            System.out.println("Empty tree");
            return;
        }
        if (root.val == val) {
            root = null;
            System.out.println("Removed root, tree is now empty");
            return;
        }
        boolean removed=removeNodeHelper(root,val);
        if(!removed) System.out.println("nothing found to remove");
        
    }
    
    boolean removeNodeHelper(TreeNode node,int val){
        Iterator <TreeNode> itr=node.children.iterator();
        while(itr.hasNext()){
            TreeNode child=itr.next();
            if(child.val==val){
                itr.remove();
                return true;
            }
            if (removeNodeHelper(child, val)) 
            return true;
        }
        return false;
    }
    
    int countNodes(TreeNode node){
        if(node==null) return 0;
        int count=1;
        
        for(TreeNode child:node.children){
            count+=countNodes(child);
        }
        
        return count;
        
    }
    void totalNodes(){
        int count=countNodes(root);
        System.out.println(count);
    }
}
