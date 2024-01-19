package fr.tln.univ.controller;

import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandMapper;
import fr.tln.univ.service.CommandServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandServiceImp commandServiceImp;
    private final CommandMapper commandMapper;

    @PostMapping("/save")
    public ResponseEntity<CommandDto> add(@Valid @RequestBody CommandDto commandDto
            , @RequestHeader("token") String token) {
        Command saveCommand = commandServiceImp.save(commandDto, token);
        return ResponseEntity.ok(commandMapper.mapCommandToCommandDto(saveCommand));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CommandDto> getById(@PathVariable Integer id) {
        Command command = commandServiceImp.getById(id);
        return ResponseEntity.ok(commandMapper.mapCommandToCommandDto(command));
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommandDto>> getAll() {
        return ResponseEntity.ok(commandMapper.listCommandToListCommandDto(commandServiceImp.getAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandDto> update(@Valid @RequestBody CommandDto commandDto
            , @RequestHeader("token") String token) {
        Command command = commandServiceImp.update(commandDto, token);
        return ResponseEntity.ok(commandMapper.mapCommandToCommandDto(command));
    }

    @GetMapping("/list/{date}")
    public ResponseEntity<List<CommandDto>> getAllByDate(@PathVariable LocalDate date) {
        List<Command> commandList = commandServiceImp.getAllByDate(date);
        return ResponseEntity.ok(commandMapper.listCommandToListCommandDto(commandList));
    }
}
