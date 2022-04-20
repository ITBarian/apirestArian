package cat.itb.m09.apirestarian.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersDTO {
    private String username;
    private String rol;
}