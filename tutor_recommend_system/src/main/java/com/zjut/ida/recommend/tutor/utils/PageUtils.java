package com.zjut.ida.recommend.tutor.utils;

import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;

import java.util.List;

/**
 * @author wly
 * @date 2021/6/2 19:40
 */
public class PageUtils<T> {

    /**
     * 构造分页信息
     *
     * @param list     列表
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 分页信息
     */
    public SimplePageInfo<T> pageInfo(List<T> list, Integer pageNum, Integer pageSize) {
        List subList = startPage(list, pageNum, pageSize);
        int pages = pages(list, pageSize);
        return new SimplePageInfo(pageNum, pageSize, pages, subList, list.size());
    }

    /**
     * 开始分页
     *
     * @param list     列表
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 分页结果
     */
    public List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        // 记录总数
        int count = list.size();
        // 页数
        int pageCount = pages(list, pageSize);

        // 开始索引
        int fromIndex = (pageNum - 1) * pageSize;
        // 结束索引
        int toIndex;
        if (!pageNum.equals(pageCount)) {
            toIndex = fromIndex + pageSize;
        } else {
            toIndex = count;
        }

        return list.subList(fromIndex, toIndex);
    }

    /**
     * 计算总页数
     *
     * @param list     列表
     * @param pageSize 页大小
     * @return 总页数
     */
    public int pages(List<T> list, Integer pageSize) {
        // 记录总数
        int count = list.size();
        // 页数
        if (count % pageSize == 0) {
            return count / pageSize;
        } else {
            return count / pageSize + 1;
        }
    }
}
