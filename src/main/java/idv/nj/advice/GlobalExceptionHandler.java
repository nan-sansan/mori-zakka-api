package idv.nj.advice;

import idv.nj.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    // Bean Validation 驗證錯誤 (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Response.fail(String.join(", ", errorMessages)));
    }

    // Security 認證異常
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<Void>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.warn("訪問權限不足: {} - 請求路徑: {}", ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(Response.fail("您沒有權限執行此操作，請聯繫系統管理員"), HttpStatus.FORBIDDEN); // 403
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Response<Void>> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.warn("認證失敗: {} - 請求路徑: {}", ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(Response.fail("登入驗證失敗，請重新登入"), HttpStatus.UNAUTHORIZED); // 401
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Response<Void>> handleNoResourceFoundException() {
        return new ResponseEntity<>(Response.fail("請求的資源不存在"), HttpStatus.NOT_FOUND);  // 返回 404 狀態
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleException(Exception e, WebRequest request) {
        log.error("系統發生未預期的異常 - 請求路徑: {} - 異常信息: ",
                request.getDescription(false), e);

        return new ResponseEntity<>(Response.fail("系統發生錯誤，請稍後再試"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
