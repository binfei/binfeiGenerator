package com.binfei.Generator.entity;

import java.util.List;

public class EntityDto {
    private String packageName;//包名
    private String modeName;//模块名
    private String importStr;//引入
    private String tableName;//表明
    private String className;//类名
    private String propertyName;//属性名
    private List<Column> columns;//列
    private String primaryKey;//主键
    private String primaryPropertyKey;//主键属性

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getPrimaryPropertyKey() {
        return primaryPropertyKey;
    }

    public void setPrimaryPropertyKey(String primaryPropertyKey) {
        this.primaryPropertyKey = primaryPropertyKey;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getImportStr() {
        return importStr;
    }

    public void setImportStr(String importStr) {
        this.importStr = importStr;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
