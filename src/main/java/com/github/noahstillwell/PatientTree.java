package com.github.noahstillwell;

public class PatientTree {
    // Nested Classes
    private class TreeNode {
        public TreeNode(Patient patient) {
            this.patient = patient;
        }
        public Patient patient;
        public TreeNode left, right;
    }

    // Instance Variables
    private TreeNode root;
    
    // Constructors
    public PatientTree() {
        this.root = null;
    }

    // Methods
    public void add(Patient patient) {
        root = addNode(root, patient);
    }

    public Patient find(PatientIdentity patientIdentity) {
        
    }

    // Helper Methods
    private TreeNode addNode(TreeNode root, TreeNode newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.patient.getPatientIdentity().isLessThan(root.patient.getPatientIdentity())) {
            root.left = addNode(root.left, newNode);
        } else {
            root.right = addNode(root.right, newNode);
        }

        return root;
    }
}
