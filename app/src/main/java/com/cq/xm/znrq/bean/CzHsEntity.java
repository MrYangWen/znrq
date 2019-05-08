package com.cq.xm.znrq.bean;

import java.util.List;

/**
 * 作者：{大鹏} on 2017/9/10 12:14
 * 邮箱：919142784@qq.com
 * 页面名称：
 */
public class CzHsEntity {


    /**
     * addAmount : 0.5
     * addNumber : 50
     * cycleAmount : 0
     * cycleNumber : 0
     */

    private PayBean pay;
    /**
     * fee : 0.5
     * quota : 50
     * step : 1
     */

    private TotalBean total;
    /**
     * fee : 0.5
     * quota : 50
     * step : 1
     * unitPrice : 0.01
     */

    private List<ItemsBean> items;

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class PayBean {
        private double addAmount;
        private int addNumber;
        private int cycleAmount;
        private int cycleNumber;

        public double getAddAmount() {
            return addAmount;
        }

        public void setAddAmount(double addAmount) {
            this.addAmount = addAmount;
        }

        public int getAddNumber() {
            return addNumber;
        }

        public void setAddNumber(int addNumber) {
            this.addNumber = addNumber;
        }

        public int getCycleAmount() {
            return cycleAmount;
        }

        public void setCycleAmount(int cycleAmount) {
            this.cycleAmount = cycleAmount;
        }

        public int getCycleNumber() {
            return cycleNumber;
        }

        public void setCycleNumber(int cycleNumber) {
            this.cycleNumber = cycleNumber;
        }
    }

    public static class TotalBean {
        private double fee;
        private int quota;
        private int step;

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getQuota() {
            return quota;
        }

        public void setQuota(int quota) {
            this.quota = quota;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }
    }

    public static class ItemsBean {
        private double fee;
        private int quota;
        private int step;
        private double unitPrice;

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getQuota() {
            return quota;
        }

        public void setQuota(int quota) {
            this.quota = quota;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
