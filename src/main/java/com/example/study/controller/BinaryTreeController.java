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
        right = new TreeNode("H");
        treeNode.getLeft().getLeft().setRight(right);

        String preStrRecursion = binaryTreeService.preOrderRecursion(treeNode);
        String preStrIteration = binaryTreeService.preOrderIteration(treeNode);
        String infixStrRecursion = binaryTreeService.infixOrderRecursion(treeNode);
        String infixStrIteration = binaryTreeService.infixOrderIteration(treeNode);
        String postStrRecursion = binaryTreeService.postOrderRecursion(treeNode);
        String postStrIteration = binaryTreeService.postOrderIteration(treeNode);
        String layeringStrIteration = binaryTreeService.layeringIteration(treeNode);

        // 根据前序遍历和中序遍历构造二叉树
        String preorder[] = new String[] {"A", "B", "D", "H", "E", "C", "F", "G"};
        String inorder[] = new String[] {"D", "H", "B", "E", "A", "F", "C", "G"};
        TreeNode orderNode = binaryTreeService.buildTreeNode(preorder, inorder);
        String buildTreeNodePreStr = binaryTreeService.preOrderRecursion(orderNode);
        String buildTreeNodeinfixStr = binaryTreeService.infixOrderRecursion(orderNode);

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

        return new ResponseMessage<>(mp);
    }


}
