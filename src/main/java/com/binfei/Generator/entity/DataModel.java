package com.binfei.Generator.entity;

import java.util.HashMap;
import java.util.List;

public class DataModel<T> extends HashMap<String, Object> {
    private T record;

    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
        this.put("record",record);
    }

    public String getValue(String key) {
        if(!this.containsKey(key)) {
            return null;
        } else {
            List list = (List)this.get(key);
            return list == null?null:(list.get(0) == null?null:(list.size() > 0?((String)list.get(0)).trim():null));
        }
    }

    public List<String> getList(String key) {
        return (List)this.get(key);
    }

    public String[] getArray(String key) {
        if(!this.containsKey(key)) {
            return null;
        } else {
            List list = (List)this.get(key);
            return !list.isEmpty()?(String[])((String[])list.toArray()):null;
        }
    }

    public int getInt(String key) {
        if(!this.containsKey(key)) {
            return -1;
        } else {
            try {
                return Integer.valueOf(this.getValue(key)).intValue();
            } catch (Exception var3) {
                return -1;
            }
        }
    }

    public Integer getInteger(String key) {
        if(!this.containsKey(key)) {
            return null;
        } else {
            try {
                return Integer.valueOf(this.getValue(key));
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public boolean isSingleValue(String key) {
        return this.containsKey(key) && ((List)this.get(key)).size() == 1;
    }



    public boolean getBoolean(String key) {
        if(!this.containsKey(key)) {
            return false;
        } else {
            try {
                return Boolean.valueOf(this.getValue(key)).booleanValue();
            } catch (Exception var3) {
                return false;
            }
        }
    }

    public Double getDouble(String key) {
        if(!this.containsKey(key)) {
            return null;
        } else {
            try {
                return Double.valueOf(Double.parseDouble(this.getValue(key)));
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public Long getLong(String key) {
        if(!this.containsKey(key)) {
            return null;
        } else {
            try {
                return Long.valueOf(Long.parseLong(this.getValue(key)));
            } catch (Exception var3) {
                return null;
            }
        }
    }
}
