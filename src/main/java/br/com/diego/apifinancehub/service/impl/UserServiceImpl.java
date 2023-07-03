package br.com.diego.apifinancehub.service.impl;

import br.com.diego.apifinancehub.dto.CreateDepositDto;
import br.com.diego.apifinancehub.dto.CreateUserDto;
import br.com.diego.apifinancehub.exception.AppException;
import br.com.diego.apifinancehub.model.User;
import br.com.diego.apifinancehub.repository.UserRepository;
import br.com.diego.apifinancehub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailAndCpf(CreateUserDto userData) {
        if (userRepository.existsUserByCpf(userData.getCpf())){
            throw  new AppException("Cpf already in use", HttpStatus.CONFLICT);
        }

        if (userRepository.existsUserByEmail(userData.getEmail())){
            throw  new AppException("Email already in use", HttpStatus.CONFLICT);
        }
    }

    public User createUser(final CreateUserDto userData) {

        checkEmailAndCpf(userData);

        final User newUser = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(newUser);
    }


    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User reatrieveUser(final long id)  {
        return userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
    }

    public User updateUser(final CreateUserDto userData, final long id) {

        checkEmailAndCpf(userData);

        final User userToBeUpdate =  userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

        userToBeUpdate.setName(userData.getName());
        userToBeUpdate.setCpf(userData.getCpf());
        userToBeUpdate.setEmail(userData.getEmail());
        userToBeUpdate.setPassword(userData.getPassword());
        userToBeUpdate.setType(userData.getType());

        return userRepository.save(userToBeUpdate);
    }

    public void deleteUser(final long id) {
        final User userToBeDelete =  userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        userRepository.delete(userToBeDelete);
    }

    public User createDeposit(final CreateDepositDto depositDate, final long id) {
        final User userToBeDeposit =  userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

        final float currentBalance = userToBeDeposit.getBalance();

        userToBeDeposit.setBalance(currentBalance + depositDate.getValue());

        return userRepository.save(userToBeDeposit);
    }
}
