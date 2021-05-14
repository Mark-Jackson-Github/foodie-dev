package com.imooc.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
public class MyPage<T> {

    protected List<T> rows = Collections.emptyList();

    /**
     * 获得总页数
     */
    protected long total;
    /**
     * 获得总记录数
     */
    protected long records;


    public MyPage(IPage<T> page) {
        this.rows= page.getRecords();
        this.total=page.getPages();
        this.records =page.getTotal();
    }


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }
}
