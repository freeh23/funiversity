package com.switchfully.funiversity.service;

import com.switchfully.funiversity.api.dto.CreateProfessorDto;
import com.switchfully.funiversity.api.dto.ProfessorDto;
import com.switchfully.funiversity.api.dto.UpdateProfessorDto;
import com.switchfully.funiversity.domain.Professor;
import com.switchfully.funiversity.domain.ProfessorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ProfessorService.class);

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.repository = professorRepository;
    }

    public ProfessorDto createProfessor(CreateProfessorDto createProfessorDto) {
        //map to Professor object
        Professor professor = new Professor(
                createProfessorDto.getFirstname(),
                createProfessorDto.getLastname()
        );
        //save in repo
        repository.save(professor);
        logger.info("Professor object succesfully added to the database.");
        //return a Dto
        return mapToProfessorDto(professor);
    }

    public Collection<ProfessorDto> getProfessors() {
        //get all professors from repo
        Collection<Professor> professors = repository.getAll();
        //map all those professors to ProfessorDto
        //return as collection of ProfessorDto's
        logger.info("Returning all professors from database.");
        return professors.stream()
                .map(this::mapToProfessorDto)
                .collect(Collectors.toList());
    }

    public ProfessorDto getProfessor(String id) {
        //TODO dubbelcheck
        try {
            //get by id from repo
            Professor professor = repository.get(id);
            //map to dto
            //return dto
            logger.info("Match found for given id in database.");
            return mapToProfessorDto(professor);
        } catch (NullPointerException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The provided id " + id + " does not exist in the database.", exception);
        }
    }

    public ProfessorDto updateProfessor(String id, UpdateProfessorDto updateProfessorDto) {
        //get professor from repo
        Professor professor = repository.get(id);
        logger.info("Match found for given id in database.");
        //update professor from repo and save
        professor.setFirstname(updateProfessorDto.getFirstname());
        professor.setLastname(updateProfessorDto.getLastname());
        logger.info("Professor with id:" + professor.getId() + " successfully updated.");

        //map updated object to dto and return
        return mapToProfessorDto(professor);
    }

    public ProfessorDto deleteProfessor(String id) {
        //get professor from repo and delete
        Professor professor = repository.delete(id);
        logger.info("Match found for given id in database.");
        logger.info("Deleted: " + professor.toString());
        //return dto
        return mapToProfessorDto(professor);
    }


    private ProfessorDto mapToProfessorDto(Professor professor) {
        return new ProfessorDto()
                .setId(professor.getId())
                .setFirstname(professor.getFirstname())
                .setLastname(professor.getLastname());
    }
}
