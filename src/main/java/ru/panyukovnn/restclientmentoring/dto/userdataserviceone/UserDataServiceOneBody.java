package ru.panyukovnn.restclientmentoring.dto.userdataserviceone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceOneBody {

    private String id;
    private String guid;
    private String phone;

}
