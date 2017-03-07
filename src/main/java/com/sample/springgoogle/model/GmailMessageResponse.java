package com.sample.springgoogle.model;

import com.google.api.services.gmail.model.MessagePart;

import java.util.HashMap;
import java.util.List;

public class GmailMessageResponse {

    private String historyId;
    private String id;
    private Long internalDate;
    private List<String> labelIds;
    private HashMap<String, Object> payload;
    private String raw;
    private Integer sizeEstimate;
    private String snippet;
    private String threadId;

    public GmailMessageResponse() {
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(Long internalDate) {
        this.internalDate = internalDate;
    }

    public List<String> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<String> labelIds) {
        this.labelIds = labelIds;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(HashMap<String, Object> payload) {
        this.payload = payload;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getSizeEstimate() {
        return sizeEstimate;
    }

    public void setSizeEstimate(Integer sizeEstimate) {
        this.sizeEstimate = sizeEstimate;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}

