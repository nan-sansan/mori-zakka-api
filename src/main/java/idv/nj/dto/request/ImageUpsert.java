package idv.nj.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUpsert {
    private String remark;

    private MultipartFile file;
}
