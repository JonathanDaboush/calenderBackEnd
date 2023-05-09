package com.example.calenderBackEnd.User;

import com.example.calenderBackEnd.Event.Event;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique=true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Event> events;
}
