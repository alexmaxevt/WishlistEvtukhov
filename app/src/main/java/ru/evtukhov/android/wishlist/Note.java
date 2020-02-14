package ru.evtukhov.android.wishlist;

public class Note {

    private String title;
    private String body;
    private boolean hasDeadline;
    private String deadlineDate;

    Note(String title, String body, boolean hasDeadline, String deadlineDate) {
        this.title = title;
        this.body = body;
        this.hasDeadline = hasDeadline;
        this.deadlineDate = deadlineDate;
    }

    public Note(String title, String body, boolean hasDeadline) {
        this.title = title;
        this.body = body;
        this.hasDeadline = hasDeadline;
    }

    public Note(String title, String body, String deadlineDate) {
        this.title = title;
        this.body = body;
        this.deadlineDate = deadlineDate;
    }

    boolean isHasDeadline() {
        return hasDeadline;
    }

    String getDeadlineDate() {
        return deadlineDate;
    }

    String getTitle() {
        return title;
    }

    String getBody() {
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
