package com.topjava.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String website) {
        Objects.requireNonNull(name, "Company name must not be null");
        this.homePage = new Link(name, website);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;
        return homePage.equals(company.homePage) && Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + Objects.hashCode(periods);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
               "homePage=" + homePage +
               ", periods=" + periods +
               '}';
    }

    public static class Period {
        private final String title;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
            Objects.requireNonNull(title, "Title must not be null");
            Objects.requireNonNull(startDate, "PeriodStart must not be null");
            Objects.requireNonNull(endDate, "PeriodEnd must not be null");
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;
            return title.equals(period.title) && Objects.equals(description, period.description) && startDate.equals(period.startDate) && endDate.equals(period.endDate);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + Objects.hashCode(description);
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return startDate + " - " + endDate +
                   ", " + title + "\n" +
                   description;
        }
    }
}
