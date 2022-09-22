package com.example.cartService.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

/**
 * purpose: Creating a token class
 * @author Anuj Solanki
 */

@Component
public class Token {

    public final String Token_Secret="LoginToken";

    /**
     * purpose:creating token for access
     * @param id
     * @return
     */
    public String createToken(Long id){
        try {
            Algorithm algorithm=Algorithm.HMAC256(Token_Secret);

            String token = JWT.create().withClaim("userId",id).sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            exception.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * purpose:decoding token
     * @param token
     * @return
     */
    public Long decodeToken(String token){
        Long userid;
        Verification verification=null;
        try {
            verification=JWT.require(Algorithm.HMAC256(Token_Secret));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        JWTVerifier jwtVerifier = verification.build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);
        Claim claim =decodedJWT.getClaim("userId");
        userid=claim.asLong();
        return userid;

    }

}
