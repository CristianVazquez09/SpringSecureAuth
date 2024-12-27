package com.wolfpack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ResetMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String random;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiration;


    public void setExpiration(int minutes){
        LocalDateTime today = LocalDateTime.now();

        this.expiration = today.plusMinutes(minutes);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(this.expiration);
    }

}
