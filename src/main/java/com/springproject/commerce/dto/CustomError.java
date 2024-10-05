package com.springproject.commerce.dto;

import java.time.Instant;

public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;


    public static final class CustomErrorBuilder {
        private Instant timestamp;
        private Integer status;
        private String error;
        private String path;

        private CustomErrorBuilder() {
        }

        public static CustomErrorBuilder builder() {
            return new CustomErrorBuilder();
        }

        public CustomErrorBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CustomErrorBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public CustomErrorBuilder error(String error) {
            this.error = error;
            return this;
        }

        public CustomErrorBuilder path(String path) {
            this.path = path;
            return this;
        }

        public CustomError build() {
            CustomError customError = new CustomError();
            customError.path = this.path;
            customError.timestamp = this.timestamp;
            customError.status = this.status;
            customError.error = this.error;
            return customError;
        }
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
