package com.bitboxer2.SpringBootAPI.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  implements IUserService{

    @Autowired
    private IUserRepository repoUser;
    @Override
    public void createUser(User user) {
        repoUser.save(user);
    }

    @Override
    public List<User> getUsers() {

        List<User> userList = repoUser.findAll();

        return userList;
    }

    @Override
    public void deleteUser(Long id) {
        repoUser.deleteById(id);
    }
}
