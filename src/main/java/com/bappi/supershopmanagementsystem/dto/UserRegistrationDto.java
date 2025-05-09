package com.bappi.supershopmanagementsystem.dto;

import com.google.protobuf.Message;
import com.mysql.cj.protocol.x.XMessage;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;
    private String email;
    private String username;
    private String password;
}
