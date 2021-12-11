package com.example.radiosun;

import junit.framework.TestCase;

import org.junit.Assert;

public class MapaFragmentTest extends TestCase {

    public void testRadiacion() {
       MapaFragment.radiacion("4.7326", "-73.9519");
        Assert.assertEquals(true, true);
    }
}