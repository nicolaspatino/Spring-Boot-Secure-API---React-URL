package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
@Service
public class UserServiceImpl
    implements UserService
{

    private List<User> users = new ArrayList<>();


    @Autowired
    public UserServiceImpl()
    {
    }

    @PostConstruct
    private void populateSampleData()
    {
        users.add( new User( "test@mail.com", "password", "Andres", "Perez" ) );
    }


    @Override
    public List<User> getUsers()
    {
        return users;
    }

    @Override
    public User getUser( Long id )
    {
        Optional<User> userFound = users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();

        return userFound.get();

    }

    @Override
    public User createUser( User user )
    {
        users.add(user);
        return user;
    }

    @Override
    public User findUserByEmail( String email )
    {
        Optional<User> userFound = users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
        return userFound.get();
    }

    @Override
    public User findUserByEmailAndPassword( String email, String password )
    {
        Optional<User> userFound = users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst();
        return userFound.get();
    }

}