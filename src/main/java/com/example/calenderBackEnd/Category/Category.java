package com.example.calenderBackEnd.Category;

import com.example.calenderBackEnd.Event.Event;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Event> events;
}
