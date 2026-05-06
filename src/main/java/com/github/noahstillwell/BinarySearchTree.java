package com.github.noahstillwell;

public class BinarySearchTree {
    // Nested Classes
    private class TreeNode {
        // Instance Variables
        private IdentifiedObject identifiedObject;
        private TreeNode leftNode;
        private TreeNode rightNode;

        // Constructors
        private TreeNode(IdentifiedObject identifiedObject) {
            this.identifiedObject = identifiedObject;
            this.leftNode = null;
            this.rightNode = null;
        }
    }

    private class Stack<Type> {
        // Instance Variables
        private Type[] array;
        private int size;
        private int top;

        // Constructors
        @SuppressWarnings("unchecked")
        private Stack(int size) {
            this.array = (Type[]) new Object[size];
            this.size = size;
            this.top = 0;
        }
       
        // Methods
        private boolean push(Type item) {
            if (this.isFull()) {
                return false;
            }

            this.array[this.top] = item;
            this.top++;
            return true;
        }

        private Type pop() {
            if (this.isEmpty()) {
                return null;
            }

            Type item = this.array[this.top - 1];
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
    private static final int STACK_SIZE = 100;

    // Constructors
    public BinarySearchTree() {
        this.rootNode = null;
        this.iterationStack = new Stack<TreeNode>(STACK_SIZE);
    }

    // Methods
    public boolean add(IdentifiedObject identifiedObject) {
        if (identifiedObject == null || identifiedObject.getObjectIdentity() == null) {
            return false;
        }

        if (this.find(identifiedObject.getObjectIdentity()) != null) {
            return false;
        }

        this.rootNode = addNode(this.rootNode, new TreeNode(identifiedObject));
        return true;
    }

    public Object find(ObjectIdentity identity) {
        if (identity == null) {
            return null;
        }
        
        TreeNode node = findNode(this.rootNode, identity);

        if (node == null) {
            return null;
        }

        return node.identifiedObject;
    }

    public void initializeIteration() {
        this.iterationStack.clear();
        findLeftmostNode(this.rootNode);
    }

    public Object next() {
        TreeNode nextNode = this.iterationStack.pop();

        if (nextNode == null) {
            return null;
        }

        findLeftmostNode(nextNode.rightNode);
        return nextNode.identifiedObject;
    }

    // Helper Methods
    private TreeNode addNode(TreeNode rootNode, TreeNode newNode) {
        if (rootNode == null) {
            return newNode;
        }

        ObjectIdentity rootIdentity = rootNode.identifiedObject.getObjectIdentity();
        ObjectIdentity newIdentity = newNode.identifiedObject.getObjectIdentity();

        if (newIdentity.match(rootIdentity)) {
            return rootNode;
        }

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

        ObjectIdentity rootIdentity = rootNode.identifiedObject.getObjectIdentity();

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
            this.iterationStack.push(node);
            node = node.leftNode;
        }
    }
}
