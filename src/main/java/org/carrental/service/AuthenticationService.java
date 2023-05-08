package org.carrental.service;
import org.carrental.dao.UserDao;
import org.carrental.domain.User;

public class AuthenticationService {

//                               THIS CLASS PROVIDE AUTHANTICATION SERVICE TO CLIENT TO CHECK USERNAME AND PASSWORD
    private final UserDao userDao = new UserDao();

        public Boolean checkLogin(String username,String password){
        User user =  userDao.getUserByUsernameAndPassword(username,password);
        if(user !=null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}


