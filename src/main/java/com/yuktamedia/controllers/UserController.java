package com.yuktamedia.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuktamedia.model.Session;
import com.yuktamedia.model.User;
import com.yuktamedia.security.JWTTokenCheck;
import com.yuktamedia.service.SessionService;
import com.yuktamedia.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @ApiOperation(value = "Login user with username and Password", nickname = "get_login")
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public HashMap<String, Object> getLogin(
            @RequestBody JsonNode payload,
            @RequestHeader(value = "referer", required = false) String referer,
            @RequestHeader(value="Authorization", required = false) String token,
            HttpServletResponse response
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map params = mapper.convertValue(payload, Map.class);

        boolean isReqFromSwagger = referer.contains("swagger-ui");
        boolean isAuthorized = false;

        if (token != null && token.startsWith("Bearer ")) {
            isAuthorized = JWTTokenCheck.tokenCheck(token, payload);
        }

        String email = params.get("email").toString();
        String password = params.get("password").toString();

        if (isAuthorized || isReqFromSwagger) {
            User dbUser = userService.findUserByEmail(email);
            String dbUserPassword = dbUser.getPassword();
            int dbUserStatus = dbUser.getActive();
            UUID uuid = UUID.randomUUID();
            String sessionId = uuid.toString();

            if (dbUserStatus != 0 && bCryptPasswordEncoder.matches(password, dbUserPassword)) {
                logger.info("User is active and authenticated");

                Session session = new Session();
                session.setSessionId(sessionId);
                session.setUserId(dbUser.getId());
                sessionService.save(session);
                response.setHeader("jSessionId", sessionId);
                HashMap<String, Object> map = new HashMap<>();
                map.put("status", true);
                map.put("message", "Login successful");
                return map;
            } else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("status", false);
                map.put("error", "Authorization failed");
                return map;
            }
        }
        throw new RuntimeException("Authorization failed");
    }
}
