package com.uca.spring.jqgrid;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 * A POJO representing a jQgrid's jsonReader property.
 *
 * @see
 * <a href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">JSON
 * Data</a>
 */
public class JqgridResponse<T extends Serializable> {

    /**
     * Current page
     */
    private String page;

    /**
     * Total pages
     */
    private String total;

    /**
     * Total number of records
     */
    private String records;

    private Map<String, Object> userdata;

    /**
     * Contains the actual data
     */
    private List<T> rows;

    public JqgridResponse() {
    }

    public JqgridResponse(String page, String total, String records, List<T> rows) {
        super();
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    public JqgridResponse(String page, String total, String records, List<T> rows, Map<String, Object> userdata) {
        super();
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
        this.userdata = userdata;
    }

    public Map<String, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<String, Object> userdata) {
        this.userdata = userdata;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "JqgridResponse [page=" + page + ", total=" + total
                + ", records=" + records + "]";
    }

    public JqgridResponse<T> jGridFill(Page<T> list, Integer page, Integer rows) {

        JqgridResponse<T> response = new JqgridResponse<T>();
        response.setRows(list.getContent());
        response.setRecords(Long.valueOf(list.getTotalElements()).toString());
        response.setTotal(Integer.valueOf(list.getTotalPages()).toString());
        response.setPage(Integer.valueOf(list.getNumber() + 1).toString());
        return response;

    }
}
