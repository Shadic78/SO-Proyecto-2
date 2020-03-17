package com.sw.controller;

/**
 *
 * @author SonBear
 */
@FunctionalInterface
public interface Controller<E>
{

    public void setDefaultData(E data);
}
