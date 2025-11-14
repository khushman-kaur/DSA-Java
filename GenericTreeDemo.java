import java.util.*;
import java.lang.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        GenericTree tree = new GenericTree();

        // ------------------ BASIC TREE SETUP ------------------
        System.out.println("\n--- Creating Tree ---");
        tree.addNode(1);
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(4);

        // ------------------ PRINT TREE ------------------
        System.out.println("\nTree structure (root with 3 children):");
        tree.printTree(tree.root);

        // ------------------ FIND NODE TESTS ------------------
        System.out.println("\n--- Find Node Tests ---");
        System.out.println(tree.findNode(1, tree.root)); // true
        System.out.println(tree.findNode(3, tree.root)); // true
        System.out.println(tree.findNode(5, tree.root)); // false
        System.out.println(tree.findNode(4, tree.root)); // true
        System.out.println(tree.findNode(10, tree.root)); // false

        // ------------------ ADD UNDER PARENT ------------------
        System.out.println("\n--- Adding Under Specific Parent ---");
        tree.addNodeUnderParent(2, 20); // add child under node 2
        tree.addNodeUnderParent(2, 21);
        tree.addNodeUnderParent(3, 30);
        tree.addNodeUnderParent(10, 999); // invalid parent

        System.out.println("\nTree after adding children under 2 and 3:");
        tree.printTree(tree.root);

        // ------------------ COUNT NODES ------------------
        System.out.println("\n--- Total Nodes in Tree ---");
        tree.totalNodes(); // should print total number including new nodes

        // ------------------ HEIGHT OF TREE ------------------
        System.out.println("\n--- Height of Tree ---");
        System.out.println(tree.height(tree.root)); // depends on added structure

        // ------------------ REMOVE NODE ------------------
        System.out.println("\n--- Remove Node Tests ---");
        tree.removeNode(21); // removing a leaf
        tree.removeNode(3); // removing parent with child (30)
        tree.removeNode(100); // not present
        tree.removeNode(1); // removing root

        System.out.println("\nTree After Removals:");
        tree.printTree(tree.root);

        System.out.println("\nHeight After Removals:");
        System.out.println(tree.height(tree.root));

        System.out.println("\nTotal Nodes After Removals:");
        tree.totalNodes();
    }
}

class GenericTree {
    public class TreeNode {
        int val;
        List < TreeNode > children;

        public TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList < > ();
        }
    }

    TreeNode root;

    void addNode(int val) {
        if (root == null) {
            System.out.println("No root present, creating a new root");
            TreeNode node = new TreeNode(val);
            root = node;
            return;
        }
        TreeNode node = new TreeNode(val);
        root.children.add(node);

    }

    boolean findNode(int val, TreeNode node) {
        if (node == null) return false;
        if (node.val == val) {
            return true;
        }
        for (TreeNode nod: node.children) {
            if (findNode(val, nod))
                return true;
        }
        return false;
    }

    void printTree(TreeNode node) {
        if (node == null) {
            //System.out.println("No Tree present");
            return;
        }
        System.out.println(node.val);
        for (TreeNode nod: node.children) {
            printTree(nod);
        }
    }

    void addNodeUnderParent(int parentVal, int childVal) {
        if (root == null) {
            System.out.println("Tree not present");
            return;
        }

        boolean added = addChildToParent(root, parentVal, childVal);
        if (!added) System.out.println("parent is not present to add the child");


    }

    boolean addChildToParent(TreeNode node, int parentVal, int childVal) {
        if (node == null) return false;

        if (node.val == parentVal) {
            TreeNode newNode = new TreeNode(childVal);
            node.children.add(newNode);
            System.out.println("Value added");
            return true;
        }
        for (TreeNode curr: node.children) {
            if (addChildToParent(curr, parentVal, childVal))
                return true;
        }
        return false;
    }

    void removeNode(int val) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        if (root.val == val) {
            root = null;
            System.out.println("Removed root, tree is now empty");
            return;
        }
        boolean removed = removeNodeHelper(root, val);
        if (!removed) System.out.println("nothing found to remove");

    }

    boolean removeNodeHelper(TreeNode node, int val) {
        Iterator < TreeNode > itr = node.children.iterator();
        while (itr.hasNext()) {
            TreeNode child = itr.next();
            if (child.val == val) {
                itr.remove();
                return true;
            }
            if (removeNodeHelper(child, val))
                return true;
        }
        return false;
    }

    int countNodes(TreeNode node) {
        if (node == null) return 0;
        int count = 1;

        for (TreeNode child: node.children) {
            count += countNodes(child);
        }

        return count;

    }

    void totalNodes() {
        int count = countNodes(root);
        System.out.println(count);
    }

    int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxheight = 0;

        for (TreeNode child: node.children) {
            int h = height(child);
            maxheight = Math.max(h, maxheight);
        }

        return maxheight + 1;
    }

}