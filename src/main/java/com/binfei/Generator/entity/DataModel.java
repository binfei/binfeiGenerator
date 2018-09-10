package com.binfei.Generator.entity;

import java.util.HashMap;

public class DataModel<T> extends HashMap<String, Object> {
    private T record;

    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
        this.put("record",record);
    }
}
