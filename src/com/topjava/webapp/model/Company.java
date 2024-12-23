package com.topjava.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    String header;
    String businessLink;
    List<Activity> activities = new ArrayList<>();

    public Company(String header, String businessLink) {
        Objects.requireNonNull(header, "Company name must not be null");
        this.header = header;
        this.businessLink = businessLink;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public String toString() {
        return header + ", " + businessLink +
               activities.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;
        return header.equals(company.header) && Objects.equals(businessLink, company.businessLink) && activities.equals(company.activities);
    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + Objects.hashCode(businessLink);
        result = 31 * result + activities.hashCode();
        return result;
    }

    public static class Activity {
        String title;
        String content;
        // TODO: String period to Date;
        String periodStart;
        String periodEnd;

        public Activity(String title, String periodStart, String periodEnd, String content) {
            Objects.requireNonNull(title, "Title must not be null");
            Objects.requireNonNull(periodStart, "PeriodStart must not be null");
            Objects.requireNonNull(periodEnd, "PeriodEnd must not be null");
            this.title = title;
            this.periodStart = periodStart;
            this.periodEnd = periodEnd;
            this.content = content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Activity activity = (Activity) o;
            return title.equals(activity.title) && Objects.equals(content, activity.content) && periodStart.equals(activity.periodStart) && periodEnd.equals(activity.periodEnd);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + Objects.hashCode(content);
            result = 31 * result + periodStart.hashCode();
            result = 31 * result + periodEnd.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return periodStart + " - " + periodEnd +
                   ", " + title + "\n" +
                   content;
        }
    }
}
