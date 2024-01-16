package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminMapper {
    public abstract Admin mapAdminDtoToAdmin(AdminDto adminDto);
    public abstract AdminDto mapAdminToAdminDto(Admin admin);

}
