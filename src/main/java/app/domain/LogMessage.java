package app.domain;

public enum LogMessage {

    LOG10001("METRIC - RECEIVED EVENT: [%s]"),
    LOG10002("METRIC - SUCCESSFUL EVENT: [%s]"),
    LOG10003("METRIC - FAIL EVENT: [%s]");

    private String message;

    LogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return toString();
    }

    @Override
    public String toString() {
        return new StringBuilder(this.name()).append(" - ").append(message).toString();
    }

}