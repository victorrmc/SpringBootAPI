package com.bitboxer2.SpringBootAPI.User;


import java.util.List;

public interface IUserService {
    public void createUser(User supplier);

    public List<User> getUsers();
    public void deleteUser(Long id);

}
