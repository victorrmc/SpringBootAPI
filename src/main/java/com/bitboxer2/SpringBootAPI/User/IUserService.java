package com.bitboxer2.SpringBootAPI.User;


import com.bitboxer2.SpringBootAPI.model.Supplier;

import java.util.List;

public interface IUserService {
    public void createUser(User supplier);

    public List<User> getUsers();
    public void deleteUser(Long id);
    public User findUser(Long id);

}
