package com.wer.importInfo.model;

import com.wer.importInfo.utils.ExcelColumn;
import lombok.Data;

@Data
public class BusClick {

    @ExcelColumn(value = "姓名", col = 1)
    private String name;

    @ExcelColumn(value = "sex", col = 2)
    private String sex;

    @ExcelColumn(value = "age", col = 3)
    private String age;

    @ExcelColumn(value = "time", col = 4)
    private String time;

    @ExcelColumn(value = "remark", col = 5)
    private String remark;
}
