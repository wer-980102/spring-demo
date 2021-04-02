package com.wer.parking.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkAccessInfoParam {

    /** 数据来源 */
    private String parkSourceData;

    /** 页数 */
    private Integer pageNum;
}
