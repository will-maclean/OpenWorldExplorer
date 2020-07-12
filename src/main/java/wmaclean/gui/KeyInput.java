package wmaclean.gui;

import wmaclean.characters.CharacterHandler;
import wmaclean.characters.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final CharacterHandler handler;
    private final boolean[] keyDown = new boolean[4];

    public KeyInput(CharacterHandler handler){

        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        //key events for player 1

        Player player = this.handler.getPlayer();

        if(key == KeyEvent.VK_W){
            player.setVelY(-1 * player.getSpeed());
            keyDown[0] = true;
        }

        if(key == KeyEvent.VK_S){
            player.setVelY(player.getSpeed());
            keyDown[1] = true;
        }

        if(key == KeyEvent.VK_D){
            player.setVelX(player.getSpeed());
            keyDown[2] = true;
        }

        if(key == KeyEvent.VK_A){
            player.setVelX(-1 * player.getSpeed());
            keyDown[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        Player player = this.handler.getPlayer();

        if(key == KeyEvent.VK_W){
            keyDown[0] = false;
        }

        if(key == KeyEvent.VK_S){
            keyDown[1] = false;
        }

        if(key == KeyEvent.VK_D){
            keyDown[2] = false;
        }

        if(key == KeyEvent.VK_A){
            keyDown[3] = false;
        }

        //vertical movement
        if(!keyDown[0] && !keyDown[1]){
            player.setVelY(0);
        }

        //horizontal movement
        if(!keyDown[2] && !keyDown[3]){
            player.setVelX(0);
        }

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }
}
