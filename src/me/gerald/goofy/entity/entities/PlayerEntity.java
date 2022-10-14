package me.gerald.goofy.entity.entities;

import me.gerald.goofy.entity.Entity;

public class PlayerEntity extends Entity {
    public PlayerEntity() {
        super(100, 20);
        this.x = 375 - (getWidth() / 2f);
        this.y = 450;
    }
}
