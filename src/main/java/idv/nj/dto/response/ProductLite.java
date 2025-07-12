package idv.nj.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public interface ProductLite {
    Long getId();

    String getName();

    String getCategory();

    Integer getPrice();

    Integer getQuantity();

    @Nullable
    @JsonIgnore
    String getRawImages();

    default List<String> getImages() {
        String rawImages = getRawImages();
        if (rawImages == null) {
            return List.of();
        }
        return Arrays.stream(getRawImages().split(","))
                .map(s -> s.split(":"))
                .filter(s -> s.length == 2)
                .sorted(Comparator.comparingLong(s -> Long.parseLong(s[0])))
                .map(s -> String.format("/images/%s", s[1]))
                .toList();
    }
}
