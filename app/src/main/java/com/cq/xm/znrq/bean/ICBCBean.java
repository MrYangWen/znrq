package com.cq.xm.znrq.bean;


/**
 * Created by jiang on 2016/11/11.
 */

public class ICBCBean {


    /**
     * clientType : 0
     * interfaceName : ICBC_WAPB_B2C
     * interfaceVersion : 1.0.0.6
     * merCert : MIIDlDCCAnygAwIBAgIKG5LKECVWAAKxCTANBgkqhkiG9w0BAQsFADA7MR8wHQYDVQQDExZJQ0JDIFRlc3QgQ29ycG9yYXRlIENBMRgwFgYDVQQKEw90ZXN0aWNiYy5jb20uY24wHhcNMTgwNjAxMDAyNDM5WhcNMTkwNjAxMDAyNDM5WjBEMRkwFwYDVQQDDBBkdWFuZ3VpbGkuZS4yMTE0MQ0wCwYDVQQLDAQyMTE0MRgwFgYDVQQKDA90ZXN0aWNiYy5jb20uY24wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCkl15+rDK5uGEmrpOxLdT3Y+Tu+J/Kz4aPc5l92CBH9g9U889R8FWNvJe6/IAZwfVfsxUiL+Tdir+93DL+35CnEb+nxybqMlpNZS9Gpr5ZVOW620iO7CdGxRRUGQlPkqFo3vhVgJXeQwHU9nGk0P7SESjuYyJ88ng2umZx7wJgdosV9lbnwx/JxyJCFFdLxfq/VEkuWJS/fudmYMo9ZLb6hKDrqyHqcHBnRF4f+YuJGiZZVRWet43iNi1QbSB+fHt2D/xLlJid4pWtGTcqlyTq4UitMMYOIyAZHroRC9ZdnpWFGCiWZWaToEBtjGaN8x5FPA8TK2gisp6POmP4qxH3AgMBAAGjgZAwgY0wHwYDVR0jBBgwFoAURH23kCw3pNntbOKkh1dnCrXwTRQwSwYDVR0fBEQwQjBAoD6gPKQ6MDgxDjAMBgNVBAMMBWNybDM2MQwwCgYDVQQLDANjcmwxGDAWBgNVBAoMD3Rlc3RpY2JjLmNvbS5jbjAdBgNVHQ4EFgQUd50qLbvPR4AXMxsYwuq2o0azJTgwDQYJKoZIhvcNAQELBQADggEBADFu0nnS8Vai4/LlhG1Ha8Hb+tqXThLjxI5ifAhjo4bAqCT0UxaOQ/fCkrR/stwAhr6dQGYy7vnT7RQFprIs4GURitswPBSiKrOHGaSg837Fsj3H6zYrBkrzLivVhHXanskiOUqRVe5Kp6BeEpKnOIM2YIAjmfP3HnojuyD0o7HYIq15o3EaU7Edc+H1NHEbalN/XbkGR6bF3nsq7qGHFubEV84rCgyOUtzM+/Tss/xoz33sR44KMX6tAOFMHqNXOXi6xb4fP0SOdhLR4uvyDQLm2/1nlq2ZJ1Iu87kfWJ7E6NrZBjQqGW+/uIXGo7VZ8343/2B707bgF//AW9uMspI=
     * merSignMsg : GJ7t+BmCKB5OwUgLgHI2yhqve4b48DfGM1hKK4Nx4cAhm83dtx33rycX+AkDVbLk4TcWVveyEdBkfcJD3NlwtXSAypgFVbPt/uWNLUhEY/WMeA/2oJ09VyBxVx9KCEJuYNWjRydbAqC8irNf1JPsAyFvvRudWzIYHdEI4xEjIqX80azNspZRKOdaLEbgZW4lSBr1Sa6j1PgsTtnkuh/HVkNgAlX4ZVvh4puvXPlB/sb9SnUGu0PdNTAhf5PgY8yAg2h9MmE8mTRxjf+oS8NDuNpxxtDpBDZbyIKiz0UOrkzPn1KBUoiyDuZcdZEXIToPC5Jc0RxHPpFuDMyK9T8UrQ==
     * tranData : PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iR0JLIiBzdGFuZGFsb25lPSJubyI/PjxCMkNSZXE+PGludGVyZmFjZU5hbWU+SUNCQ19XQVBCX0IyQzwvaW50ZXJmYWNlTmFtZT48aW50ZXJmYWNlVmVyc2lvbj4xLjAuMC42PC9pbnRlcmZhY2VWZXJzaW9uPjxvcmRlckluZm8+PG9yZGVyRGF0ZT4yMDE4MDcwNjE0MTYxMjwvb3JkZXJEYXRlPjxvcmRlcmlkPjE4MDcwNjAwMDAwMDAwPC9vcmRlcmlkPjxhbW91bnQ+MTwvYW1vdW50PjxpbnN0YWxsbWVudFRpbWVzPjE8L2luc3RhbGxtZW50VGltZXM+PGN1clR5cGU+MDAxPC9jdXJUeXBlPjxtZXJJRD4yMTE0RUUyMDIzMjAzMjwvbWVySUQ+PG1lckFjY3Q+MjExNDg2MDAwOTMwMDExNzQwOTwvbWVyQWNjdD48L29yZGVySW5mbz48Y3VzdG9tPjx2ZXJpZnlKb2luRmxhZz4wPC92ZXJpZnlKb2luRmxhZz48TGFuZ3VhZ2U+emhfQ048L0xhbmd1YWdlPjwvY3VzdG9tPjxtZXNzYWdlPjxnb29kc0lEPjwvZ29vZHNJRD48Z29vZHNOYW1lPsi8xvi30dPDPC9nb29kc05hbWU+PGdvb2RzTnVtPjE8L2dvb2RzTnVtPjxjYXJyaWFnZUFtdD4wPC9jYXJyaWFnZUFtdD48bWVySGludD7JzLPHzOHKvjwvbWVySGludD48cmVtYXJrMT5yZW1hcmsxPC9yZW1hcmsxPjxyZW1hcmsyPnJlbWFyazI8L3JlbWFyazI+PG1lclVSTD5odHRwOi8vMjIyLjE4MC4xNjMuMjA1OjgwOTAvZ2FzaHViL2FwaS9pY2JjL25vdGlmeTwvbWVyVVJMPjxtZXJWQVI+sbjXojwvbWVyVkFSPjxub3RpZnlUeXBlPkhTPC9ub3RpZnlUeXBlPjxyZXN1bHRUeXBlPjA8L3Jlc3VsdFR5cGU+PGJhY2t1cDE+PC9iYWNrdXAxPjxiYWNrdXAyPjwvYmFja3VwMj48YmFja3VwMz48L2JhY2t1cDM+PGJhY2t1cDQ+PC9iYWNrdXA0PjwvbWVzc2FnZT48L0IyQ1JlcT4=
     */

    private String clientType;
    private String interfaceName;
    private String interfaceVersion;
    private String merCert;
    private String merSignMsg;
    private String tranData;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public String getMerCert() {
        return merCert;
    }

    public void setMerCert(String merCert) {
        this.merCert = merCert;
    }

    public String getMerSignMsg() {
        return merSignMsg;
    }

    public void setMerSignMsg(String merSignMsg) {
        this.merSignMsg = merSignMsg;
    }

    public String getTranData() {
        return tranData;
    }

    public void setTranData(String tranData) {
        this.tranData = tranData;
    }
}
