/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oauthclienttest;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author Roberto
 */
public class VSOnlineApi extends DefaultApi20 {

    @Override
    public String getAccessTokenEndpoint() {
        return "https://app.vssps.visualstudio.com/oauth2/token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oac) {
        return String.format("https://app.vssps.visualstudio.com/oauth2/authorize?mkt=es&client_id=%s&response_type=Assertion&state=sample&scope=vso.profile&redirect_uri=%s", 
                oac.getApiKey(), oac.getCallback());
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    
    @Override
    public OAuthService createService(OAuthConfig config) {
        return new VSOOauthService(this, config);
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new VSOTokenExtractor();
    }
}
