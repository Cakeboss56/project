package com.github.noahstillwell;

public class BinarySearchTree<TreeType extends IdentifiedObject> {
    // Nested Classes
    private class TreeNode {
        // Instance Variables
        private TreeType treeObject;
        private TreeNode leftNode;
        private TreeNode rightNode;

        // Constructors
        private TreeNode(TreeType treeObject) {
            this.treeObject = treeObject;
            this.leftNode = null;
            this.rightNode = null;
        }
    }

    private class Stack<StackType> {
        // Instance Variables
        private StackType[] array;
        private int size;
        private int top;

        // Constructors
        @SuppressWarnings("unchecked")
        private Stack(int size) {
            this.array = (StackType[]) new Object[size];
            this.size = size;
            this.top = 0;
        }
       
        // Methods
        private boolean push(StackType item) {
            if (this.isFull()) {
                return false;
            }

            this.array[this.top] = item;
            this.top++;
            return true;
        }

        private StackType pop() {
            if (this.isEmpty()) {
                return null;
            }

            StackType item = this.array[this.top - 1];
            this.array[this.top - 1] = null;
            this.top--;
            return item;
        }

        private void clear() {
            while (!this.isEmpty()) {
                this.pop();
            }
        }

        // Helper Methods
        private boolean isEmpty() {
            return this.top == 0;
        }

        private boolean isFull() {
            return this.top >= this.size;
        }
    }

    // Instance Variables
    private TreeNode rootNode;
    private Stack<TreeNode> iterationStack;
    private static final int STACK_SIZE = 1000;

    // Constructors
    public BinarySearchTree() {
        this.rootNode = null;
        this.iterationStack = new Stack<TreeNode>(STACK_SIZE);
    }

    // Methods
    public boolean add(TreeType treeObject) {
        if (treeObject == null || treeObject.getObjectIdentity() == null) {
            return false;
        }

        if (this.find(treeObject.getObjectIdentity()) != null) {
            return false;
        }

        this.rootNode = addNode(this.rootNode, new TreeNode(treeObject));
        return true;
    }

    public TreeType find(ObjectIdentity identity) {
        if (identity == null) {
            return null;
        }
        
        TreeNode node = findNode(this.rootNode, identity);

        if (node == null) {
            return null;
        }

        return node.treeObject;
    }

    public void initializeIteration() {
        this.iterationStack.clear();
        findLeftmostNode(this.rootNode);
    }

    public TreeType next() {
        TreeNode nextNode = this.iterationStack.pop();

        if (nextNode == null) {
            return null;
        }

        findLeftmostNode(nextNode.rightNode);
        return nextNode.treeObject;
    }

    // Helper Methods
    private TreeNode addNode(TreeNode rootNode, TreeNode newNode) {
        if (rootNode == null) {
            return newNode;
        }

        ObjectIdentity rootIdentity = rootNode.treeObject.getObjectIdentity();
        ObjectIdentity newIdentity = newNode.treeObject.getObjectIdentity();

        if (newIdentity.isLessThan(rootIdentity)) {
            rootNode.leftNode = addNode(rootNode.leftNode, newNode);
        } else {
            rootNode.rightNode = addNode(rootNode.rightNode, newNode);
        }

        return rootNode;
    }

    private TreeNode findNode(TreeNode rootNode, ObjectIdentity identity) {
        if (rootNode == null) {
            return null;
        }

        ObjectIdentity rootIdentity = rootNode.treeObject.getObjectIdentity();

        if (identity.match(rootIdentity)) {
            return rootNode;
        }

        if (identity.isLessThan(rootIdentity)) {
            return findNode(rootNode.leftNode, identity);
        }

        return findNode(rootNode.rightNode, identity);
    }

    private void findLeftmostNode(TreeNode node) {
        while (node != null) {
            if (!this.iterationStack.push(node)) {
                return;
            }

            node = node.leftNode;
        }
    }
}
