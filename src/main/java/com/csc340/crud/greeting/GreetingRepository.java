package com.csc340.crud.greeting;

import com.csc340.crud.greeting.Greeting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sunny Ntini
 */
@Repository
public class GreetingRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public List<Greeting> getAllGreetings() {
        String query = "select id, message,recipient from greeting";
        return template.query(query,
                (result, rowNum)
                -> new Greeting(result.getLong("id"), result.getString("message"), result.getString("recipient")));
    }

    public Greeting getGreetingById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "select * from greeting where id=:id";
        return template.queryForObject(query, namedParameters, BeanPropertyRowMapper.newInstance(Greeting.class));
    }

    public int addGreeting(String message, String recipient) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("message", message);
        paramMap.put("recipient", recipient);
        String query = "INSERT INTO greeting(message,recipient) VALUES(:message, :recipient)";
        return template.update(query, paramMap);
    }
}
