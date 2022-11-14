package jm.task.core.jdbc;

import com.mysql.cj.util.Util;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

       userService.createUsersTable();
////
//        userService.saveUser("Name1", "LastName1", (byte) 20);
//        userService.saveUser("Name2", "LastName2", (byte) 25);
//        userService.saveUser("Name3", "LastName3", (byte) 31);
//        userService.saveUser("Name4", "LastName4", (byte) 38);
//
//        userService.removeUserById(3);
//        userService.cleanUsersTable();
//        System.out.println(userService.getAllUsers());
//        userService.dropUsersTable();
    }
}
