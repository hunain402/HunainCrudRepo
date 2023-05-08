package org.carrental.mapper;

import org.carrental.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements IMapper<User>{

    public static final String ID = "id";
    public static final String USERNAME = "user_name";
    public static final String PASSWORD = "pass";

    @Override
    public List<User> resultSetToList(ResultSet rs) throws SQLException {

        return null;
    }

    @Override
    public User resultSetToListObject (ResultSet rs) throws SQLException {
        if(rs.next()) {
            return User.builder()
                    .id(rs.getInt(ID))
                    .username(rs.getString(USERNAME))
                    .password(rs.getString(PASSWORD))
                    .build();
        }
        return null;
    }
}
