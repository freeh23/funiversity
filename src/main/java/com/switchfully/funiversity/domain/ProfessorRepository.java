package com.switchfully.funiversity.domain;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class ProfessorRepository {

    private final HashMap<String, Professor> repository;

    public ProfessorRepository() {
        this.repository = new HashMap<>();
    }


    public Professor save(Professor professor) {
        return repository.put(professor.getId(), professor);
    }

    public Collection<Professor> getAll() {
        return repository.values();
    }

    public Professor get(String id) {
        return repository.get(id);
    }

    public Professor delete(String id) {
        return repository.remove(id);

    }
}
