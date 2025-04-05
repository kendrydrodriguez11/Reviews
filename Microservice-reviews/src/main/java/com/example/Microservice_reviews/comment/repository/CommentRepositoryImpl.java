package com.example.Microservice_reviews.comment.repository;

import com.example.Microservice_reviews.comment.model.CommentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addComment(CommentEntity comment) {
        entityManager.persist(comment);
    }

    @Override
    public void deleteComment(Long id) {
        CommentEntity comment = entityManager.find(CommentEntity.class, id);
        if (comment != null) {
            entityManager.remove(comment);
        } else {
            throw new EntityNotFoundException("Comment not found with ID " + id);
        }
    }


    @Override
    public List<CommentEntity> showAllComments(Long idMovie) {
        String query = "SELECT c FROM commentEntity c WHERE c.movie.id = :idMovie";
        return entityManager.createQuery(query, CommentEntity.class)
                .setParameter("idMovie", idMovie)
                .getResultList();
    }


    @Override
    public void UpdateComment(CommentEntity comment) {
        entityManager.merge(comment);
    }

    @Override
    public CommentEntity FindCommentById(Long id) {
        return entityManager.find(CommentEntity.class, id);
    }
}
