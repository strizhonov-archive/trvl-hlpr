package by.strizhonov.app.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 4L;

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
