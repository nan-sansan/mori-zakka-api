package idv.nj.dto;

import lombok.Data;

import java.util.List;

@Data
public class Response<T> {
    private Boolean success;
    private String errorMsg;
    private T content;
    private Long total;

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> ok(T content) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }

    public static <T extends List<?>> Response<T> ok(T content, Long total) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setContent(content);
        response.setTotal(total);
        return response;
    }

    public static <T> Response<T> fail(String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
