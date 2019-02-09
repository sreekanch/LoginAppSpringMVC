package com.ssm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssm.pojo.User;

@Repository
public class UserDAOImpl implements UserDAO
{

    private JdbcTemplate jdbcTemplate;
    // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get user by anme
    public User getUser(String name)
    {
        String sql = "select * from users where name=?";
        User user = (User) jdbcTemplate.queryForObject(sql, new Object[]
        { name }, new RowMapper<User>()
        {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                User user = new User();
                user.setName(rs.getString(1));
                user.setPassword(rs.getString(2));
                return user;
            }
        });
        return user;
    }


}
