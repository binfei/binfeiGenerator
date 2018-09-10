package com.binfei.Generator.entity;

public class Column {
    private String columnName;//列名
    private String jdbcType;//列类型
    private String propertyName;//属性名
    private String propertyType;//属性类型
    private String isSND;//属性类型
    private String remarks;//注释

    public String getIsSND() {
        return isSND;
    }

    public void setIsSND(String isSND) {
        this.isSND = isSND;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
