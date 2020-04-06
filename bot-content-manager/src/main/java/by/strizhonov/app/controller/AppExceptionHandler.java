package by.strizhonov.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    private static final String CONSTRAINT_VIOLATIONS_DELIMITER = "; ";


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ErrorInfo handleEntityNotFoundException(final EntityNotFoundException e, final HttpServletRequest request) {
        LOGGER.error("Handling exception on the top-level", e);
        return new ErrorInfo.Builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();
    }


    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(EntityExistsException.class)
    @ResponseBody
    public ErrorInfo handleEntityExistsException(final EntityExistsException e, final HttpServletRequest request) {
        LOGGER.error("Handling exception on the top-level", e);
        return new ErrorInfo.Builder()
                .timestamp(LocalDateTime.now())
                .status(403)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorInfo handleConstraintViolationException(final ConstraintViolationException e, final HttpServletRequest request) {
        LOGGER.error("Handling exception on the top-level", e);
        return new ErrorInfo.Builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error(e.getClass().getSimpleName())
                .message(e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(CONSTRAINT_VIOLATIONS_DELIMITER)))
                .path(request.getRequestURL().toString())
                .build();
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorInfo handleHttpMessageNotReadableException(final HttpMessageNotReadableException e, final HttpServletRequest request) {
        LOGGER.error("Handling exception on the top-level", e);
        return new ErrorInfo.Builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleException(final Exception e, final HttpServletRequest request) {
        LOGGER.error("Handling exception on the top-level", e);
        return new ErrorInfo.Builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error(e.getClass().getSimpleName())
                .message(e.getLocalizedMessage())
                .path(request.getRequestURL().toString())
                .build();
    }


}
