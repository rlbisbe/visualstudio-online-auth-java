/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oauthclienttest;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.*;
import org.scribe.oauth.OAuth20ServiceImpl;

/**
 *
 * @author Roberto
 */
public class VSOOauthService extends OAuth20ServiceImpl {

    private final DefaultApi20 api;
    private final OAuthConfig config;
    
    public VSOOauthService(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
        
        this.api = api;
        this.config = config;
    }
    
    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier)
    {
      OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
      request.addBodyParameter(VSOConstants.CLIENT_ASSERTION_TYPE, "urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
      request.addBodyParameter(VSOConstants.GRANT_TYPE, "urn:ietf:params:oauth:grant-type:jwt-bearer");
      request.addBodyParameter(VSOConstants.CLIENT_ASSERTION, config.getApiSecret());
      request.addBodyParameter(VSOConstants.ASSERTION, verifier.getValue());
      request.addBodyParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
      request.addHeader("Content-type", "application/x-www-form-urlencoded");
      config.log(request.getCompleteUrl());
      Response response = request.send();
      return api.getAccessTokenExtractor().extract(response.getBody());
    }
}
