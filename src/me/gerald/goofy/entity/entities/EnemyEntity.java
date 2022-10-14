package me.gerald.goofy.entity.entities;

import me.gerald.goofy.entity.Entity;

public class EnemyEntity extends Entity {
    public boolean rendering = true;

    public EnemyEntity(int spawnX, int spawnY) {
        super(55, 20);
        this.x = spawnX;
        this.y = spawnY;
    }
}
