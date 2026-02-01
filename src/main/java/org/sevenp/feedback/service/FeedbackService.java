package org.sevenp.feedback.service;

import org.sevenp.feedback.dto.FeedbackRequestDto;
import org.sevenp.feedback.dto.FeedbackResponseDto;
import org.sevenp.feedback.entity.Feedback;
import org.sevenp.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackResponseDto createFeedback(FeedbackRequestDto request) {
        Feedback feedback = new Feedback(request.message(), request.author());
        Feedback saved = feedbackRepository.save(feedback);
        return toResponseDto(saved);
    }

    public List<FeedbackResponseDto> getAllFeedback() {
        return feedbackRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public Optional<FeedbackResponseDto> markHelpful(UUID id) {
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setHelpfulCount(feedback.getHelpfulCount() + 1);
                    Feedback saved = feedbackRepository.save(feedback);
                    return toResponseDto(saved);
                });
    }

    private FeedbackResponseDto toResponseDto(Feedback feedback) {
        return new FeedbackResponseDto(
                feedback.getId(),
                feedback.getMessage(),
                feedback.getAuthor(),
                feedback.getHelpfulCount()
        );
    }
}
