package study.datajpa.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Member {

    @javax.persistence.Id
    private  Long Id;
    private String name

}
