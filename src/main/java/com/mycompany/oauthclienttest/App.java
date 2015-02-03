/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oauthclienttest;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.*;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import static spark.Spark.*;
import spark.SparkBase;

/**
 *
 * @author Roberto
 */
public class App {

    private static final Token EMPTY_TOKEN = null;
    
    public static void main(String[] args) {
   
        SparkBase.setSecure("C:\\Users\\Roberto\\Documents\\NetBeansProjects\\OauthClientTest\\keystore.jks", "password", null, null);
        
        OAuthService service = new ServiceBuilder()
                           .provider(VSOnlineApi.class)
                           .apiKey("4B287A91-61CB-4AA4-A20A-7D3257E72195")
                           .apiSecret("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Im9PdmN6NU1fN3AtSGpJS2xGWHo5M3VfVjBabyJ9.eyJjaWQiOiI0YjI4N2E5MS02MWNiLTRhYTQtYTIwYS03ZDMyNTdlNzIxOTUiLCJjc2kiOiJkMDJjZTdjMi01NTY4LTQwMTEtYWI3MS04YTFjNzAxOTg3ODIiLCJuYW1laWQiOiIzMzljYmQxYi1iNmRkLTQzOGUtODE1OC0xODIzMjM4NTU3N2EiLCJpc3MiOiJhcHAudnNzcHMudmlzdWFsc3R1ZGlvLmNvbSIsImF1ZCI6ImFwcC52c3Nwcy52aXN1YWxzdHVkaW8uY29tIiwibmJmIjoxNDIyOTg4ODYxLCJleHAiOjE0NTQ1MjQ4NjF9.3yByavT6QAXcgw2l4QjxGbuXwgcK7qaZS-dOBIpnlUQ7oAOaW9guHGoGacTp1Rob_E3B4LC4XsCdwqibswtiHclw4xtBoYBOBWlIvtGtr4XbdA7BtsFXH-JUdNYhyX3jeUMQ6fupE2FgeAQzLr92e7rLZ6APTYZWYhEEp1LQQxwGcG_CFyQxPHBBvqDaKSz10JcGjHn26QTjAiGDc3iZF4DssDxo6q8kMfZjDFuovM54e5lSJpN1-bLm-kxcp67lNd9P79_eccTrEnMLhjWrgppk9zBMCXkHIcHfIWmA7hotfotIhGTZXaYQtji93YqG3C3ZHrRB850EJyFBDVPI7Q")
                           .callback("https://rlbisbe.dev:4567/callback")
                           .signatureType(SignatureType.QueryString)
                           .debugStream(System.out)
                           .debug()
                           .build();
        
        
        get("/index", (req, res) -> "Hello World");
        
        get("/auth", (req, res) -> {
            res.redirect(service.getAuthorizationUrl(EMPTY_TOKEN));
            return null;
        });
        
         get("/callback", (req, res) -> {

             String code = req.queryParams("code");
             Verifier verifier = new Verifier(code);
            Token token = service.getAccessToken(EMPTY_TOKEN, verifier);
                return token;
        });
    }
}
