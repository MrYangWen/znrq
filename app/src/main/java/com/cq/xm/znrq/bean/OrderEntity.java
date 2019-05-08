package com.cq.xm.znrq.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author dapeng
 * @title
 * @data 2017/9/10
 * @email 919142784@qq.com
 */
public class OrderEntity {

    /**
     * arg : {}
     * pg : {"plainPageNum":1,"pageNum":1,"numPerPage":50,"orderField":"id","orderDirection":"DESC","totalPage":1,"prePage":1,"nextPage":1,"totalCount":3,"arg":{}}
     * items : [{"payStat":"01","addNumber":50,"accountId":92,"chargeWay":"00","operatorId":0,"seqNo":"0908071558-1868","packageId":"17090800000286","gasAmount":0,"crtUserId":0,"addAmount":125,"optType":"00","id":100,"meterId":1089,"writeStat":"01","latePayment":0,"payNum":3,"payType":"03","payDate":"2017-09-08 07:15:55","cmdStat":"00","settle":"01","crtDate":"2017-09-08 07:15:55","gasUserId":92,"readLimit":0},{"payStat":"01","addNumber":20,"accountId":92,"chargeWay":"00","operatorId":0,"seqNo":"0908071339-1821","packageId":"17090800000280","gasAmount":0,"crtUserId":0,"addAmount":50,"optType":"00","id":99,"meterId":1089,"writeStat":"01","latePayment":0,"payNum":2,"payType":"03","payDate":"2017-09-08 07:13:36","cmdStat":"00","settle":"01","crtDate":"2017-09-08 07:13:36","gasUserId":92,"readLimit":0},{"payStat":"01","addNumber":300,"accountId":92,"chargeWay":"00","operatorId":0,"seqNo":"0908070456-1173","packageId":"17090800000269","gasAmount":0,"crtUserId":0,"addAmount":360,"optType":"00","id":98,"meterId":1089,"writeStat":"01","latePayment":0,"payNum":1,"payType":"03","payDate":"2017-09-08 07:04:53","cmdStat":"00","settle":"01","crtDate":"2017-09-08 07:04:53","gasUserId":92,"readLimit":0}]
     */

    private ArgBean arg;
    private PgBean pg;
    private List<ItemsBean> items;

    public ArgBean getArg() {
        return arg;
    }

    public void setArg(ArgBean arg) {
        this.arg = arg;
    }

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

    public static class ArgBean {
    }

    public static class PgBean {
        /**
         * plainPageNum : 1
         * pageNum : 1
         * numPerPage : 50
         * orderField : id
         * orderDirection : DESC
         * totalPage : 1
         * prePage : 1
         * nextPage : 1
         * totalCount : 3
         * arg : {}
         */

        private int plainPageNum;
        private int pageNum;
        private int numPerPage;
        private String orderField;
        private String orderDirection;
        private int totalPage;
        private int prePage;
        private int nextPage;
        private int totalCount;
        private ArgBeanX arg;

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

        public ArgBeanX getArg() {
            return arg;
        }

        public void setArg(ArgBeanX arg) {
            this.arg = arg;
        }

        public static class ArgBeanX {
        }
    }

    public static class ItemsBean implements Parcelable {


        /**
         * payStat : 01
         * addNumber : 50
         * accountId : 92
         * chargeWay : 00
         * operatorId : 0
         * seqNo : 0908071558-1868
         * packageId : 17090800000286
         * gasAmount : 0
         * crtUserId : 0
         * addAmount : 125
         * optType : 00
         * id : 100
         * meterId : 1089
         * writeStat : 01
         * latePayment : 0
         * payNum : 3
         * payType : 03
         * payDate : 2017-09-08 07:15:55
         * cmdStat : 00
         * settle : 01
         * crtDate : 2017-09-08 07:15:55
         * gasUserId : 92
         * readLimit : 0
         */

        private String payStat;
        private int addNumber;
        private int accountId;
        private String chargeWay;
        private int operatorId;
        private String seqNo;
        private String packageId;
        private int gasAmount;
        private int crtUserId;
        private String addAmount;
        private String optType;
        private int id;
        private int meterId;
        private String writeStat;
        private int latePayment;
        private int payNum;
        private String payType;
        private String payDate;
        private String cmdStat;
        private String settle;
        private String crtDate;
        private int gasUserId;
        private int readLimit;

        public String getPayStat() {
            return payStat;
        }

