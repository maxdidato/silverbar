package com.maxdidato.silverbar.datalayer;

import java.util.LinkedHashMap;

public class KeyValueStorage {
    LinkedHashMap<Object, Object> entries;

    public KeyValueStorage() {
        entries = new LinkedHashMap<>();
    }

    public void addValue(Object id, Object value) {
         entries.put(id, value);
    }

    public Object retrieveValue(Object id) {
        return entries.get(id);
    }

    public LinkedHashMap getAllValues(){
        return entries;
    }

}