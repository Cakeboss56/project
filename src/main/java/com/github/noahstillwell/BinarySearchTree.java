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
    public void add(Object object) {
        if (object == null || object.getObjectIdentity() == null) {
            return;
        }

        this.rootTreeNode = addTreeNode(this.rootTreeNode, new TreeNode(object));
    }

    public Object find(ObjectIdentity objectIdentity) {
        TreeNode treeNode = findTreeNode(this.rootTreeNode, objectIdentity);
        
        if (treeNode == null) {
            return null;
        }

        return treeNode.object;
    }

    // Helper Methods
    private TreeNode addTreeNode(TreeNode rootTreeNode, TreeNode newTreeNode) {
        if (rootTreeNode == null) {
            return newTreeNode;
        }

        ObjectIdentity rootObjectIdentity = rootTreeNode.object.getObjectIdentity();
        ObjectIdentity newObjectIdentity = newTreeNode.object.getObjectIdentity();

        if (newObjectIdentity.match(rootObjectIdentity)) {
            return rootTreeNode;
        }

        if (newObjectIdentity.isLessThan(rootObjectIdentity)) {
            rootTreeNode.leftTreeNode = addTreeNode(rootTreeNode.leftTreeNode, newTreeNode);
        } else {
            rootTreeNode.rightTreeNode = addTreeNode(rootTreeNode.rightTreeNode, newTreeNode);
        }

        return rootTreeNode;
    }

    private TreeNode findTreeNode(TreeNode rootTreeNode, ObjectIdentity objectIdentity) {
        if (rootTreeNode == null) {
            return null;
        }

        ObjectIdentity rootObjectIdentity = rootTreeNode.object.getObjectIdentity();

        if (objectIdentity.match(rootObjectIdentity)) {
            return rootTreeNode;
        }

        if (objectIdentity.isLessThan(rootObjectIdentity)) {
            return findTreeNode(rootTreeNode.leftTreeNode, objectIdentity);
        }

        return findTreeNode(rootTreeNode.rightTreeNode, objectIdentity);
    }
}
