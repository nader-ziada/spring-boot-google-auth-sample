package com.sample.springgoogle.model;

import com.google.api.services.gmail.model.Message;

import java.util.List;

public class GmailMessagesListResponse {

    private java.util.List<Message> messages;
    private java.lang.String nextPageToken;
    private java.lang.Long resultSizeEstimate;

    public GmailMessagesListResponse() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public Long getResultSizeEstimate() {
        return resultSizeEstimate;
    }

    public void setResultSizeEstimate(Long resultSizeEstimate) {
        this.resultSizeEstimate = resultSizeEstimate;
    }
}
