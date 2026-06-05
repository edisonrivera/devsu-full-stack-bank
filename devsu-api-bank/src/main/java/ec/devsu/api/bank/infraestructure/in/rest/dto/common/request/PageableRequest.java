package ec.devsu.api.bank.infraestructure.in.rest.dto.common.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@Getter
@Setter
public class PageableRequest implements Serializable {
    @NotNull(message = "pageNo is required")
    @PositiveOrZero(message = "pageNo must be greater than -1")
    private int pageNo;

    @NotNull(message = "pageSize is required")
    @Positive(message = "pageSize must be greater than 0")
    @Max(value = 50, message = "pageSize must be less than or equal to {value}")
    private int pageSize;

    public Pageable pageable() {
        return PageRequest.of(pageNo, pageSize);
    }

    public long offset() {
        return this.pageable().getOffset();
    }
}
