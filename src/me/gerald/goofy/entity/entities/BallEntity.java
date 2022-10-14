package me.gerald.goofy.entity.entities;

import me.gerald.goofy.entity.Entity;

public class BallEntity extends Entity {
    public float hVelocity;
    public float vVelocity = 0.5f;

    public BallEntity() {
        super(30, 30);
        this.x = 375 - (getWidth() / 2f);
        this.y = 410;
    }

    public void updateCoords() {
        this.x += hVelocity;
        this.y += vVelocity;
    }
}
