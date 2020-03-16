package com.sw.controller;

/**
 *
 * @author HikingCarrot7
 */
@FunctionalInterface
public interface Controller<E>
{

    public void setDefaultData(E data);
}
