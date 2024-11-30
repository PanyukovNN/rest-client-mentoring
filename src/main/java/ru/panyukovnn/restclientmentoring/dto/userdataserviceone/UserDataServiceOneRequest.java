package ru.panyukovnn.restclientmentoring.dto.userdataserviceone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataServiceOneRequest {

    private UserDataServiceOneMeta meta;
    private UserDataServiceOneBody body;
}
