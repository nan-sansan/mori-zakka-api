package idv.nj.entity;

import idv.nj.entity.enums.ImageMimeType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    @Column(name = "mime_type", nullable = false)
    @Convert(converter = ImageMimeType.DbConverter.class)
    private ImageMimeType mimeType;

    @Column(name = "remark", length = 255)
    private String remark;

    @Column(name = "data", nullable = false, length = 2 * 1024 * 1024) // 最大2MB
    private byte[] data;
}

