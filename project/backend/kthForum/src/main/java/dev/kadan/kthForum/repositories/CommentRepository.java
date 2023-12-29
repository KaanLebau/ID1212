package dev.kadan.kthForum.repositories;

import dev.kadan.kthForum.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {



}
