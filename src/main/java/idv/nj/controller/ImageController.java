package idv.nj.controller;

import idv.nj.dto.Response;
import idv.nj.dto.request.ImageUpsert;
import idv.nj.entity.Image;
import idv.nj.entity.enums.ImageMimeType;
import idv.nj.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageRepo imageRepo;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Image image = imageRepo.findById(id).orElse(null);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        String contentDisposition = String.format("inline; filename=\"%s\"", image.getFileName());
        return ResponseEntity.ok()
                .header("Content-Disposition", contentDisposition)
                .contentType(MediaType.parseMediaType(image.getMimeType().getValue()))
                .contentLength(image.getData().length)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(image.getData());
    }

    @PostMapping
    public Response<String> saveImage(ImageUpsert req) {
        try {
            MultipartFile file = req.getFile();

            Image image = new Image();
            image.setFileName(file.getOriginalFilename());
            String contentType = file.getContentType();
            image.setMimeType(ImageMimeType.fromValue(contentType));
            image.setRemark(req.getRemark());
            image.setData(file.getBytes());

            Image saved = imageRepo.save(image);

            return Response.ok(saved.getId().toString());
        } catch (Exception e) {
            return Response.fail("新增圖片失敗");
        }
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteImageById(@PathVariable Long id) {
        imageRepo.deleteById(id);
        return Response.ok();
    }
}
