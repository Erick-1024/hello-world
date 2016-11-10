package com.cana.vbam.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author XuMeng
 * 分页返回数据
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int total = 0;
    private List<T> data;

    public PageResult() {
    }

    public PageResult(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = null == data ? new ArrayList<T>() : data;
        this.total = null == data ? total = 0 : data.size();
    }
    
}
