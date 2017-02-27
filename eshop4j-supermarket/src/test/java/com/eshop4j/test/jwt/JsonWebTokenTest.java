package com.eshop4j.test.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
/**
 * JSON Web Token Test
 * @author Mignet
 * @since 2016-07-05 17:18:41
 *
 */
public class JsonWebTokenTest{

	@Test
	public void jwtTest() throws JOSEException, ParseException {

		// Generate random 256-bit (32-byte) shared secret
//		SecureRandom random = new SecureRandom();
		byte[] sharedSecret = StringUtils.getBytesUtf8("https://www.eshop4j.com");
//		random.nextBytes(sharedSecret);
		
//		SecureRandom random = new SecureRandom();
//		byte[] sharedSecret = new byte[32];
//		random.nextBytes(sharedSecret);

		// Create HMAC signer
		JWSSigner signer = new MACSigner(sharedSecret);

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet();
		claimsSet.setSubjectClaim("kermit");
		claimsSet.setIssuerClaim("https://www.eshop4j.com");
		claimsSet.setExpirationTimeClaim(System.currentTimeMillis()+60 * 1000);//60s

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		// Apply the HMAC protection
		signedJWT.sign(signer);

		// Serialize to compact form, produces something like
		// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
		String s = signedJWT.serialize();

		//-------------------------------------------------------------------------------------
		// On the consumer side, parse the JWS and verify its HMAC
		signedJWT = SignedJWT.parse(s);

		JWSVerifier verifier = new MACVerifier(sharedSecret);

		assertTrue(signedJWT.verify(verifier));

		System.out.println(signedJWT.getJWTClaimsSet().getSubjectClaim());
		// Retrieve / verify the JWT claims according to the app requirements
		assertEquals("kermit", signedJWT.getJWTClaimsSet().getSubjectClaim());
		assertEquals("https://www.eshop4j.com", signedJWT.getJWTClaimsSet().getIssuerClaim());
		assertTrue(System.currentTimeMillis()<signedJWT.getJWTClaimsSet().getExpirationTimeClaim());
	}
}
