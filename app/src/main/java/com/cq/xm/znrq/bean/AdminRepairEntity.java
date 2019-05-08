package com.cq.xm.znrq.bean;

/**
 * @author dapeng
 * @title
 * @data 2018/1/31
 * @email 919142784@qq.com
 */
public class AdminRepairEntity {


    /**
     * routingInspection : {"id":13,"meterCode":"30","prevId":1,"prevLimit":15,"readLimit":20,"remark":"","rouName":"","rouTime":"2018年01月31日   23:46","stat":"00"}
     */

    private RoutingInspectionBean routingInspection;

    public RoutingInspectionBean getRoutingInspection() {
        return routingInspection;
    }

    public void setRoutingInspection(RoutingInspectionBean routingInspection) {
        this.routingInspection = routingInspection;
    }

    public static class RoutingInspectionBean {
        /**
         * id : 13
         * meterCode : 30
         * prevId : 1
         * prevLimit : 15.0
         * readLimit : 20
         * remark :
         * rouName :
         * rouTime : 2018年01月31日   23:46
         * stat : 00
         */

        private int id;
        private String meterCode;
        private int prevId;
        private double prevLimit;
        private int readLimit;
        private String remark;
        private String rouName;
        private String rouTime;
        private String stat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMeterCode() {
            return meterCode;
        }

        public void setMeterCode(String meterCode) {
            this.meterCode = meterCode;
        }

        public int getPrevId() {
            return prevId;
        }

        public void setPrevId(int prevId) {
            this.prevId = prevId;
        }

        public double getPrevLimit() {
            return prevLimit;
        }

        public void setPrevLimit(double prevLimit) {
            this.prevLimit = prevLimit;
        }

        public int getReadLimit() {
            return readLimit;
        }

        public void setReadLimit(int readLimit) {
            this.readLimit = readLimit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRouName() {
            return rouName;
        }

        public void setRouName(String rouName) {
            this.rouName = rouName;
        }

        public String getRouTime() {
            return rouTime;
        }

        public void setRouTime(String rouTime) {
            this.rouTime = rouTime;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }
    }
}
