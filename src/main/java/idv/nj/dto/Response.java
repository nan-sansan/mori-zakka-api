package idv.nj.dto;

import lombok.Data;

@Data
public class Response<T> {
    private Boolean success;
    private String errorMsg;
    private T content;
    private Long total;

    public static <T> Response<T> ok(T content) {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        response.setContent(content);
        return response;
    }

    public static <T> Response<T> fail(String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
