package org.sevenp.feedback.dto;

import java.util.UUID;

public record FeedbackResponseDto(UUID id, String message, String author, Integer helpfulCount) {
}
