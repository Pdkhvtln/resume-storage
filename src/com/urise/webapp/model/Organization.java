package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrew on 05.02.17.
 */
public class Organization {
    private final Link homePage;

    private final List<Position> position;

    public Organization(String name, String url, List<Position> position)//, LocalDate startDate, LocalDate endDate, String title, String description)
    {
        this.homePage = new Link(name,url);
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        return position != null ? position.equals(that.position) : that.position == null;
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", position=" + position +
                '}';
    }
}
