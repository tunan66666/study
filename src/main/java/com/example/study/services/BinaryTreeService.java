package com.example.study.services;

import com.example.study.entity.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BinaryTreeService {

    /**
     * 前序遍历（递归）：根 - 左 - 右
     * @param node
     * @return
     */
    public String preOrderRecursion(TreeNode node) {
        if (node == null) {
            return "";
        }
        String data = node.getData();
        String left = preOrderRecursion(node.getLeft());
        String right = preOrderRecursion(node.getRight());
        return data + left + right;
    }

    /**
     * 前序遍历（迭代）：根 - 左 - 右
     * @param node
     * @return
     */
    public String preOrderIteration(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            TreeNode treeNode = stack.pop();
            list.add(treeNode.getData());
            if (treeNode.getRight() != null) {
                stack.push(treeNode.getRight());
            }
            if (treeNode.getLeft() != null) {
                stack.push(treeNode.getLeft());
            }
        }
        return StringUtils.join(list.toArray());
    }

    /**
     * 中序遍历（递归）：左 - 根 - 右
      * @param node
     * @return
     */
    public String infixOrderRecursion(TreeNode node) {
        if (node == null) {
            return "";
        }
        String left = infixOrderRecursion(node.getLeft());
        String data = node.getData();
        String right = infixOrderRecursion(node.getRight());
        return left + data + right;
    }

    /**
     * 中序遍历（迭代）：左 - 根 - 右
     * @param node
     * @return
     */
    public String infixOrderIteration(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.add(current);
                current = current.getLeft();
            }
            current = stack.pop();
            list.add(current.getData());
            current = current.getRight();
        }
        return StringUtils.join(list.toArray());
    }

    /**
     * 后序遍历（递归）：左 - 右 - 根
      * @param node
     * @return
     */
    public String postOrderRecursion(TreeNode node) {
        if (node == null) {
            return "";
        }
        String left = postOrderRecursion(node.getLeft());
        String right = postOrderRecursion(node.getRight());
        String data = node.getData();
        return left + right + data;
    }

    /**
     * 后序遍历（迭代）：左 - 右 - 根
     * @param node
     * @return
     */
    public String postOrderIteration(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            TreeNode current = stack.pop();
            list.add(current.getData());
            if (current.getLeft() != null) {
                stack.push(current.getLeft());
            }
            if (current.getRight() != null) {
                stack.push(current.getRight());
            }
        }
        Collections.reverse(list);
        return StringUtils.join(list.toArray());
    }

    /**
     * 按层遍历（迭代）：第一层 - 第二层 - 第三层...
     * 按层遍历从方法上不具有递归的形式，所以一般不用递归实现
     * @param node
     * @return
     */
    public String layeringIteration(TreeNode node) {
        if (node == null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            list.add(treeNode.getData());
            if (treeNode.getLeft() != null) {
                queue.add(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.add(treeNode.getRight());
            }
        }
        return StringUtils.join(list.toArray());
    }

    /**
     * 构建二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeNode(String[] preorder, String[] inorder){
        if(preorder.length != inorder.length){
            return null;
        }
        return myBuildTree(inorder,0,inorder.length-1,preorder,0,preorder.length-1);
    }

    private TreeNode myBuildTree(String[] inorder, int instart, int inend, String[] preorder, int prestart, int preend){
        if(instart > inend){
            return null;
        }
        TreeNode root = new TreeNode(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, preorder[prestart]);
        root.setLeft(myBuildTree(inorder,instart,position-1, preorder,prestart+1,prestart+position-instart));
        root.setRight(myBuildTree(inorder,position+1,inend, preorder,position-inend+preend+1, preend));
        return root;
    }

    private int findPosition(String[] arr, int start, int end, String key){
        int i;
        for(i = start;i<=end;i++){
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }


}
