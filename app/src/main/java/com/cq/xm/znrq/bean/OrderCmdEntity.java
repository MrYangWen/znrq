package com.cq.xm.znrq.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：{大鹏} on 2017/9/12 10:41
 * 邮箱：919142784@qq.com
 * 页面名称：
 */
public class OrderCmdEntity implements Parcelable {


    /**
     * content : 2222222222222222
     * password : 123456
     */

    private CardBean card;

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean implements Parcelable {


        private String content;
        private String password;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.password);
        }

        public CardBean() {
        }

        protected CardBean(Parcel in) {
            this.content = in.readString();
            this.password = in.readString();
        }

        public static final Creator<CardBean> CREATOR = new Creator<CardBean>() {
            @Override
            public CardBean createFromParcel(Parcel source) {
                return new CardBean(source);
            }

            @Override
            public CardBean[] newArray(int size) {
                return new CardBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.card, flags);
    }

    public OrderCmdEntity() {
    }

    protected OrderCmdEntity(Parcel in) {
        this.card = in.readParcelable(CardBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderCmdEntity> CREATOR = new Parcelable.Creator<OrderCmdEntity>() {
        @Override
        public OrderCmdEntity createFromParcel(Parcel source) {
            return new OrderCmdEntity(source);
        }

        @Override
        public OrderCmdEntity[] newArray(int size) {
            return new OrderCmdEntity[size];
        }
    };
}
