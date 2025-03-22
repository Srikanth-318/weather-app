package com.example.weather.model;

import java.util.List;

public class WeatherResponse {

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    private Sys sys;

    public Sys  getSys(){
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }


    private List<Weather> weather;

    public List<Weather> getWeather(){
        return weather;
    }

    public void setWeather(List<Weather> weather){
        this.weather = weather;
    }


    private Main main;

    public Main getMain(){
        return main;
    }

    public void setMain(Main main){
        this.main = main;
    }
    
    private Wind wind;

    public Wind getWind(){
        return wind;
    } 

    public void setWind(Wind wind){
        this.wind = wind;
    }

    public static class Sys{
        private String country;

        public String getCountry(){
            return country;
        }

        public void SetCountry(String country){
            this.country = country;
        }
    }


    public static class Weather{
        private int id ;
        private String description;

        public String getDescription(){
            return description;
        }

        public void SetDescription(String description){
            this.description = description;
        }

        public int getId(){
            return id;
        }

        public void setid(int id){
            this.id = id;
        }
    }

    public static class Main{

        private double temp;
        private int humidity;

        public double getTemp(){
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
        public int getHumidity(){
            return humidity;
        }

        public void setHumidity(int humidity){
            this.humidity = humidity;
        }
    }

    public static class Wind{
        private double speed;

        public double getSpeed(){
            return speed;
        }

        public void setSpeed(double speed){
            this.speed = speed;
        }
    }

}