/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Owner
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String reservationName;
    public Long getId() {
        return id;
    }
    public String getReservationName() {
        return reservationName;
    }
    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }
    public Reservation() {
    }
    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", reservationName=" + reservationName + '}';
    }
}