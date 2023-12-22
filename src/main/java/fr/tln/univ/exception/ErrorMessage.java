package fr.tln.univ.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
    private LocalDateTime timeStamp;
    private String message, description;
}
