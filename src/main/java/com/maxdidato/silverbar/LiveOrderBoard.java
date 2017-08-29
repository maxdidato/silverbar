package com.maxdidato.silverbar;
import com.maxdidato.silverbar.datalayer.KeyValueStorage;
import com.maxdidato.silverbar.domain.Order;
import java.util.*;

public class LiveOrderBoard {
   KeyValueStorage keyValueStorage;

    public LiveOrderBoard() {
        //Using a LinkedHashMap to preserve the insertion order
        keyValueStorage = new KeyValueStorage();
    }

    public void register(Order order) {
        //In order to track every single order we use a uuid as id
        keyValueStorage.addValue(UUID.randomUUID(),order);
    }

    public LinkedHashMap getOrders() {
        return keyValueStorage.getAllValues();
    }
}
