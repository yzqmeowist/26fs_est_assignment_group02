package zest;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = validateAndCopyInput(head);
        TreeNode root = sortedArrayToBST(list, 0, list.size() - 1);
        validateOutput(list, root);
        return root;
    }

    private TreeNode sortedArrayToBST(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = sortedArrayToBST(list, start, mid - 1);
        root.right = sortedArrayToBST(list, mid + 1, end);
        return root;
    }

    private List<Integer> validateAndCopyInput(ListNode head) {
        List<Integer> list = new ArrayList<>();
        Integer previousValue = null;

        while (head != null) {
            assert head.val >= -100000 && head.val <= 100000 : "Node values must be in [-100000, 100000].";
            assert previousValue == null || head.val >= previousValue : "Input list must be sorted in ascending order.";

            list.add(head.val);

            assert list.size() <= 20000 : "Input list length must be in [0, 20000].";
            previousValue = head.val;
            head = head.next;
        }

        return list;
    }

    private void validateOutput(List<Integer> inputValues, TreeNode root) {
        if (inputValues.isEmpty()) {
            assert root == null : "Result must be null when the input list is empty.";
            return;
        }

        assert root != null : "Result must not be null when the input list is not empty.";
        assert isBinarySearchTree(root) : "Result must be a binary search tree.";
        assert inorderTraversal(root).equals(inputValues) : "The returned tree must be built from the input list values.";
        assert isHeightBalanced(root) : "Result must be height-balanced.";
    }

    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode node = stack.pop();
            values.add(node.val);
            current = node.right;
        }

        return values;
    }

    private boolean isBinarySearchTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        Integer previousValue = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode node = stack.pop();
            if (previousValue != null && node.val < previousValue) {
                return false;
            }
            previousValue = node.val;
            current = node.right;
        }

        return true;
    }

    private boolean isHeightBalanced(TreeNode root) {
        return heightIfBalanced(root) >= 0;
    }

    private int heightIfBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = heightIfBalanced(root.left);
        if (leftHeight < 0) {
            return -1;
        }

        int rightHeight = heightIfBalanced(root.right);
        if (rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
