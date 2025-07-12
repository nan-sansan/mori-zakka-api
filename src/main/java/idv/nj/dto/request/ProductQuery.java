package idv.nj.dto.request;

import idv.nj.dto.BaseQuery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQuery extends BaseQuery {
    private String category;
}
