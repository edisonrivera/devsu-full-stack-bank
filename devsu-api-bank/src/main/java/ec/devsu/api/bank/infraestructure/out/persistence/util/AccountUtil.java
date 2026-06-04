package ec.devsu.api.bank.infraestructure.out.persistence.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@AllArgsConstructor
public class AccountUtil {
    private final SecureRandom secureRandom;

    private static final int ACCOUNT_LENGTH = 50;

    public String generateAccountNumber() {
        var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
        var sb = new StringBuilder(ACCOUNT_LENGTH);

        for (int i = 0; i < ACCOUNT_LENGTH; i++) {
            sb.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
