package com.example.dong.xiang.bean;

import java.util.List;

public class ZuJiBean {
    public String message;
    public String status;
    public List<ZuJiResult> result;
    public class ZuJiResult{
        public String commodityName;
        public String commodityId;
        public String masterPic;
        public String price;
        public String browseNum;
        public String browseTime;
        public String userId;

    }
}
