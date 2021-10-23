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
import java.util.stream.Collectors;

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
        return professorService.getProfessor(id);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    ProfessorDto updateProfessor(@PathVariable String id, @RequestBody UpdateProfessorDto updateProfessorDto) {
        return professorService.updateProfessor(id, updateProfessorDto);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    ProfessorDto deleteProfessor(@PathVariable String id) {
        return professorService.deleteProfessor(id);
    }

/* TODO
    @ExceptionHandler(NotFoundException.class)
    protected void professorNotFoundException(NotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), e.getMessage());
    }*/
}
