package me.gerald.goofy.listeners;

import me.gerald.goofy.entity.entities.PlayerEntity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseDragListener implements MouseMotionListener {
    final PlayerEntity player;

    public MouseDragListener(PlayerEntity thePlayer) {
        this.player = thePlayer;
    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.player.x = e.getX();
    }
}
