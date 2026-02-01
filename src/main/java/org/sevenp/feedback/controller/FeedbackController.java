package org.sevenp.feedback.controller;

import org.sevenp.feedback.dto.FeedbackRequestDto;
import org.sevenp.feedback.dto.FeedbackResponseDto;
import org.sevenp.feedback.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@Valid @RequestBody FeedbackRequestDto request) {
        FeedbackResponseDto created = feedbackService.createFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<FeedbackResponseDto> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @PutMapping("/{id}/helpful")
    public ResponseEntity<FeedbackResponseDto> markHelpful(@PathVariable UUID id) {
        return feedbackService.markHelpful(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
