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

    // Instance Variables
    private Node rootNode;

    // Constructors
    public BinarySearchTree() {
        this.rootNode = null;
    }

    // Methods
    public void addObject(Object object) {
        if (object == null || object.getIdentity() == null) {
            return;
        }

        this.rootNode = addNode(this.rootNode, new Node(object));
    }

    public Object findObject(Identity identity) {
        Node node = findNode(this.rootNode, identity);

        if (node == null) {
            return null;
        }

        return node.object;
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
}
