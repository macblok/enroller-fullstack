package com.company.enroller.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "participant")
public class Participant {

    @Id
    private String login;

    @Column
    @JsonProperty(access = Access.WRITE_ONLY) //hash hasla nie bedzie odsylany z powrotem do przegladarki uzytkownika rejestrujacego sie
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "meeting_participant", joinColumns = {
            @JoinColumn(name = "participant_login")}, inverseJoinColumns = {@JoinColumn(name = "meeting_id")})
    Set<Meeting> meetings = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
