package com.sample.springgoogle.controller;

import com.sample.springgoogle.client.GmailMessagesClient;
import com.sample.springgoogle.model.EmailMessage;
import com.sample.springgoogle.model.GmailMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    private OAuth2RestTemplate googleRestTemplate;
    private ResourceServerProperties googleResource;

    @Autowired
    public MainController(OAuth2RestTemplate googleRestTemplate, ResourceServerProperties googleResource) {
        this.googleRestTemplate = googleRestTemplate;
        this.googleResource = googleResource;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping("/userinfo")
    public String userinfo(Principal principal) throws Exception {
        Map<?, ?> userInfoResponse = googleRestTemplate.getForObject(googleResource.getUserInfoUri(), Map.class);
        String username = principal.getName();
        String token = googleRestTemplate.getOAuth2ClientContext().getAccessToken().getValue();
        return username + "--" + token;
    }

    @RequestMapping("/list")
    public List<String> list(OAuth2Authentication authentication, Principal principal) {
        List<String> result = new ArrayList<>();

        GmailMessagesClient client = new GmailMessagesClient(googleRestTemplate, principal);
        List<GmailMessageResponse> gmailMessages = client.listMessages();
        List<EmailMessage> messages = new ArrayList<>();
        gmailMessages.forEach( gm -> {
            try {
                EmailMessage email = new EmailMessage(gm);
                messages.add(email);
                result.add(email.getSubject());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
//        return authentication.getName();
        return result;
    }
}
