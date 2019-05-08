package com.cq.xm.znrq.bean;

import java.util.List;

/**
 * @author dapeng
 * @title
 * @data 2017/9/10
 * @email 919142784@qq.com
 */
public class RecordEntity {

    /**
     * plainPageNum : 1
     * pageNum : 1
     * numPerPage : 10
     * orderField : id
     * orderDirection : DESC
     * totalPage : 1
     * prePage : 1
     * nextPage : 1
     * totalCount : 7
     * arg : {}
     */

    private PgBean pg;
    /**
     * historyType : 0
     * crtUserName : 管理员
     * hardware : 100
     * status : 32768
     * tapStatus : 00
     * orderNum : 0
     * cbsj : 1505037191000
     * xcsj : 1505037189000
     * surplusFee : 0
     * scjbrq : 1505030252000
     * network : 23
     * qbll : 0
     * crtUserId : 1
     * userFee : 0
     * id : 253
     * feeSetup : 0
     * unit : 0
     * software : -50
     * meterId : 1105
     * scds : 0
     * pjyql : 0
     * deadSetup : 0
     * payNum : 0
     * freeQbll : 0
     * qbbh : 24961909101231
     */

    private List<ItemsBean> items;

    public PgBean getPg() {
        return pg;
    }

    public void setPg(PgBean pg) {
        this.pg = pg;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class PgBean {
        private int plainPageNum;
        private int pageNum;
        private int numPerPage;
        private String orderField;
        private String orderDirection;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;

        public int getPlainPageNum() {
            return plainPageNum;
        }

        public void setPlainPageNum(int plainPageNum) {
            this.plainPageNum = plainPageNum;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(int numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getOrderField() {
            return orderField;
        }

        public void setOrderField(String orderField) {
            this.orderField = orderField;
        }

        public String getOrderDirection() {
            return orderDirection;
        }

        public void setOrderDirection(String orderDirection) {
            this.orderDirection = orderDirection;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class ItemsBean {
        private String historyType;
        private String crtUserName;
        private int hardware;
        private String status;
        private String tapStatus;
        private int orderNum;
        private String cbsj;
        private long xcsj;
        private String surplusFee;
        private long scjbrq;
        private int network;
        private String qbll;
        private int crtUserId;
        private String userFee;
        private int id;
        private int feeSetup;
        private String unit;
        private int software;
        private int meterId;
        private int scds;
        private int pjyql;
        private int deadSetup;
        private int payNum;
        private String freeQbll;
        private String qbbh;

        public String getHistoryType() {
            return historyType;
        }

        public void setHistoryType(String historyType) {
            this.historyType = historyType;
        }

        public String getCrtUserName() {
            return crtUserName;
        }

        public void setCrtUserName(String crtUserName) {
            this.crtUserName = crtUserName;
        }

        public int getHardware() {
            return hardware;
        }

        public void setHardware(int hardware) {
            this.hardware = hardware;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTapStatus() {
            return tapStatus;
        }

        public void setTapStatus(String tapStatus) {
            this.tapStatus = tapStatus;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getCbsj() {
            return cbsj;
        }

        public void setCbsj(String cbsj) {
            this.cbsj = cbsj;
        }

        public long getXcsj() {
            return xcsj;
        }

        public void setXcsj(long xcsj) {
            this.xcsj = xcsj;
        }

        public String getSurplusFee() {
            return surplusFee;
        }

        public void setSurplusFee(String surplusFee) {
            this.surplusFee = surplusFee;
        }

        public long getScjbrq() {
            return scjbrq;
        }

        public void setScjbrq(long scjbrq) {
            this.scjbrq = scjbrq;
        }

        public int getNetwork() {
            return network;
        }

        public void setNetwork(int network) {
            this.network = network;
        }

        public String getQbll() {
            return qbll;
        }

        public void setQbll(String qbll) {
            this.qbll = qbll;
        }

        public int getCrtUserId() {
            return crtUserId;
        }

        public void setCrtUserId(int crtUserId) {
            this.crtUserId = crtUserId;
        }

        public String getUserFee() {
            return userFee;
        }

        public void setUserFee(String userFee) {
            this.userFee = userFee;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFeeSetup() {
            return feeSetup;
        }

        public void setFeeSetup(int feeSetup) {
            this.feeSetup = feeSetup;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getSoftware() {
            return software;
        }

        public void setSoftware(int software) {
            this.software = software;
        }

        public int getMeterId() {
            return meterId;
        }

        public void setMeterId(int meterId) {
            this.meterId = meterId;
        }

        public int getScds() {
            return scds;
        }

        public void setScds(int scds) {
            this.scds = scds;
        }

        public int getPjyql() {
            return pjyql;
        }

        public void setPjyql(int pjyql) {
            this.pjyql = pjyql;
        }

        public int getDeadSetup() {
            return deadSetup;
        }

        public void setDeadSetup(int deadSetup) {
            this.deadSetup = deadSetup;
        }

        public int getPayNum() {
            return payNum;
        }

        public void setPayNum(int payNum) {
            this.payNum = payNum;
        }

        public String getFreeQbll() {
            return freeQbll;
        }

        public void setFreeQbll(String freeQbll) {
            this.freeQbll = freeQbll;
        }

        public String getQbbh() {
            return qbbh;
        }

        public void setQbbh(String qbbh) {
            this.qbbh = qbbh;
        }
    }
}
