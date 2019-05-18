package hr.java.web.plesa.controller;

import hr.java.web.plesa.domain.Expense;
import hr.java.web.plesa.domain.Users;
import hr.java.web.plesa.dto.ExpenseDto;
import hr.java.web.plesa.dto.UserDto;
import hr.java.web.plesa.repository.ExpenseRepository;
import hr.java.web.plesa.repository.UserRepository;
import hr.java.web.plesa.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/user", produces="application/json")
@CrossOrigin
public class UserRestController {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserRestController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping(consumes="application/json")
        public UserDto save(@RequestBody Users user) {
            var existByUsername = userRepository.isExist(user.getUsername());
            if(existByUsername){
                return null;
            }
            var newUser = userRepository.save(user);

            return modelMapper.map(newUser, UserDto.class);
        }


        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        @Secured("admin")
        public void delete(@PathVariable Long id) {
            var user = userRepository.findOne(id);
            if (user != null){
                userRepository.delete(user);
            }
        }
}
