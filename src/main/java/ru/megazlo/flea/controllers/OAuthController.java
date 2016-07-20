package ru.megazlo.flea.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.Properties;
import java.util.Random;

/**
 * @author paradoxfm - 19.02.2016
 */
@Controller
public class OAuthController {

    public static final String STATE = "state";

    private String applicationHost;
    //private OAuth20Service oAuthService;
    // Jackson ObjectMapper
    private ObjectMapper objectMapper;

    @Resource(name = "oauthProp")
    private Properties oauthProp;

    /*@Autowired
    private RestOperations facebookRestTemplate;

    @RequestMapping("/login/facebook")
    public void loginWithFacebook() {
        ObjectNode result = facebookRestTemplate.getForObject("https://graph.facebook.com/me/friends", ObjectNode.class);
        ArrayNode data = (ArrayNode) result.get("data");
        ArrayList<String> friends = new ArrayList<>();
        for (JsonNode dataNode : data) {
            friends.add(dataNode.get("name").asText());
        }
    }*/

    /*@RequestMapping("/login/facebook")
    public RedirectView loginWithFacebook() {
        oAuthService = buildOAuthService(oauthProp.getProperty("oauth.facebook.clientId"), oauthProp.getProperty("oauth.facebook.clientSecret"));
        this.objectMapper = new ObjectMapper();

        return new RedirectView(oAuthService.getAuthorizationUrl());
        //this.objectMapper.registerModule(new AfterburnerModule());
        //details.setAccessTokenUri(oauthProp.getProperty("oauth.facebook.accessTokenUri"));
        //details.setUserAuthorizationUri(oauthProp.getProperty("oauth.facebook.userAuthorizationUri"));
    }*/

    /*@RequestMapping("/auth/facebook")
    public RedirectView startAuthentication(HttpSession session) {
        session.setAttribute(STATE, state);
        String authorizationUrl = oAuthService.getAuthorizationUrl() + "&" + STATE + "=" + state;
        return new RedirectView(authorizationUrl);
    }*/

    /*private OAuth20Service buildOAuthService(String clientId, String clientSecret) {
        // The callback must match Site-Url in the Facebook app settings
        String secretState = "secret" + new Random().nextInt(999_999);
        return new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .state(secretState)
                .callback("http://www.example.com/oauth_callback/")
                .build(FacebookApi.instance());
    }*/
}
