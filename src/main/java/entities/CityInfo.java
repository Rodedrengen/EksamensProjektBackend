/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author simon
 */
@Entity
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    private double longditue;
    private double laditute;
    
    private String municipality;
    
    private int population;

    public CityInfo(String name, double longditue, double laditute, String municipality, int population) {
        this.name = name;
        this.longditue = longditue;
        this.laditute = laditute;
        this.municipality = municipality;
        this.population = population;
    }

    public CityInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongditue() {
        return longditue;
    }

    public void setLongditue(double longditue) {
        this.longditue = longditue;
    }

    public double getLaditute() {
        return laditute;
    }

    public void setLaditute(double laditute) {
        this.laditute = laditute;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
