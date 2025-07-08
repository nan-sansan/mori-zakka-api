package idv.nj.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum ImageMimeType {
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png");

    private final String value;

    ImageMimeType(String value) {
        this.value = value;
    }

    public static ImageMimeType fromValue(String value) {
        for (ImageMimeType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("不支援的圖片類型: " + value);
    }

    @Converter(autoApply = true)
    public static class DbConverter implements AttributeConverter<ImageMimeType, String>{
        @Override
        public String convertToDatabaseColumn(ImageMimeType attr) {
            if (attr == null) return null;
            return attr.getValue();
        }

        @Override
        public ImageMimeType convertToEntityAttribute(String dbValue) {
            if (dbValue == null) return null;
            return ImageMimeType.fromValue(dbValue);
        }
    }
}