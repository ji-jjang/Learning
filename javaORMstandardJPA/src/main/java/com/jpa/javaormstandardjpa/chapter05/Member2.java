package com.jpa.javaormstandardjpa.chapter05;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Setter
public class Member2 {
    @Id
    private String id;
    private String username;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Member2(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }
}
