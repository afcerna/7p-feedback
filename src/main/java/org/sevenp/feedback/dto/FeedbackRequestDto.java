package org.sevenp.feedback.dto;

import jakarta.validation.constraints.NotBlank;

public record FeedbackRequestDto(
                @NotBlank(message = "message is required") String message,
                String author) {
}
