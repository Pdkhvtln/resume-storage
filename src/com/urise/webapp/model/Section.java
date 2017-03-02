package com.urise.webapp.model;

import java.io.Serializable;

/**
 * Created by andrew on 02.02.17.
 */
public abstract class Section implements Serializable{
    public abstract boolean equals(Object o);

    public abstract int hashCode();

    public abstract String toString();
}
