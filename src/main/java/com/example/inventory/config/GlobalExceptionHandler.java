package com.example.inventory.config;

import com.example.inventory.payload.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
        implements AuthenticationEntryPoint, AccessDeniedHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse err = new ErrorResponse("PR40001", msg);
        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponse err = new ErrorResponse("PR40001", ex.getMessage());
        return ResponseEntity.status(400).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {
        ErrorResponse err = new ErrorResponse("PR40401",
                ex.getMessage() == null ? "Resource not found" : ex.getMessage());
        return ResponseEntity.status(404).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        ErrorResponse err = new ErrorResponse("PR50001", "Server error");
        return ResponseEntity.status(500).body(err);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        ErrorResponse err = new ErrorResponse("PR40101", "Authentication error");
        writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, err);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ErrorResponse err = new ErrorResponse("PR40301", "Not authorized");
        writeJson(response, HttpServletResponse.SC_FORBIDDEN, err);
    }

    private void writeJson(HttpServletResponse response, int status, ErrorResponse err) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), err);
    }
}
