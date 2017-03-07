package com.sample.springgoogle.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class EmailMessage {

    private String id;
    private String threadId;
    private Long internalDate;
    private Integer sizeEstimate;
    private String snippet;
    private String date;
    private String from;
    private String to;
    private String subject;
    private String body;

    public EmailMessage(GmailMessageResponse gmailMessage) throws UnsupportedEncodingException {
        this.id = gmailMessage.getId();
        this.threadId = gmailMessage.getThreadId();
        this.internalDate = gmailMessage.getInternalDate();
        this.sizeEstimate = gmailMessage.getSizeEstimate();
        this.snippet = gmailMessage.getSnippet();
        HashMap<String, Object> gmailPayload = gmailMessage.getPayload();
        ArrayList<LinkedHashMap<String, String>> headers = (ArrayList<LinkedHashMap<String, String>>) gmailPayload.get("headers");
        headers.forEach( h -> {
            if (h.get("name").equals("Date")){
                setDate(h.get("value").toString());
            } else if(h.get("name").equals("To")){
                setTo(h.get("value").toString());
            } else if (h.get("name").equals("From")){
                setFrom(h.get("value").toString());
            } else if (h.get("name").equals("Subject")){
                setSubject(h.get("value").toString());
            }
        });

        String bodyPartData = "";
        ArrayList<LinkedHashMap<String, LinkedHashMap<String, Object>>> parts = (ArrayList<LinkedHashMap<String, LinkedHashMap<String, Object>>>) gmailPayload.get("parts");
        if(parts != null){
            LinkedHashMap<String, LinkedHashMap<String, Object>> part0 = parts.get(1);
            LinkedHashMap<String, Object> bodyPart = part0.get("body");
            bodyPartData = (String) bodyPart.get("data");
        } else {
            LinkedHashMap<String, String> bodyPart = (LinkedHashMap<String, String>) gmailPayload.get("body");
            bodyPartData = (String) bodyPart.get("data");
        }
        setBody(decodeMessageBody(bodyPartData));
    }

    private String decodeMessageBody(String bodyPartData) throws UnsupportedEncodingException {
        byte[] base64decodedBytes = Base64.getUrlDecoder().decode(bodyPartData);
        return new String(base64decodedBytes, "utf-8");
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
