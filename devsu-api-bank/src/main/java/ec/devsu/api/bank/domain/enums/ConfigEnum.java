package ec.devsu.api.bank.domain.enums;

import lombok.Getter;

@Getter
public enum ConfigEnum {
    DAILY_AMOUNT("limit.daily.debit");

    private final String value;

    ConfigEnum(final String value) {
        this.value = value;
    }
}
