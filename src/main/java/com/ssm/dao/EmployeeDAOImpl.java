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

import com.ssm.pojo.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO
{

    private JdbcTemplate jdbcTemplate;
    // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Saving a new Employee
    public void saveEmployee(Employee employee)
    {
        String sql = "insert into emp values(?,?,NULL,NULL,NULL,NULL,NULL,NULL)";
        System.out.println("dao called");
        jdbcTemplate.update(sql, new Object[]
        { employee.getId(), employee.getName() });
    }

    // Getting a particular Employee
    public Employee getEmployeeById(int id)
    {
        String sql = "select * from emp where empno=?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]
        { id }, new RowMapper<Employee>()
        {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                return employee;
            }
        });
        return employee;
    }

    // Getting all the Employees
    public List<Employee> getAllEmployees()
    {
        String sql = "select * from emp";

        List<Employee> employeeList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>()
        {
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<Employee> list = new ArrayList<Employee>();
                while (rs.next())
                {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    list.add(employee);
                }
                return list;
            }

        });
        return employeeList;
    }

    // Updating a particular Employee
    public void updateEmployee(Employee employee)
    {
    }

    // Deletion of a particular Employee
    public void deleteEmployee(int id)
    {
    }
}
