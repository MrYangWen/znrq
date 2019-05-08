package com.cq.xm.znrq.bean;

/**
 * Created by JackMar on 2017/6/19.
 * 邮箱：1261404794@qq.com
 */

public class Entity {
    private String title;
    private Child child;

    public Entity(String title, Child child) {
        this.title = title;
        this.child = child;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public static class Child {

        public Child(String content) {
            this.content = content;
        }

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