        public void setPayStat(String payStat) {
            this.payStat = payStat;
        }

        public int getAddNumber() {
            return addNumber;
        }

        public void setAddNumber(int addNumber) {
            this.addNumber = addNumber;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getChargeWay() {
            return chargeWay;
        }

        public void setChargeWay(String chargeWay) {
            this.chargeWay = chargeWay;
        }

        public int getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(int operatorId) {
            this.operatorId = operatorId;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public int getGasAmount() {
            return gasAmount;
        }

        public void setGasAmount(int gasAmount) {
            this.gasAmount = gasAmount;
        }

        public int getCrtUserId() {
            return crtUserId;
        }

        public void setCrtUserId(int crtUserId) {
            this.crtUserId = crtUserId;
        }

        public String getAddAmount() {
            return addAmount;
        }

        public void setAddAmount(String addAmount) {
            this.addAmount = addAmount;
        }

        public String getOptType() {
            return optType;
        }

        public void setOptType(String optType) {
            this.optType = optType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMeterId() {
            return meterId;
        }

        public void setMeterId(int meterId) {
            this.meterId = meterId;
        }

        public String getWriteStat() {
            return writeStat;
        }

        public void setWriteStat(String writeStat) {
            this.writeStat = writeStat;
        }

        public int getLatePayment() {
            return latePayment;
        }

        public void setLatePayment(int latePayment) {
            this.latePayment = latePayment;
        }

        public int getPayNum() {
            return payNum;
        }

        public void setPayNum(int payNum) {
            this.payNum = payNum;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getCmdStat() {
            return cmdStat;
        }

        public void setCmdStat(String cmdStat) {
            this.cmdStat = cmdStat;
        }

        public String getSettle() {
            return settle;
        }

        public void setSettle(String settle) {
            this.settle = settle;
        }

        public String getCrtDate() {
            return crtDate;
        }

        public void setCrtDate(String crtDate) {
            this.crtDate = crtDate;
        }

        public int getGasUserId() {
            return gasUserId;
        }

        public void setGasUserId(int gasUserId) {
            this.gasUserId = gasUserId;
        }

        public int getReadLimit() {
            return readLimit;
        }

        public void setReadLimit(int readLimit) {
            this.readLimit = readLimit;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.payStat);
            dest.writeInt(this.addNumber);
            dest.writeInt(this.accountId);
            dest.writeString(this.chargeWay);
            dest.writeInt(this.operatorId);
            dest.writeString(this.seqNo);
            dest.writeString(this.packageId);
            dest.writeInt(this.gasAmount);
            dest.writeInt(this.crtUserId);
            dest.writeString(this.addAmount);
            dest.writeString(this.optType);
            dest.writeInt(this.id);
            dest.writeInt(this.meterId);
            dest.writeString(this.writeStat);
            dest.writeInt(this.latePayment);
            dest.writeInt(this.payNum);
            dest.writeString(this.payType);
            dest.writeString(this.payDate);
            dest.writeString(this.cmdStat);
            dest.writeString(this.settle);
            dest.writeString(this.crtDate);
            dest.writeInt(this.gasUserId);
            dest.writeInt(this.readLimit);
        }

        public ItemsBean() {
        }

        protected ItemsBean(Parcel in) {
            this.payStat = in.readString();
            this.addNumber = in.readInt();
            this.accountId = in.readInt();
            this.chargeWay = in.readString();
            this.operatorId = in.readInt();
            this.seqNo = in.readString();
            this.packageId = in.readString();
            this.gasAmount = in.readInt();
            this.crtUserId = in.readInt();
            this.addAmount = in.readString();
            this.optType = in.readString();
            this.id = in.readInt();
            this.meterId = in.readInt();
            this.writeStat = in.readString();
            this.latePayment = in.readInt();
            this.payNum = in.readInt();
            this.payType = in.readString();
            this.payDate = in.readString();
            this.cmdStat = in.readString();
            this.settle = in.readString();
            this.crtDate = in.readString();
            this.gasUserId = in.readInt();
            this.readLimit = in.readInt();
        }

        public static final Parcelable.Creator<ItemsBean> CREATOR = new Parcelable.Creator<ItemsBean>() {
            @Override
            public ItemsBean createFromParcel(Parcel source) {
                return new ItemsBean(source);
            }

            @Override
            public ItemsBean[] newArray(int size) {
                return new ItemsBean[size];
            }
        };
    }
}
