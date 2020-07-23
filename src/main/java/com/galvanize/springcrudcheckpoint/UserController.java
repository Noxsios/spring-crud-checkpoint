package com.galvanize.springcrudcheckpoint;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<User> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        return this.repository.save(user);
    }

    @GetMapping("/{id}")
    public User userByID(@PathVariable Long id) {
        Optional<User> found = this.repository.findById(id);
        return found.get();
    }

    @PatchMapping("/{id}")
    public User patch(@PathVariable Long id, @RequestBody User updates) {
        Optional<User> current = this.repository.findById(id);
        if (updates.getEmail() != null) {
            current.get().setEmail(updates.getEmail());
        }
        if (updates.getPassword() != null) {
            current.get().setPassword(updates.getPassword());
        }
        return this.repository.save(current.get());
    }

    @DeleteMapping("/{id}")
    public Map<String, Integer> grossMovies(@PathVariable Long id) {
        this.repository.deleteById(id);
        int count = 0;
        for (User us : this.repository.findAll()) {
            count++;
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("count", count);
        return result;
    }

    @PostMapping("/authenticate")
    public Authenticated auth(@RequestBody User auth) {
        boolean valid = this.repository.findByEmail(auth.getEmail()).getEmail().equals(auth.getEmail());
        Authenticated output;
        if (valid) {
            output = new Authenticated(true);
            System.out.println("valid");
            output.setUser(this.repository.findByEmail(auth.getEmail()));
        } else {
            output = new Authenticated(false);
        }
        return output;
    }
}