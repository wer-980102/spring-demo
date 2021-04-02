package com.wer.parking.utlis.enums;

/**
 * @author nutgi
 */

public enum ParkingIndex {
        ACCESS_INFO("parking_es_access_info", "车辆出入记录表"),
        PAY_INFO("parking_es_pay_info","停车缴费记录表");

        private final String index;
        private final String comment;

        ParkingIndex(String index, String comment)
        {
            this.index = index;
            this.comment = comment;
        }

        public String getIndex()
        {
            return index;
        }

        public String getComment()
        {
            return comment;
        }

}
