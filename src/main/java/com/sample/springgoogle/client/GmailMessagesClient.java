package com.sample.springgoogle.client;

import com.sample.springgoogle.model.GmailMessageResponse;
import com.sample.springgoogle.model.GmailMessagesListResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class GmailMessagesClient {

    public static final String HTTPS_WWW_GOOGLEAPIS_BASE_URL = "https://www.googleapis.com/gmail/v1/users/";
    private String url;
    private OAuth2RestTemplate googleRestTemplate;
    private Principal principal;

    public GmailMessagesClient(OAuth2RestTemplate googleRestTemplate, Principal principal) {
        this.googleRestTemplate = googleRestTemplate;
        this.principal = principal;
    }

    public List<GmailMessageResponse> listMessages(){
        List<GmailMessageResponse> fullMessagesList = new ArrayList<>();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + googleRestTemplate.getAccessToken().getValue());
        System.out.println("Bearer " + googleRestTemplate.getAccessToken().getValue());

        HttpEntity requestEntity = new HttpEntity(httpHeaders);
        ResponseEntity<GmailMessagesListResponse> messagesList = googleRestTemplate.exchange(listUrlBuilder("me"), HttpMethod.GET, requestEntity, GmailMessagesListResponse.class);

        System.out.println("======> " + messagesList.getBody().getMessages().size());

        messagesList.getBody().getMessages().stream().forEach( m -> {
            ResponseEntity<GmailMessageResponse> responseEntity = googleRestTemplate.exchange(getUrlBuilder("me", m.getId()), HttpMethod.GET, requestEntity, GmailMessageResponse.class);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                fullMessagesList.add(responseEntity.getBody());
            }
        });

        System.out.println("======> done!");
        return fullMessagesList;
    }

    public String listUrlBuilder(String userId){
        String result;
        result = HTTPS_WWW_GOOGLEAPIS_BASE_URL + userId + "/messages?includeSpamTrash=false&maxResults=10";
        return result;
    }

    public String getUrlBuilder(String userId, String messageId){
        String result;
        result = HTTPS_WWW_GOOGLEAPIS_BASE_URL + userId + "/messages/" + messageId;
        return result;
    }

}
