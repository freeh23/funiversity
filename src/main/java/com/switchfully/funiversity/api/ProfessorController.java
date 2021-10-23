package com.switchfully.funiversity.api;

import com.switchfully.funiversity.api.dto.CreateProfessorDto;
import com.switchfully.funiversity.api.dto.ProfessorDto;
import com.switchfully.funiversity.api.dto.UpdateProfessorDto;
import com.switchfully.funiversity.domain.ProfessorRepository;
import com.switchfully.funiversity.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {


    /* TODO
    Edge cases:
        -What if, when update, only one variable is mentionned/changed in the RequestBody?

    Exceptions:
        -Professor (byId) not found! in get, put and delete

     */

    private final ProfessorRepository repository;
    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorRepository repository, ProfessorService professorService) {
        this.repository = repository;
        this.professorService = professorService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ProfessorDto createProfessor(@RequestBody CreateProfessorDto createProfessorDto) {
        //Exception: wrong input -> sending a JSON without one or no parameters is possible and results in a null String variable present in our object. Should not result in the creation of an object in the repo!!!
        //add logger to exception handler
        return professorService.createProfessor(createProfessorDto);
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    Collection<ProfessorDto> getProfessors() {
        return professorService.getProfessors();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfessorDto getProfessor(@PathVariable String id) {
        //Exception: entering a non-valid id results in a Bad request message. Implement Exception so that we would have a message with it.
        //add logger to exception handler
        return professorService.getProfessor(id);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ProfessorDto updateProfessor(@PathVariable String id, @RequestBody UpdateProfessorDto updateProfessorDto) {
        //Exception:
            // Same as in get professor by id
            // If we pass only one field, the other field is initialized as null, even if there was a correct String before!
        //add logger to exception handler
        return professorService.updateProfessor(id, updateProfessorDto);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfessorDto deleteProfessor(@PathVariable String id) {
        //same Exception as in get professor! No other problem expected.
        //add logger to exception handler
        return professorService.deleteProfessor(id);
    }

/* TODO
    @ExceptionHandler(NotFoundException.class)
    protected void professorNotFoundException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), e.getMessage());
    }*/
}
