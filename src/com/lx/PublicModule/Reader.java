package com.lx.PublicModule;

// 定义读者信息
// 和读者信息表中各表项一一对应，用来保存查询结果

public class Reader {
    private String id;
    private String readerName;
    private String readerType;
    private String sex;
    private Integer maxNum;
    private Integer daysNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderType() {
        return readerType;
    }

    public void setReaderType(String readerType) {
        this.readerType = readerType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getDaysNum() {
        return daysNum;
    }

    public void setDaysNum(Integer daysNum) {
        this.daysNum = daysNum;
    }
}
