package org.homework.hibernatehw7.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"company", "customer", "developers"})
@ToString(exclude = {"company","developers", "customer"})// {"customer","company","developers"}
@Entity
@Table(name = "projects")
public class Project implements BaseModel<Long> {

    private static final long serialVersionUID = 2028374651928374654L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "cost", length = 10)
    private Long cost;

    @Column(name = "first_date")
    private String firstDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Developer> developers;
}
