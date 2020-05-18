package pl.somday.java14.newsletter;

public enum NewsletterFrequency {
    NEVER("never"),
    WEEKLY("weekly"),
    MONTHLY("monthly");

    private String value;

    private NewsletterFrequency(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
