package au.edu.uq.repositories.impl;

import au.edu.uq.repositories.StaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDaoImpl implements StaffDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public String getEmailByName(String name) {
        String email = template.queryForObject("select email from staff where name = ?", new Object[]{name}, String.class);
        return email;
    }

    @Override
    public String getNameById(String id){
        String name = template.queryForObject("select name from staff where id = ?", new Object[]{id}, String.class);
        return name;
    }
}
