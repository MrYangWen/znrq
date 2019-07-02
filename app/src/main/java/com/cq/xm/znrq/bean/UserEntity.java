package com.cq.xm.znrq.bean;

/**
 * Created by JackMar on 2017/6/13.
 * 邮箱：1261404794@qq.com
 */

public class UserEntity {
    /**
     * id : 3
     * nature : 01
     * priceName : 非阶梯民用气
     * step : 1
     * quota1 : 0
     * unitPrice1 : 1.2
     * quota2 : 0
     * unitPrice2 : 0
     * quota3 : 0
     * unitPrice3 : 0
     * quota4 : 0
     * unitPrice4 : 0
     * quota5 : 0
     * unitPrice5 : 0
     * cycle : 12
     * billingType : 00
     * openDate : 2017-02-06
     * readjustTime : 1486310400000
     * regulate : Z
     * readjustWay : W
     * defaultPrice : 0.01
     * maxBuyAmount : 1950
     * monthFlow : 35
     * crtUserId : 1
     * crtUserName : 管理员
     * crtDate : 1486368682000
     * updUserId : 1
     * updUserName : 管理员
     * updDate : 1502654094000
     */

    private PcBean pc;
    /**
     * addrOther :
     * addr : 检查院小区
     * repeaterCode : 0
     * meterType : 21
     * installUnit :
     * nameplate : 249
     * concentratorId : 0
     * protocolCode : 00000000243434
     * addrFloor :
     * version : 0
     * id : 26
     * installFee : 0
     * userGender :
     * maxBuyCount : 0
     * overdraft : 0
     * readType :
     * serviceRemark :
     * addrNumber :
     * userName : 1221
     * meterPosition :
     * bank :
     * installId :
     * billingType : 01
     * addrLocation : 检查院小区
     * meterStat : 00
     * certCard :
     * gasUserId : 26
     * office :
     * userCode : 00001024
     * deadDate :
     * airDirection :
     * addrBuilding :
     * isTax : 00
     * startLimit : 0
     * meterId : 1007
     * setupFee : 0
     * makeDate :
     * email :
     * isFlag : 0
     * settingCardCode : 01
     * userStatus : 00
     * accountType : 00
     * caliber :
     * factoryCode : 243434
     * contractCode : 17021800000094
     * mobile :
     * bankAccount :
     * latePaymentType :
     */

    private AccountBean account;

    public PcBean getPc() {
        return pc;
    }

