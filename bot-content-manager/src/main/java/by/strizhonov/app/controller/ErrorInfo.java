package by.strizhonov.app.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 4L;

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorInfo() {
        // Empty bean constructor
    }

    public ErrorInfo(final LocalDateTime timestamp, final int status, final String error, final String message, final String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ErrorInfo errorInfo = (ErrorInfo) o;
        return status == errorInfo.status &&
                Objects.equals(timestamp, errorInfo.timestamp) &&
                Objects.equals(error, errorInfo.error) &&
                Objects.equals(message, errorInfo.message) &&
                Objects.equals(path, errorInfo.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, status, error, message, path);
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }


    public static final class Builder {

        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public Builder() {
        }

        public Builder timestamp(final LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(final int status) {
            this.status = status;
            return this;
        }

        public Builder error(final String error) {
            this.error = error;
            return this;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder path(final String path) {
            this.path = path;
            return this;
        }

        public ErrorInfo build() {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setTimestamp(timestamp);
            errorInfo.setStatus(status);
            errorInfo.setError(error);
            errorInfo.setMessage(message);
            errorInfo.setPath(path);
            return errorInfo;
        }
    }
}
