package com.snhu.sslserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@RestController
public class SslServerController {

    @GetMapping("/hash")
    public String generateChecksum() {
        String uniqueData = "Andrew Torrez";  // This could be replaced with data received from the user
        return "<p>Data: " + uniqueData + "<br>SHA-256 Digest: " + getSHA256Checksum(uniqueData) + "</p>";
    }

    private String getSHA256Checksum(String uniqueData) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(uniqueData.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            return "SHA-256 algorithm not available";
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
