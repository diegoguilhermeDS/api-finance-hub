package br.com.diego.apifinancehub.service;

import br.com.diego.apifinancehub.dto.CreateDepositDto;
import br.com.diego.apifinancehub.dto.CreateUserDto;
import br.com.diego.apifinancehub.model.User;

import java.util.List;

public interface UserService {

    User createUser(final CreateUserDto userData);

    List<User> readUsers();

    User reatrieveUser(final long id);

    User updateUser(final CreateUserDto userData, final long id);

    void deleteUser(final long id);

    User createDeposit(final CreateDepositDto depositDate, final long id);
}
