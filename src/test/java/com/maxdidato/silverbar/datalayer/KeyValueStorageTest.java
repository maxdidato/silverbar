package com.maxdidato.silverbar.datalayer;

import org.junit.Before;
import org.junit.Test;

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
}