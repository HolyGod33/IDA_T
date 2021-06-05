package com.zjut.ida.recommend.tutor.core.page;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplePageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 结果总数
     */
    private long total;

    public SimplePageInfo(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.total = page.getTotal();
        } else if (list != null) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.total = list.size();
        }
        this.list = list;

    }

    public SimplePageInfo(SimplePageInfo<?> pageInfo, List<T> list) {
        if (list == null) {
            this.pageNum = 0;
            this.pageSize = 0;
            this.pages = 0;
            this.list = null;
            this.total = 0;
        } else if (pageInfo == null) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.list = list;
            this.total = list.size();
        } else {
            this.pageNum = pageInfo.getPageNum();
            this.pageSize = pageInfo.getPageSize();
            this.pages = pageInfo.getPages();
            this.list = list;
            this.total = pageInfo.getTotal();
        }
    }
}

