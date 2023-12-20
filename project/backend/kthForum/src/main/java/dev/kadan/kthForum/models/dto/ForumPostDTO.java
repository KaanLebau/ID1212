package dev.kadan.kthForum.models.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record ForumPostDTO(
        Integer authorId,
        String authorName,
        String title,
        String content,
       LocalDate created,
        LocalDate updated

) {
}
