package com.example.study.services;

import org.springframework.stereotype.Service;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 带虚拟节点的一致性Hash算法
 */
@Service
public class ConsistentHashingService {

    /**
     * 待加入Hash环的服务器列表
     */
    private static String[] servers = new String[]{"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    /**
     * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
     */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODES_NUM = 5;

    /**
     * 构造虚拟节点Tree
     */
    static {
        for (int i = 0; i < servers.length; i++) {
            for (int j = 0; j < VIRTUAL_NODES_NUM; j++) {
                String virtualNodeName = servers[i] + "&&VN"+ j;
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点名称："+ virtualNodeName +"，虚拟节点名称Hash值："+ hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值
     */
    private static int getHash(String str)
    {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    /**
     * 获取数据路由到的服务器名称
     * @param data
     * @return
     */
    public static String getServer(String data) {
        // 获取数据的Hash值
        int hash = getHash(data);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        // 第一个key就是顺时针过去离data最近的那个节点
        if (subMap.isEmpty()) {
            subMap = virtualNodes.tailMap(0);
        }
        Integer i = subMap.firstKey();
        // 返回对应的虚拟节点名称
        String virtualNode = virtualNodes.get(i);
        // 返回机器名称
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

}
