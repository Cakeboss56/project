package com.github.noahstillwell;

public class BinarySearchTree {
    // Nested Classes
    private class Node {
        // Instance Variables
        private Object object;
        private Node leftNode;
        private Node rightNode;

        // Constructors
        private Node(Object object) {
            this.object = object;
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
    private Node rootNode;
    private Stack<Node> iterationStack;
    private static final int STACK_SIZE = 100;

    // Constructors
    public BinarySearchTree() {
        this.rootNode = null;
        this.iterationStack = new Stack<Node>(STACK_SIZE);
    }

    // Methods
    public boolean add(Object object) {
        if (object == null || object.getIdentity() == null) {
            return false;
        }

        if (this.find(object.getIdentity()) != null) {
            return false;
        }

        this.rootNode = addNode(this.rootNode, new Node(object));
        return true;
    }

    public Object find(Identity identity) {
        if (identity == null) {
            return null;
        }
        
        Node node = findNode(this.rootNode, identity);

        if (node == null) {
            return null;
        }

        return node.object;
    }

    public void initializeIteration() {
        this.iterationStack.clear();
        findLeftmostNode(this.rootNode);
    }

    public Object next() {
        Node nextNode = this.iterationStack.pop();

        if (nextNode == null) {
            return null;
        }

        findLeftmostNode(nextNode.rightNode);
        return nextNode.object;
    }

    // Helper Methods
    private Node addNode(Node rootNode, Node newNode) {
        if (rootNode == null) {
            return newNode;
        }

        Identity rootIdentity = rootNode.object.getIdentity();
        Identity newIdentity = newNode.object.getIdentity();

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

    private Node findNode(Node rootNode, Identity identity) {
        if (rootNode == null) {
            return null;
        }

        Identity rootIdentity = rootNode.object.getIdentity();

        if (identity.match(rootIdentity)) {
            return rootNode;
        }

        if (identity.isLessThan(rootIdentity)) {
            return findNode(rootNode.leftNode, identity);
        }

        return findNode(rootNode.rightNode, identity);
    }

    private void findLeftmostNode(Node node) {
        while (node != null) {
            this.iterationStack.push(node);
            node = node.leftNode;
        }
    }
}
