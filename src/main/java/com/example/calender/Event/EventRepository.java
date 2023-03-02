package com.example.calender.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {
    //for Geo comparison to see if target location is within Area.
    @Query(value =  " SELECT * "
            +  " FROM Events  "
            +  " WHERE date= :date "
            , nativeQuery = true)
    List<Event> findByDate(Date date);
}
