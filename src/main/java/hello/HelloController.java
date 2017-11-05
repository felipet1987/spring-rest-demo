package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;


@CrossOrigin
@RestController
public class HelloController {


    private UserRepository repository;


    @Autowired
    HelloController(UserRepository repository) {
        this.repository = repository;


    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> allUsers() {
        return this.repository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Object> subirImagen(@RequestBody User user,UriComponentsBuilder ucBuilder) throws JsonProcessingException {

        System.out.println("leyendo User " + user.getUserName());

        repository.save(user);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(headers,HttpStatus.CREATED);


    }


    @RequestMapping(value = "/users", method = RequestMethod.PUT, headers = "Accept=application/json")
    public User updateUser(@RequestBody User user) {

        User updatedUser = repository.findById(user.getId());
        updatedUser.setUserName(user.getUserName());
        return repository.save(updatedUser);


    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = repository.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        repository.delete(user);
        return new ResponseEntity<User>(HttpStatus.ACCEPTED);
    }







}

