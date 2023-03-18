package com.todo.todobackend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepository {

    Logger logger = LoggerFactory.getLogger(NoteRepository.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public NoteEntity save(NoteEntity note) {
        entityManager.persist(note);
        entityManager.flush();
        return note;
    }

    public List<NoteEntity> findAll() {
        TypedQuery<NoteEntity> query = entityManager.createQuery("SELECT t FROM NoteEntity t", NoteEntity.class);
        return query.getResultList();
    }

    public List<NoteEntity> findAllByPoster(Long posterId) {
        TypedQuery<NoteEntity> query = entityManager.createQuery("SELECT t FROM NoteEntity t where poster = :posterId", NoteEntity.class);
        return query.setParameter("posterId", posterId).getResultList();
    }

    public NoteEntity findNote(Long id) {
        return entityManager.find(NoteEntity.class, id);
    }

    @Transactional
    public NoteEntity updateNote(NoteEntity note) {
        entityManager.merge(note);
        return note;
    }

    @Transactional
    public void deleteNote(Long id) {
        NoteEntity note = findNote(id);
        if (note != null) {
            entityManager.remove(note);
        }
    }

    @Transactional
    public void deleteAll() {
        try {
            entityManager.createQuery("delete from NoteEntity").executeUpdate();
        } catch (Exception e) {
            logger.warn("failed to remove notes, see error, {1}", e);
        }

    }
}
