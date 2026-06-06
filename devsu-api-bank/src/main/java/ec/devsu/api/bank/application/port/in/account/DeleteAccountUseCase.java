package ec.devsu.api.bank.application.port.in.account;

import java.util.UUID;

public interface DeleteAccountUseCase {
    void delete(UUID accountId);
}
