package co.develhope.Unit_Test.controllers;

import co.develhope.Unit_Test.entities.User;
import co.develhope.Unit_Test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // (1) Crea un nuovo User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // (2) Ottieni la lista di tutti gli User
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // (3) Prendi uno User specifico
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    // (4) Aggiorna uno User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        });
    }

    // (5) Cancella uno User
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}

