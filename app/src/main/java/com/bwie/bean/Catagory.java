package com.bwie.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/11/9.
 */

public class Catagory extends BaseBean{
    private List<DataBean> data;

    public Catagory(String code, String msg) {
        super(code, msg);
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 1
         * createtime : 2017-10-10T19:41:39
         * icon : http://120.27.23.105/images/category/shop.png
         * ishome : 1
         * name : 京东超市
         */
        private boolean checked;//控制点击背景颜色
        private int cid;
        private String createtime;
        private String icon;
        private int ishome;
        private String name;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIshome() {
            return ishome;
        }

        public void setIshome(int ishome) {
            this.ishome = ishome;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
