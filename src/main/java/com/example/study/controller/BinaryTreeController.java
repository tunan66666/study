package com.example.study.controller;

import com.example.study.entity.TreeNode;
import com.example.study.services.BinaryTreeService;
import com.example.study.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/binary_tree")
public class BinaryTreeController {

    @Autowired
    private BinaryTreeService binaryTreeService;

    @GetMapping("/order")
    public Object order() {
        // 构造二叉树
        TreeNode treeNode1 = this.constructTreeNode1();
        TreeNode treeNode2 = this.constructTreeNode2();
        TreeNode treeNode3 = this.constructTreeNode3();

        // 排序
        String preStrRecursion = binaryTreeService.preOrderRecursion(treeNode1);
        String preStrIteration = binaryTreeService.preOrderIteration(treeNode1);
        String infixStrRecursion = binaryTreeService.infixOrderRecursion(treeNode1);
        String infixStrIteration = binaryTreeService.infixOrderIteration(treeNode1);
        String postStrRecursion = binaryTreeService.postOrderRecursion(treeNode1);
        String postStrIteration = binaryTreeService.postOrderIteration(treeNode1);
        String layeringStrIteration = binaryTreeService.layeringIteration(treeNode1);

        // 根据前序遍历和中序遍历构造二叉树
        String preorder[] = new String[] {"A", "B", "D", "H", "E", "C", "F", "G"};
        String inorder[] = new String[] {"D", "H", "B", "E", "A", "F", "C", "G"};
        TreeNode orderNode = binaryTreeService.buildTreeNode(preorder, inorder);
        String buildTreeNodePreStr = binaryTreeService.preOrderRecursion(orderNode);
        String buildTreeNodeinfixStr = binaryTreeService.infixOrderRecursion(orderNode);

        // 深度
        int maxDepth = binaryTreeService.getMaxDepth(treeNode1);
        int minDepth = binaryTreeService.getMinDepth(treeNode1);

        // 节点
        int nodeNum = binaryTreeService.getNodeNum(treeNode1);
        int noChildNodeNum = binaryTreeService.getNoChildNodeNum(treeNode1);
        int kLevelNodeNum = binaryTreeService.getKLevelNodeNum(treeNode1, 3);

        // 二叉树
        Boolean isAVLTree = binaryTreeService.isAVLTree(treeNode1);
        Boolean isSameTree = binaryTreeService.isSameTree(treeNode1, treeNode2);
        Boolean isMirror12 = binaryTreeService.isMirror(treeNode1, treeNode2);
        Boolean isMirror13 = binaryTreeService.isMirror(treeNode1, treeNode3);

        TreeNode mirrorTree1 = binaryTreeService.mirrorTreeDestory(treeNode1);
        String mirrorTreePreStr1 = binaryTreeService.preOrderRecursion(treeNode1);
        String mirrorTreePreStr2 = binaryTreeService.preOrderRecursion(mirrorTree1);

        TreeNode mirrorTree2 = binaryTreeService.mirrorTreeNoDestory(treeNode2);
        String mirrorTreePreStr3 = binaryTreeService.preOrderRecursion(treeNode2);
        String mirrorTreePreStr4 = binaryTreeService.preOrderRecursion(mirrorTree2);

        Map<String, Object> mp = new HashMap<>();
        mp.put("preStrRecursion", preStrRecursion);
        mp.put("preStrIteration", preStrIteration);
        mp.put("infixStrRecursion", infixStrRecursion);
        mp.put("infixStrIteration", infixStrIteration);
        mp.put("postStrRecursion", postStrRecursion);
        mp.put("postStrIteration", postStrIteration);
        mp.put("layeringStrIteration", layeringStrIteration);

        mp.put("buildTreeNodePreStr", buildTreeNodePreStr);
        mp.put("buildTreeNodeinfixStr", buildTreeNodeinfixStr);

        mp.put("maxDepth", maxDepth);
        mp.put("minDepth", minDepth);

        mp.put("nodeNum", nodeNum);
        mp.put("noChildNodeNum", noChildNodeNum);
        mp.put("kLevelNodeNum", kLevelNodeNum);

        mp.put("isAVLTree", isAVLTree);
        mp.put("isSameTree", isSameTree);
        mp.put("isMirror12", isMirror12);
        mp.put("isMirror13", isMirror13);
        mp.put("mirrorTreePreStr1", mirrorTreePreStr1);
        mp.put("mirrorTreePreStr2", mirrorTreePreStr2);
        mp.put("mirrorTreePreStr3", mirrorTreePreStr3);
        mp.put("mirrorTreePreStr4", mirrorTreePreStr4);

        return new ResponseMessage<>(mp);
    }

    /**
     * 构造第一颗二叉树
     * @return
     */
    private TreeNode constructTreeNode1() {
        // 构造二叉树，层1
        TreeNode treeNode = new TreeNode("A");

        // 层2
        TreeNode left = new TreeNode("B");
        TreeNode right = new TreeNode("C");
        treeNode.setLeft(left);
        treeNode.setRight(right);

        // 层3
        left = new TreeNode("D");
        right = new TreeNode("E");
        treeNode.getLeft().setLeft(left);
        treeNode.getLeft().setRight(right);
        left = new TreeNode("F");
        right = new TreeNode("G");
        treeNode.getRight().setLeft(left);
        treeNode.getRight().setRight(right);

        /**
        // 层4
        right = new TreeNode("H");
        treeNode.getLeft().getLeft().setRight(right);
         **/

        return treeNode;
    }

    /**
     * 构造第二颗二叉树，可根据实际情况修改二叉树
     * @return
     */
    private TreeNode constructTreeNode2() {
        // 构造二叉树，层1
        TreeNode treeNode = new TreeNode("A");

        // 层2
        TreeNode left = new TreeNode("B");
        TreeNode right = new TreeNode("C");
        treeNode.setLeft(left);
        treeNode.setRight(right);

        // 层3
        left = new TreeNode("D");
        right = new TreeNode("E");
        treeNode.getLeft().setLeft(left);
        treeNode.getLeft().setRight(right);
        left = new TreeNode("F");
        right = new TreeNode("G");
        treeNode.getRight().setLeft(left);
        treeNode.getRight().setRight(right);

        // 层4
        left = new TreeNode("H");
        treeNode.getLeft().getLeft().setLeft(left);

        return treeNode;
    }

    /**
     * 构造第三颗二叉树，可根据实际情况修改二叉树
     * @return
     */
    private TreeNode constructTreeNode3() {
        // 构造二叉树，层1
        TreeNode treeNode = new TreeNode("A");

        // 层2
        TreeNode left = new TreeNode("C");
        TreeNode right = new TreeNode("B");
        treeNode.setLeft(left);
        treeNode.setRight(right);

        // 层3
        left = new TreeNode("G");
        right = new TreeNode("F");
        treeNode.getLeft().setLeft(left);
        treeNode.getLeft().setRight(right);
        left = new TreeNode("E");
        right = new TreeNode("D");
        treeNode.getRight().setLeft(left);
        treeNode.getRight().setRight(right);

        /**
        // 层4
        left = new TreeNode("H");
        treeNode.getLeft().getLeft().setLeft(left);
         **/

        return treeNode;
    }

}
