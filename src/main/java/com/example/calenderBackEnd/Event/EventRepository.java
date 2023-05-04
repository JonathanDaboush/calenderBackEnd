package com.example.calenderBackEnd.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM event  "
            +  " WHERE date_of_event = :key and user_id = :id "
            , nativeQuery = true)
    List<Event> findEventByDate(Date key, long id);
}