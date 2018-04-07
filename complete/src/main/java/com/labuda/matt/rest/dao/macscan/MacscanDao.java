package com.labuda.matt.rest.dao.macscan;

import com.labuda.matt.iface.ICanLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by mateu on 04/04/2018.
 */
@Repository
public class MacscanDao implements ICanLog {

    private final String SQL_DAILY = "select ts, owner from v_mapscan_home where ts >= :date and ts < :nextDate order by ts";

    @Autowired
    private DataSource dataSource;

    public Map<Timestamp, String[]> getDailyData(Date date) {
        _log().debug("Started querying database for daily data for date {}, at {}",date.toString(),new Date().toString());
        Map<String,Object> params = new HashMap<>();
        params.put("date",date);
        params.put("nextDate",new Date(date.toInstant().plus(1, ChronoUnit.DAYS).toEpochMilli()));
        JdbcTemplate t = new JdbcTemplate(dataSource);
        t.setFetchSize(3601);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(t);
        Map<Timestamp,String[]> result = template.query(SQL_DAILY, params, new ResultSetExtractor<Map<Timestamp, String[]>>() {
            @Override
            public Map<Timestamp, String[]> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<Timestamp,String[]> result = new LinkedHashMap<>();
                Timestamp ts = null;
                Set<String> valueList = null;
                while(resultSet.next()) {

                    if(!resultSet.getTimestamp("TS").equals(ts) || valueList==null ){
                        if(ts!=null){
                            result.put(ts,valueList.toArray(new String[0]));
                        }
                        valueList = new HashSet<>();
                    }

                    ts = resultSet.getTimestamp("TS");
                    valueList.add(resultSet.getString("OWNER"));
                }

                if(valueList!=null && !valueList.isEmpty() && !result.containsKey(ts))
                result.put(ts,valueList.toArray(new String[0]));
                    return result;
                }
        });
        return result;
    }

    public String[] getCurrentUsers() {
        _log().debug("Started querying database for current users, at {}",new Date().toString());
        LocalDateTime now = LocalDateTime.now().minus(15,ChronoUnit.MINUTES);
        Map<Timestamp,String[]> result = getDailyData(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        return result.values().stream().flatMap(x->Arrays.stream(x)).collect(Collectors.toList()).toArray(new String[0]);
    }
}
