package com.crud.serverside.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "skill_description")
    private String skillDescription;

    @ManyToMany
    @JoinTable(
            name = "employee_skill",
            joinColumns = {@JoinColumn(name = "skill_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private List<Employee> skilledEmployees;

    public Skill(){

    }

    public Skill(long id, String skillName, String skillDescription) {
        this.id = id;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.skilledEmployees = new ArrayList<>();
    }

    public Skill(long id, String skillName, String skillDescription, List<Employee> skilledEmployees) {
        this.id = id;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
        this.skilledEmployees = skilledEmployees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public List<Employee> getSkilledEmployees() {
        return skilledEmployees;
    }

    public void setSkilledEmployees(List<Employee> skilledEmployees) {
        this.skilledEmployees = skilledEmployees;
    }

    public boolean assignSkilledEmployee(Employee employee){
        if (skilledEmployees.stream().anyMatch(o -> o.getId() == employee.getId()))
            return false;

        return this.skilledEmployees.add(employee);
    }

    public boolean removeSkilledEmployee(Employee employee){
        if (skilledEmployees.stream().anyMatch(o ->  o.getId() == employee.getId()))
            return this.skilledEmployees.remove(employee);

        return false;
    }
}
