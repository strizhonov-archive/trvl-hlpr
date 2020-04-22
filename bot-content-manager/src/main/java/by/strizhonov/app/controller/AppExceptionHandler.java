package by.strizhonov.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);
    private static final String COMMON_MESSAGE = "Handling exception on the top-level";
    private static final String CONSTRAINT_VIOLATIONS_DELIMITER = "; ";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(final EntityNotFoundException e, final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorInfo> handleEntityExistsException(final EntityExistsException e, final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(403)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> handleConstraintViolationException(final ConstraintViolationException e, final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error(e.getClass().getSimpleName())
                .message(e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(CONSTRAINT_VIOLATIONS_DELIMITER)))
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e, final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorInfo>
    handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e,
                                                 final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(final Exception e, final HttpServletRequest request) {
        LOGGER.error(COMMON_MESSAGE, e);

        ErrorInfo responseInfo = ErrorInfo.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(responseInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
