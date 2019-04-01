package com.example.study.entity;

import lombok.Data;

/**
 * 树
 */
@Data
public class TreeNode
{
    /**
     * 节点数据
     */
    private String data;

    /**
     * 左子节点
     */
    private TreeNode left;

    /**
     * 右子节点
     */
    private TreeNode right;

    public TreeNode(String data) {
        this.data = data;
    }

}
