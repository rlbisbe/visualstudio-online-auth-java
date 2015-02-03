/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oauthclienttest;

import java.util.Map;
import org.json.simple.JSONValue;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.model.Token;

/**
 *
 * @author Roberto
 */
public class VSOTokenExtractor implements AccessTokenExtractor {

    private static final String EMPTY_SECRET = "";
    
    @Override
    public Token extract(String string) {
        
        Map json = (Map) JSONValue.parse(string);
        String token = (String) json.get("access_token");
        return new Token(token, EMPTY_SECRET);
    }
}
