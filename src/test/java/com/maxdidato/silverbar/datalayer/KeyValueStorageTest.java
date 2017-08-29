package com.maxdidato.silverbar.datalayer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class KeyValueStorageTest {
    KeyValueStorage keyValueStorage = null;

    @Before
    public void setUp() {
        keyValueStorage = new KeyValueStorage();
    }

    @Test
    public void it_inserts_key_value_pair() {
        Object objectToBeInserted = new Object();
        int key = 1;
        keyValueStorage.addValue(key, objectToBeInserted);
        assertThat(keyValueStorage.retrieveValue(key), is(objectToBeInserted));

    }

    @Test
    public void it_returns_all_the_entries() {
        keyValueStorage.addValue(1, "Object1");
        keyValueStorage.addValue(2, "Object2");
        keyValueStorage.addValue(3, "Object3");

        LinkedHashMap allValues = keyValueStorage.getAllValues();
        assertThat(allValues.values().size(), is(3));
        assertThat(new ArrayList<>(allValues.values()), is(Arrays.asList("Object1", "Object2", "Object3")));


    }
}