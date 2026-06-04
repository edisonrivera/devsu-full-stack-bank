package ec.devsu.api.bank.infraestructure.out.persistence.cache.config;

import ec.devsu.api.bank.domain.enums.ConfigEnum;
import ec.devsu.api.bank.domain.exception.NotFoundDataException;
import ec.devsu.api.bank.infraestructure.out.persistence.repository.ConfigJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConfigCache {
    private final ConfigJpaRepository configJpaRepository;
    private final Map<ConfigEnum, String> configMap = new EnumMap<>(ConfigEnum.class);

    @PostConstruct
    private void init() {
        final String dailyAmount = this.configJpaRepository.getConfigValue(ConfigEnum.DAILY_AMOUNT.getValue())
                .orElseThrow(() -> new NotFoundDataException("Daily amount configuration not found"));

        this.configMap.put(ConfigEnum.DAILY_AMOUNT, dailyAmount);
    }

    public String getConfigValue(final ConfigEnum configEnum) {
        return this.configMap.get(configEnum);
    }
}
