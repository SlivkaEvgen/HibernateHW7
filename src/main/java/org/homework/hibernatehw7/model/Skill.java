package org.homework.hibernatehw7.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "developers")
@ToString(exclude = "developers")
@Entity
@Table(name = "skills")
public class Skill implements BaseModel<Long> {

    private static final long serialVersionUID = 2028544651928374654L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "level", length = 30)
    private String level;

    @Column(name = "activity", length = 30)
    private String activity;

    @ManyToMany(mappedBy = "skills", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Developer> developers;
}
