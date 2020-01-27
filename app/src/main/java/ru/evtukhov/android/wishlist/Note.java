package ru.evtukhov.android.wishlist;

public class Note {

    private String title;
    private String body;
    private boolean hasDeadline;
    private String deadlineDate;

    public Note (String title, String body, boolean hasDeadline, String deadlineDate) {
        this.title = title;
        this.body = body;
        this.hasDeadline = hasDeadline;
        this.deadlineDate = deadlineDate;
    }

    public Note (String title, String body, boolean hasDeadline) {
        this.title = title;
        this.body = body;
        this.hasDeadline = hasDeadline;
    }

    public Note (String title, String body, String deadlineDate) {
        this.title = title;
        this.body = body;
        this.deadlineDate= deadlineDate;
    }

    public boolean isHasDeadline() {
        return hasDeadline;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setHasDeadline(boolean hasDeadline) {
        this.hasDeadline = hasDeadline;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
