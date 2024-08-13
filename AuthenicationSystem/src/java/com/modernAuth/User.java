package com.modernAuth;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "user")
public class User {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @Column(name = "added_date")
    @Temporal(TemporalType.DATE)
    Date date;
    
    @Id
    @Column(name = "email" , length = 100)
    String email;
    
    @Column(name = "secure_password" , length = 80)
    String password;
    
    @Column(name = "salt" , length = 70)
    String salt;
    
    public User(Date date , String email , String password , String salt){
        super();
        this.date = date;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }
    
    public User(){
        super();
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getSalt(){
        return this.salt;
    }
}
