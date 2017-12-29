package com.yuktamedia.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.yuktamedia.security.SecurityConstants.SECRET;
import static com.yuktamedia.security.SecurityConstants.TOKEN_PREFIX;

public class JWTTokenCheck {

    private static final Logger logger = Logger.getLogger(JWTTokenCheck.class);

    public static boolean tokenCheck(String token, JsonNode jnode) {
        token = token.replace(TOKEN_PREFIX, "");
        logger.info("token is " + token);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(jnode, Map.class);
        logger.info("jsonstring " + result);
        try {
            String claims = Jwts.builder()
                    .setClaims(result)
                    .signWith(SignatureAlgorithm.HS256, SECRET.getBytes("UTF-8"))
                    .compact();

            logger.info("created token is " + claims);
            if (claims.equals(token)) {
                return true;
            }
        } catch (SignatureException e) {
            logger.info("error" + e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
