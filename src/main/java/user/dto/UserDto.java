package user.dto;

import common.business.dto.BaseDto;
import user.domain.ClientType;

public class UserDto extends BaseDto<Long> {
    private String firstName;
    private String lastName;
    private Integer passport;

    private ClientType clientType;

    public UserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getPassport() {
        return passport;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Id: ").append(id).append("; UserName: ").append(firstName).append("; UserSurname: ").append(lastName).toString();
    }

}
