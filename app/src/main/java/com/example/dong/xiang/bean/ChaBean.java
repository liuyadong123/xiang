package com.example.dong.xiang.bean;

import java.util.List;

public class ChaBean {
    public String message;
    public String status;
    public List<Result> result;

    public  class  Result{
          public String commodityName;
          public double price;
          public String pic;
          public String count;
          public String commodityId;
          public  int Chanum;
          public boolean checkedbox;
    }
}
