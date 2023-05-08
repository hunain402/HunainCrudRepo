package org.carrental.dao;

import org.carrental.domain.User;
import org.carrental.mapper.IMapper;
import org.carrental.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends  BaseDao implements ICrud<User>{

    private final IMapper<User> userMapper = new UserMapper();



    @Override
    public void insert(User obj) {
    }
    @Override
    public List<User> getAll() {
        return null;
    }
    @Override
    public User GetById(Long id) {
        return null;
    }
    @Override
    public void Update(User obj, Long id) {
    }
    @Override
    public void DeleteById(Long id) {
    }

//                                           GET USERNAME AND PASSWORD OF USER
//                                           ADDITIONAL METHOD
    public User getUserByUsernameAndPassword(String username,String password){
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_USER_BY_USERNAME_AND_PASSWORD);
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();
            return userMapper.resultSetToListObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
