package idv.nj.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public abstract class BaseQuery {
    private int page = 0;
    private int size = 10;
    private String orderBy;
    private boolean desc = true;

    @JsonIgnore
    public Pageable toPageable() {
        if (orderBy == null) return PageRequest.of(page, size);

        return PageRequest.of(
                page,
                size,
                desc ? Sort.by(orderBy).descending() : Sort.by(orderBy).ascending()
        );
    }
}
