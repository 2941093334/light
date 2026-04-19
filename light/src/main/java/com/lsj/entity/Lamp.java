package com.lsj.entity;


public class Lamp {
    private Long id;
    private String brand;
    private String model;
    private Double power;
    private Integer colorTemperature;

    public Lamp() {
    }

    public Lamp(String brand, String model, Double power, Integer colorTemperature) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.colorTemperature = colorTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Integer getColorTemperature() {
        return colorTemperature;
    }

    public void setColorTemperature(Integer colorTemperature) {
        this.colorTemperature = colorTemperature;
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", power=" + power +
                ", colorTemperature=" + colorTemperature +
                '}';
    }
}
