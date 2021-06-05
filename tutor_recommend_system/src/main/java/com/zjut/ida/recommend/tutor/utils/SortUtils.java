package com.zjut.ida.recommend.tutor.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/6/2 20:32
 */
public class SortUtils {

    /**
     * 按value排序
     *
     * @param map 键值对
     * @param topN top N 个
     * @return 返回排序后的列表
     */
    public static <T> List<T> sortByValue(Map<T, Integer> map, int topN, boolean asc) {
        // 降序排序
        List<Map.Entry<T, Integer>> sorted = new ArrayList<>(map.entrySet());
        if (asc) {
            sorted.sort((x, y) -> x.getValue() - y.getValue());
        } else {
            sorted.sort((x, y) -> y.getValue() - x.getValue());
        }
        List<T> labels = sorted.stream().map(Map.Entry::getKey).collect(Collectors.toList());

        // 限定个数
        if (labels.size() > topN) {
            return labels.subList(0, topN);
        } else {
            return labels;
        }
    }
}
