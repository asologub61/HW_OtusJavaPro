package ru.otus.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq_gen")
    @SequenceGenerator(name = "phone_seq_gen", sequenceName = "phone_sequence", allocationSize = 1)
    private long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Phone(long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public Phone(Client client, String phoneNumber){
        this.client = client;
        this.phoneNumber = phoneNumber;
    }
}