    public void setPc(PcBean pc) {
        this.pc = pc;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public static class PcBean {
        private int id;
        private String nature;
        private String priceName;
        private int step;
        private int quota1;
        private double unitPrice1;
        private int quota2;
        private int unitPrice2;
        private int quota3;
        private int unitPrice3;
        private int quota4;
        private int unitPrice4;
        private int quota5;
        private int unitPrice5;
        private String cycle;
        private String billingType;
        private String openDate;
        private long readjustTime;
        private String regulate;
        private String readjustWay;
        private double defaultPrice;
        private int maxBuyAmount;
        private int monthFlow;
        private int crtUserId;
        private String crtUserName;
        private long crtDate;
        private int updUserId;
        private String updUserName;
        private long updDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getPriceName() {
            return priceName;
        }

        public void setPriceName(String priceName) {
            this.priceName = priceName;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getQuota1() {
            return quota1;
        }

        public void setQuota1(int quota1) {
            this.quota1 = quota1;
        }

        public double getUnitPrice1() {
            return unitPrice1;
        }

        public void setUnitPrice1(double unitPrice1) {
            this.unitPrice1 = unitPrice1;
        }

        public int getQuota2() {
            return quota2;
        }

        public void setQuota2(int quota2) {
            this.quota2 = quota2;
        }

        public int getUnitPrice2() {
            return unitPrice2;
        }

        public void setUnitPrice2(int unitPrice2) {
            this.unitPrice2 = unitPrice2;
        }

        public int getQuota3() {
            return quota3;
        }

        public void setQuota3(int quota3) {
            this.quota3 = quota3;
        }

        public int getUnitPrice3() {
            return unitPrice3;
        }

        public void setUnitPrice3(int unitPrice3) {
            this.unitPrice3 = unitPrice3;
        }

        public int getQuota4() {
            return quota4;
        }

        public void setQuota4(int quota4) {
            this.quota4 = quota4;
        }

        public int getUnitPrice4() {
            return unitPrice4;
        }

        public void setUnitPrice4(int unitPrice4) {
            this.unitPrice4 = unitPrice4;
        }

        public int getQuota5() {
            return quota5;
        }

        public void setQuota5(int quota5) {
            this.quota5 = quota5;
        }

        public int getUnitPrice5() {
            return unitPrice5;
        }

        public void setUnitPrice5(int unitPrice5) {
            this.unitPrice5 = unitPrice5;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public String getBillingType() {
            return billingType;
        }

        public void setBillingType(String billingType) {
            this.billingType = billingType;
        }

        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }

        public long getReadjustTime() {
            return readjustTime;
        }

        public void setReadjustTime(long readjustTime) {
            this.readjustTime = readjustTime;
        }

        public String getRegulate() {
            return regulate;
        }

        public void setRegulate(String regulate) {
            this.regulate = regulate;
        }

        public String getReadjustWay() {
            return readjustWay;
        }

        public void setReadjustWay(String readjustWay) {
            this.readjustWay = readjustWay;
        }

        public double getDefaultPrice() {
            return defaultPrice;
        }

        public void setDefaultPrice(double defaultPrice) {
            this.defaultPrice = defaultPrice;
        }

        public int getMaxBuyAmount() {
            return maxBuyAmount;
        }

        public void setMaxBuyAmount(int maxBuyAmount) {
            this.maxBuyAmount = maxBuyAmount;
        }

        public int getMonthFlow() {
            return monthFlow;
        }

        public void setMonthFlow(int monthFlow) {
            this.monthFlow = monthFlow;
        }

        public int getCrtUserId() {
            return crtUserId;
        }

        public void setCrtUserId(int crtUserId) {
            this.crtUserId = crtUserId;
        }

        public String getCrtUserName() {
            return crtUserName;
        }

        public void setCrtUserName(String crtUserName) {
            this.crtUserName = crtUserName;
        }

        public long getCrtDate() {
            return crtDate;
        }

        public void setCrtDate(long crtDate) {
            this.crtDate = crtDate;
        }

        public int getUpdUserId() {
            return updUserId;
        }

        public void setUpdUserId(int updUserId) {
            this.updUserId = updUserId;
        }

        public String getUpdUserName() {
            return updUserName;
        }

        public void setUpdUserName(String updUserName) {
            this.updUserName = updUserName;
        }

        public long getUpdDate() {
            return updDate;
        }

        public void setUpdDate(long updDate) {
            this.updDate = updDate;
        }
    }

    public static class AccountBean {
        private String addrOther;
        private String addr;
        private int repeaterCode;
        private String meterType;
        private String installUnit;
        private String nameplate;
        private int concentratorId;
        private String protocolCode;
        private String addrFloor;
        private String version;
        private int id;
        private int installFee;
        private String userGender;
        private int maxBuyCount;
        private int overdraft;
        private String readType;
        private String serviceRemark;
        private String addrNumber;
        private String userName;
        private String meterPosition;
        private String bank;
        private String installId;
        private String billingType;
        private String addrLocation;
        private String meterStat;
        private String certCard;
        private int gasUserId;
        private String office;
        private String userCode;
        private String deadDate;
        private String airDirection;
        private String addrBuilding;
        private String isTax;
        private int startLimit;
        private int meterId;
        private int setupFee;
        private String makeDate;
        private String email;
        private int isFlag;
        private String settingCardCode;
        private String userStatus;
        private String accountType;
        private String caliber;
        private String factoryCode;
        private String contractCode;
        private String mobile;
        private String bankAccount;
        private String latePaymentType;

        public String getAddrOther() {
            return addrOther;
        }

        public void setAddrOther(String addrOther) {
            this.addrOther = addrOther;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getRepeaterCode() {
            return repeaterCode;
        }

        public void setRepeaterCode(int repeaterCode) {
            this.repeaterCode = repeaterCode;
        }

        public String getMeterType() {
            return meterType;
        }

        public void setMeterType(String meterType) {
            this.meterType = meterType;
        }

        public String getInstallUnit() {
            return installUnit;
        }

        public void setInstallUnit(String installUnit) {
            this.installUnit = installUnit;
        }

        public String getNameplate() {
            return nameplate;
        }

        public void setNameplate(String nameplate) {
            this.nameplate = nameplate;
        }

        public int getConcentratorId() {
            return concentratorId;
        }

        public void setConcentratorId(int concentratorId) {
            this.concentratorId = concentratorId;
        }

        public String getProtocolCode() {
            return protocolCode;
        }

        public void setProtocolCode(String protocolCode) {
            this.protocolCode = protocolCode;
        }

        public String getAddrFloor() {
            return addrFloor;
        }

        public void setAddrFloor(String addrFloor) {
            this.addrFloor = addrFloor;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInstallFee() {
            return installFee;
        }

        public void setInstallFee(int installFee) {
            this.installFee = installFee;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public int getMaxBuyCount() {
            return maxBuyCount;
        }

        public void setMaxBuyCount(int maxBuyCount) {
            this.maxBuyCount = maxBuyCount;
        }

        public int getOverdraft() {
            return overdraft;
        }

        public void setOverdraft(int overdraft) {
            this.overdraft = overdraft;
        }

        public String getReadType() {
            return readType;
        }

        public void setReadType(String readType) {
            this.readType = readType;
        }

        public String getServiceRemark() {
            return serviceRemark;
        }

        public void setServiceRemark(String serviceRemark) {
            this.serviceRemark = serviceRemark;
        }

        public String getAddrNumber() {
            return addrNumber;
        }

        public void setAddrNumber(String addrNumber) {
            this.addrNumber = addrNumber;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMeterPosition() {
            return meterPosition;
        }

        public void setMeterPosition(String meterPosition) {
            this.meterPosition = meterPosition;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getInstallId() {
            return installId;
        }

        public void setInstallId(String installId) {
            this.installId = installId;
        }

        public String getBillingType() {
            return billingType;
        }

        public void setBillingType(String billingType) {
            this.billingType = billingType;
        }

        public String getAddrLocation() {
            return addrLocation;
        }

        public void setAddrLocation(String addrLocation) {
            this.addrLocation = addrLocation;
        }

        public String getMeterStat() {
            return meterStat;
        }

        public void setMeterStat(String meterStat) {
            this.meterStat = meterStat;
        }

        public String getCertCard() {
            return certCard;
        }

        public void setCertCard(String certCard) {
            this.certCard = certCard;
        }

        public int getGasUserId() {
            return gasUserId;
        }

        public void setGasUserId(int gasUserId) {
            this.gasUserId = gasUserId;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getDeadDate() {
            return deadDate;
        }

        public void setDeadDate(String deadDate) {
            this.deadDate = deadDate;
        }

        public String getAirDirection() {
            return airDirection;
        }

        public void setAirDirection(String airDirection) {
            this.airDirection = airDirection;
        }

        public String getAddrBuilding() {
            return addrBuilding;
        }

        public void setAddrBuilding(String addrBuilding) {
            this.addrBuilding = addrBuilding;
        }

        public String getIsTax() {
            return isTax;
        }

        public void setIsTax(String isTax) {
            this.isTax = isTax;
        }

        public int getStartLimit() {
            return startLimit;
        }

        public void setStartLimit(int startLimit) {
            this.startLimit = startLimit;
        }

        public int getMeterId() {
            return meterId;
        }

        public void setMeterId(int meterId) {
            this.meterId = meterId;
        }

        public int getSetupFee() {
            return setupFee;
        }

        public void setSetupFee(int setupFee) {
            this.setupFee = setupFee;
        }

        public String getMakeDate() {
            return makeDate;
        }

        public void setMakeDate(String makeDate) {
            this.makeDate = makeDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIsFlag() {
            return isFlag;
        }

        public void setIsFlag(int isFlag) {
            this.isFlag = isFlag;
        }

        public String getSettingCardCode() {
            return settingCardCode;
        }

        public void setSettingCardCode(String settingCardCode) {
            this.settingCardCode = settingCardCode;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getCaliber() {
            return caliber;
        }

        public void setCaliber(String caliber) {
            this.caliber = caliber;
        }

        public String getFactoryCode() {
            return factoryCode;
        }

        public void setFactoryCode(String factoryCode) {
            this.factoryCode = factoryCode;
        }

        public String getContractCode() {
            return contractCode;
        }

        public void setContractCode(String contractCode) {
            this.contractCode = contractCode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getLatePaymentType() {
            return latePaymentType;
        }

        public void setLatePaymentType(String latePaymentType) {
            this.latePaymentType = latePaymentType;
        }
    }
}
