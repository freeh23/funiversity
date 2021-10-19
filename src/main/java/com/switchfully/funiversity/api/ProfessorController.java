package com.switchfully.funiversity.api;

import com.switchfully.funiversity.api.dto.CreateProfessorDto;
import com.switchfully.funiversity.api.dto.ProfessorDto;
import com.switchfully.funiversity.api.dto.UpdateProfessorDto;
import com.switchfully.funiversity.domain.Professor;
import com.switchfully.funiversity.domain.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ProfessorController {


    /*
    Edge cases:
        -What if, when update, only one variable is mentionned/changed in the RequestBody?

     */

    private final ProfessorRepository repository;

    @Autowired
    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ProfessorDto createProfessor(@RequestBody CreateProfessorDto createProfessorDto) {
        //map to Professor object
        Professor professor = new Professor(
                createProfessorDto.getFirstname(),
                createProfessorDto.getLastname()
        );
        //save in repo
        repository.save(professor);
        //return a Dto
        return mapToProfessorDto(professor);
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    Collection<ProfessorDto> getProfessors() {
        //get all professors from repo
        Collection<Professor> professors = repository.getAll();
        //map all those professors to ProfessorDto
        //return as collection of ProfessorDto's
        return professors.stream()
                .map(this::mapToProfessorDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfessorDto getProfessor(@PathVariable String id) {
        //get by id from repo
        Professor professor = repository.get(id);
        //map to dto
        //return dto
        return mapToProfessorDto(professor);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ProfessorDto updateProfessor(@PathVariable String id, @RequestBody UpdateProfessorDto updateProfessorDto) {
        //get professor from repo
        Professor professor = repository.get(id);
        //update professor from repo and save
        professor.setFirstname(updateProfessorDto.getFirstname());
        professor.setLastname(updateProfessorDto.getLastname());

        //map updated object to dto and return
        return mapToProfessorDto(professor);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfessorDto deleteProfessor(@PathVariable String id) {
        //get professor from repo and delete
        Professor professor = repository.delete(id);
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
