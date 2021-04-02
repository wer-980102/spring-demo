package com.wer.parking.utlis;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author nutgi
 */
@Data
@ToString
public class ElasticSearchPageResponse<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<T> data;
}
