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

    /**
     * 求二叉树的最大深度／最大高度
     * @param node
     * @return
     */
    public int getMaxDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = getMaxDepth(node.getLeft());
        int right = getMaxDepth(node.getRight());
        return Math.max(left, right) + 1;
    }

    /**
     * 求二叉树的最小深度
     * @param node
     * @return
     */
    public int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        int left = getMinDepth(node.getLeft());
        int right = getMinDepth(node.getRight());
        return Math.min(left, right) + 1;
    }


    /**
     * 求二叉树中节点的个数
     * @param node
     * @return
     */
    public int getNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getNodeNum(node.getLeft());
        int right = getNodeNum(node.getRight());
        return left + right + 1;
    }

    /**
     * 求二叉树中叶子节点的个数
     * @param node
     * @return
     */
    public int getNoChildNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return getNoChildNodeNum(node.getLeft()) + getNoChildNodeNum(node.getRight());
    }

    /**
     * 求二叉树第k层节点的个数
     * @param node
     * @param k
     * @return
     */
    public int getKLevelNodeNum(TreeNode node, int k) {
        if (node == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int left = getKLevelNodeNum(node.getLeft(), k-1);
        int right = getKLevelNodeNum(node.getRight(), k-1);
        return left + right;
    }

    /**
     * 求两个二叉树的最低公共祖先节点
     * @param root
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode getLastCommonParent (TreeNode root, TreeNode t1, TreeNode t2) {
        if (findNode(root.getLeft(), t1)) {
            if (findNode(root.getRight(), t2)) {
                return root;
            } else {
                return getLastCommonParent(root.getLeft(), t1, t2);
            }
        } else {
            if (findNode(root.getLeft(), t2)) {
                return root;
            } else {
                return getLastCommonParent(root.getRight(), t1, t2);
            }
        }
    }

    /**
     * 查找节点node是否在当前的二叉树中
     * @param root
     * @param node
     * @return
     */
    private Boolean findNode (TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        boolean found = findNode(root.getLeft(), node);
        if (!found) {
            found = findNode(root.getRight(), node);
        }
        return found;
    }


    /**
     * 判断二叉树是否是平衡二叉树
     * @param node
     * @return
     */
    public Boolean isAVLTree(TreeNode node) {
        if (node == null) {
            return Boolean.TRUE;
        }
        // 若左子树和右子树高度相差大于1，则非平衡二叉树
        if (Math.abs(getMaxDepth(node.getLeft()) - getMaxDepth(node.getRight())) > 1) {
            return Boolean.FALSE;
        }
        return isAVLTree(node.getLeft()) && isAVLTree(node.getRight());
    }

    /**
     * 判断二叉树是否是完全二叉树
     * @param node
     * @return
     */
    public Boolean isCompleteTree(TreeNode node) {
        if (node == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(node);
        Boolean result = true;
        Boolean hasChild = true;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (hasChild) {
                if (current.getLeft() != null && current.getRight() != null) {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                } else if (current.getLeft() != null && current.getRight() == null) {
                    queue.add(current.getLeft());
                    hasChild = false;
                } else if (current.getLeft() == null && current.getRight() != null) {
                    result = false;
                    break;
                } else {
                    hasChild = false;
                }
            } else {
                if (current.getLeft() != null || current.getRight() != null) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 判断二叉树是否是合法的二叉查找树(BST)
     * @param node
     * @return
     */
    public Boolean isValidBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        String treeStr = infixOrderTree(node);
        System.out.println(treeStr);
        String[] treeAry = treeStr.split(":");
        for (int i=0; i < treeAry.length-1; i++) {
            int numI = Integer.parseInt(treeAry[i]);
            int numNext = Integer.parseInt(treeAry[i+1]);
            if (numI >= numNext) {
                return false;
            }
        }
        return true;
    }

    /**
     * 采用中序遍历来遍历二叉树，节点之间用冒号（:）分隔
     * @param node
     * @return
     */
    private String infixOrderTree(TreeNode node) {
        if (node == null) {
            return "";
        }
        String left = infixOrderTree(node.getLeft());
        String data = node.getData();
        String right = infixOrderTree(node.getRight());
        String res = "";
        if (StringUtils.isNotBlank(left)) {
            res += ":" + left;
        }
        if (StringUtils.isNotBlank(data)) {
            res += ":" + data;
        }
        if (StringUtils.isNotBlank(right)) {
            res += ":"+ right;
        }
        if (res.substring(0, 1).equals(":")) {
            res = res.substring(1);
        }
        return res;
    }


    /**
     * 判断两颗二叉树是否完全相同
     * @param node1
     * @param node2
     * @return
     */
    public Boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return Boolean.TRUE;
        }else if (node1 == null || node2 == null) {
            return Boolean.FALSE;
        } else if (node1.getData() != node2.getData()) {
            return Boolean.FALSE;
        }
        Boolean left = isSameTree(node1.getLeft(), node2.getLeft());
        Boolean right = isSameTree(node1.getRight(), node2.getRight());
        return left && right;
    }

    /**
     * 判断两颗二叉树是否互为镜像
     * @param node1
     * @param node2
     * @return
     */
    public Boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return Boolean.TRUE;
        }
        if (node1 == null || node2 == null) {
            return Boolean.FALSE;
        }
        if (node1.getData() != node2.getData()) {
            return Boolean.FALSE;
        }
        return isMirror(node1.getLeft(), node2.getRight()) && isMirror(node1.getRight(), node2.getLeft());
    }

    /**
     * 求二叉树的镜像／翻转二叉树（破坏原来的树）
     * @param node
     * @return
     */
    public TreeNode mirrorTreeDestory(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTreeDestory(node.getLeft());
        TreeNode right = mirrorTreeDestory(node.getRight());
        node.setLeft(right);
        node.setRight(left);
        return node;
    }

    /**
     * 求二叉树的镜像／翻转二叉树（不破坏原来的树）
     * @param node
     * @return
     */
    public TreeNode mirrorTreeNoDestory(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode left = mirrorTreeNoDestory(node.getLeft());
        TreeNode right = mirrorTreeNoDestory(node.getRight());

        TreeNode node1 = new TreeNode(node.getData());
        node1.setLeft(right);
        node1.setRight(left);
        return node1;
    }





}
