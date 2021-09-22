package org.homework.hibernatehw7.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developers_projects")
public class DevelopersProjects implements BaseModel<Long> {

    private static final long serialVersionUID = 2020974651988374656L;

    @Column(name = "developer_id",unique = true)
    private Long developerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id",unique = true)
    private Long projectId;

    @Override
    public Long getId() {
        return projectId;
    }
}
