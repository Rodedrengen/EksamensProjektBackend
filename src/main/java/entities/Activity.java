/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime exerciseDate;

    private String type;

    private int duration;

    private int distance;

    private String comment;
    
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weatherInfo_id", referencedColumnName = "id")
    private WeatherInfo weatherInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityInfo_id", referencedColumnName = "id")
    private CityInfo cityInfo;

    public Activity(LocalDateTime exerciseDate, String type, int duration, int distance, String comment, String userName) {
        this.exerciseDate = LocalDateTime.now();
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.comment = comment;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(LocalDateTime exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public Activity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
