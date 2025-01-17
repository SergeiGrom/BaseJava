package com.topjava.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.topjava.webapp.util.DateUtil.NOW;
import static com.topjava.webapp.util.DateUtil.of;

public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Link homePage;
    private final List<Period> periods;

    public Company(String name, String website, Period... periods) {
        this(new Link(name, website), Arrays.asList(periods));
    }

    public Company(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
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

    public static class Period implements Serializable {
        private final String title;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Period(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
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
