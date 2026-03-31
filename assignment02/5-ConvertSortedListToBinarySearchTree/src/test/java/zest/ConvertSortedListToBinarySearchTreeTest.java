package zest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static zest.Main.printBFSTraversal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.junit.jupiter.api.Test;

public class ConvertSortedListToBinarySearchTreeTest {
    // Task 1: Code Coverage
    @Test
    void test_sortedListToBSTReturnsNullForEmptyList() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();

        TreeNode root = solution.sortedListToBST(null); // head = []

        assertNull(root);
    }

    @Test
    void test_sortedListToBSTBuildsBalancedSearchTreeFromSortedList() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(
                -10,
                new ListNode(
                        -3,
                        new ListNode(
                                0,
                                new ListNode(5, new ListNode(9))
                        )
                )
        ); // head = [-10, -3, 0, 5, 9]

        TreeNode root = solution.sortedListToBST(head);

        assertNotNull(root);
        assertEquals(0, root.val);

        assertNotNull(root.left);
        assertEquals(-10, root.left.val);
        assertNull(root.left.left);
        assertNotNull(root.left.right);
        assertEquals(-3, root.left.right.val);
        assertNull(root.left.right.left);
        assertNull(root.left.right.right);

        assertNotNull(root.right);
        assertEquals(5, root.right.val);
        assertNull(root.right.left);
        assertNotNull(root.right.right);
        assertEquals(9, root.right.right.val);
        assertNull(root.right.right.left);
        assertNull(root.right.right.right);
    }

    @Test
    void test_ListNodeConstructors() {
        ListNode empty = new ListNode();
        ListNode tail = new ListNode(2);
        ListNode head = new ListNode(1, tail);

        assertEquals(0, empty.val);
        assertNull(empty.next);
        assertEquals(2, tail.val);
        assertNull(tail.next);
        assertEquals(1, head.val);
        assertSame(tail, head.next);
    }

    @Test
    void test_treeNodeConstructors() {
        TreeNode empty = new TreeNode();
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(8);
        TreeNode root = new TreeNode(5, left, right);

        assertEquals(0, empty.val);
        assertNull(empty.left);
        assertNull(empty.right);
        assertEquals(3, left.val);
        assertNull(left.left);
        assertNull(left.right);
        assertEquals(5, root.val);
        assertSame(left, root.left);
        assertSame(right, root.right);
    }

    @Test
    void test_printBFSTraversalPrintsEmptyTree() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(testOut);
            printBFSTraversal(null);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("[]" + System.lineSeparator(), new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
    }

    @Test
    void test_printBFSTraversalPrintsTrimmedLevelOrder() {
        TreeNode root = new ConvertSortedListToBinarySearchTree().sortedListToBST(
                new ListNode(
                        -10,
                        new ListNode(
                                -3,
                                new ListNode(
                                        0,
                                        new ListNode(5, new ListNode(9))
                                )
                        )
                )
        );
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(testOut);
            printBFSTraversal(root);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(
                "[0,-10,5,null,-3,null,9]" + System.lineSeparator(),
                new String(outputStream.toByteArray(), StandardCharsets.UTF_8)
        );
    }

    @Test
    void test_mainPrintsExampleTraversal() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PrintStream testOut = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(testOut);
            Main.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(
                "[0,-10,5,null,-3,null,9]" + System.lineSeparator(),
                new String(outputStream.toByteArray(), StandardCharsets.UTF_8)
        );
    }

    // Task 3: Testing Contracts
    @Test
    void test_contractAcceptsBoundaryValueInput() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(-100000, new ListNode(100000));

        assertDoesNotThrow(() -> solution.sortedListToBST(head));
    }

    @Test
    void test_contractRejectsUnsortedInput() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(1, new ListNode(3, new ListNode(2)));

        assertThrows(AssertionError.class, () -> solution.sortedListToBST(head));
    }

    @Test
    void test_contractRejectsValueBelowMinimum() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(-100001);

        assertThrows(AssertionError.class, () -> solution.sortedListToBST(head));
    }

    @Test
    void test_contractRejectsValueAboveMaximum() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(100001);

        assertThrows(AssertionError.class, () -> solution.sortedListToBST(head));
    }

    @Test
    void test_contractRejectsInputLongerThan20000() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(-10000);
        ListNode current = head;

        for (int value = -9999; value <= 10000; value++) {
            current.next = new ListNode(value);
            current = current.next;
        }

        assertThrows(AssertionError.class, () -> solution.sortedListToBST(head));
    }

    @Test
    void test_contractAcceptsInputLengthExactly20000() {
        ConvertSortedListToBinarySearchTree solution = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(0);
        ListNode current = head;

        for (int value = 1; value < 20000; value++) {
            current.next = new ListNode(value);
            current = current.next;
        }

        assertDoesNotThrow(() -> solution.sortedListToBST(head));
    }

    // Task 4: Property-Based Testing
    @Property
    void property_emptyInputReturnsNull(@ForAll("emptyInput") List<Integer> values) {
        TreeNode root = new ConvertSortedListToBinarySearchTree().sortedListToBST(
                values.isEmpty() ? null : new ListNode(values.get(0))
        );

        assertNull(root);
    }

    @Property
    void property_inorderTraversalEqualsInput(@ForAll("sortedInputs") List<Integer> values) {
        ListNode head = new ListNode(values.get(0));
        ListNode current = head;
        for (int i = 1; i < values.size(); i++) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }

        TreeNode root = new ConvertSortedListToBinarySearchTree().sortedListToBST(head);
        List<Integer> inorder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            TreeNode visited = stack.pop();
            inorder.add(visited.val);
            node = visited.right;
        }

        assertEquals(values, inorder);
    }

    @Property
    void property_resultIsHeightBalanced(@ForAll("sortedInputs") List<Integer> values) {
        ListNode head = new ListNode(values.get(0));
        ListNode current = head;
        for (int i = 1; i < values.size(); i++) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }

        TreeNode root = new ConvertSortedListToBinarySearchTree().sortedListToBST(head);

        assertTrue(heightIfBalanced(root) >= 0);
    }

    @Provide
    Arbitrary<List<Integer>> sortedInputs() {
        return Arbitraries.integers()
                .between(-100000, 100000)
                .list()
                .ofMinSize(1)
                .ofMaxSize(50)
                .map(values -> {
                    List<Integer> sorted = new ArrayList<>(values);
                    Collections.sort(sorted);
                    return sorted;
                });
    }

    @Provide
    Arbitrary<List<Integer>> emptyInput() {
        return Arbitraries.just(Collections.emptyList());
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
