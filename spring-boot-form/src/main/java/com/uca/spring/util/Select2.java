/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uca.spring.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Select2 implements Serializable {

    List<CboDTO> results;
    Integer total_count;
    Pagination pagination;

    public Select2() {
        super();
        this.results = new ArrayList<>();
        this.pagination = new Pagination();
        pagination.setMore(true);
    }
    
    @Setter
    @Getter
    public class Pagination {

        boolean more;

    }
}
