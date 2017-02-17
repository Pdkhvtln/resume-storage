package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by andrew on 05.02.17.
 */
public class OrganizationSection extends Section {
    private final List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations,"organizaions must be not null");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}