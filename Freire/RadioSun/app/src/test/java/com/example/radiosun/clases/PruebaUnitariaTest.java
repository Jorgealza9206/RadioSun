package com.example.radiosun.clases;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class PruebaUnitariaTest extends TestCase {
    private PruebaUnitaria prueba;

    @Before
    protected void setUp() throws Exception {
        prueba = new PruebaUnitaria();
    }

    @Test
    public void testPaneles(){
        assertEquals(4.0, prueba.paneles(5.0,250.0,200.0,150.0,210.0,250.0,200.0));
    }
}