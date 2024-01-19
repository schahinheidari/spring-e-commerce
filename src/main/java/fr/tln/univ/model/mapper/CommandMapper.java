package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CommandMapper {
    public abstract Command mapCommandToCommandDto(Command command);
    public abstract Command mapCommandDtoToCommand(CommandDto commandDto);

}
