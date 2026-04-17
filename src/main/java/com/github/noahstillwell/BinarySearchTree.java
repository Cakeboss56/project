package com.github.noahstillwell;

public class BinarySearchTree {
    // Nested Classes
    private class TreeNode {
        // Instance Variables
        private Object object;
        private TreeNode leftTreeNode;
        private TreeNode rightTreeNode;

        // Constructors
        private TreeNode(Object object) {
            this.object = object;
            this.leftTreeNode = null;
            this.rightTreeNode = null;
        }
    }

    // Instance Variables
    private TreeNode rootTreeNode;

    // Constructors
    public BinarySearchTree() {
        this.rootTreeNode = null;
    }

    // Methods
    public void addObject(Object object) {
        if (object == null || object.getObjectIdentity() == null) {
            return;
        }

        this.rootTreeNode = addNode(this.rootTreeNode, new TreeNode(object));
    }

    public Object findObject(ObjectIdentity objectIdentity) {
        TreeNode node = findNode(this.rootTreeNode, objectIdentity);
        
        if (node == null) {
            return null;
        }

        return node.object;
    }

    // Helper Methods
    private TreeNode addNode(TreeNode rootTreeNode, TreeNode newTreeNode) {
        if (rootTreeNode == null) {
            return newTreeNode;
        }

        ObjectIdentity rootObjectIdentity = rootTreeNode.object.getObjectIdentity();
        ObjectIdentity newObjectIdentity = newTreeNode.object.getObjectIdentity();

        if (newObjectIdentity.match(rootObjectIdentity)) {
            return rootTreeNode;
        }

        if (newObjectIdentity.isLessThan(rootObjectIdentity)) {
            rootTreeNode.leftTreeNode = addNode(rootTreeNode.leftTreeNode, newTreeNode);
        } else {
            rootTreeNode.rightTreeNode = addNode(rootTreeNode.rightTreeNode, newTreeNode);
        }

        return rootTreeNode;
    }

    private TreeNode findNode(TreeNode rootTreeNode, ObjectIdentity objectIdentity) {
        if (rootTreeNode == null) {
            return null;
        }

        ObjectIdentity rootObjectIdentity = rootTreeNode.object.getObjectIdentity();

        if (objectIdentity.match(rootObjectIdentity)) {
            return rootTreeNode;
        }

        if (objectIdentity.isLessThan(rootObjectIdentity)) {
            return findNode(rootTreeNode.leftTreeNode, objectIdentity);
        }

        return findNode(rootTreeNode.rightTreeNode, objectIdentity);
    }
}
