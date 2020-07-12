package wmaclean;

import wmaclean.characters.CharacterHandler;
import wmaclean.characters.Player;
import wmaclean.gui.HUD;
import wmaclean.gui.KeyInput;
import wmaclean.gui.Textures;
import wmaclean.gui.Window;
import wmaclean.chunk.Coordinate;
import wmaclean.chunk.Direction;
import wmaclean.chunk.TileChunk;
import wmaclean.time.Clock;
import wmaclean.time.TimeOfDay;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game extends Canvas implements Runnable{

    private Thread thread;
    private boolean running = false;
    public static final int WIDTH = 800, HEIGHT = 600;
    public static final String TITLE = "EXPLORER";
    public static final Color backgroundColor = Color.BLACK;

    public float posX;
    public float posY;

    private final List<TileChunk> allChunks;
    private final List<TileChunk> visibleChunks;
    private final CharacterHandler characterHandler;
    private TimeOfDay timeOfDay;
    private final HUD hud;

    public Game(){

        this.posX = 0;
        this.posY = 0;

        this.timeOfDay = Clock.getTimeOfDay();
        this.allChunks = new LinkedList<>();
        this.visibleChunks = new LinkedList<>();
        this.characterHandler = new CharacterHandler(this);
        this.characterHandler.setCharacter(new Player(WIDTH / 2, HEIGHT / 2, this));
        this.addKeyListener(new KeyInput(this.characterHandler));
        this.allChunks.add(new TileChunk(0, 0, this));
        this.visibleChunks.add(allChunks.get(0));
        this.hud = new HUD();
    }

    public void begin(){
        new Window(WIDTH, HEIGHT, TITLE, this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        this.requestFocus();
        running = true;
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                System.out.println("Chunks: " + this.allChunks.size());
                System.out.println("Characters: " + this.characterHandler.getCharacterListSize());
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for(TileChunk chunk : this.visibleChunks){
            chunk.render(g);
        }

        this.characterHandler.render(g);

        this.hud.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        checkVisibleChunks();
        sortVisibleChunks();
        checkTimeOfDayChange();
        this.characterHandler.tick();
        Player player = this.characterHandler.getPlayer();
        this.posX -= player.getVelX();
        this.posY -= player.getVelY();
    }

    private void checkTimeOfDayChange() {
        if(Clock.getTimeOfDay() != this.timeOfDay){
            this.timeOfDay = Clock.getTimeOfDay();
            timeOfDayChanged();
        }
    }

    private void timeOfDayChanged() {
        this.characterHandler.timeOfDayChanged();
        Textures.timeChanged();
    }

    private void sortVisibleChunks() {
        // have to sort visible chunks by their y coord so that they render in the right order (top down)
        Collections.sort(this.visibleChunks);
    }

    public void checkVisibleChunks(){
        List<TileChunk> newChunks = new ArrayList<>();
        List<TileChunk> removeChunks = new ArrayList<>();

        // iterate through all visible chunks.
        for(TileChunk chunk : this.visibleChunks){

            // make sure chunk is visible
            if(!(chunk.isVisible())){
                removeChunks.add(chunk);
                continue;
            }

            // check if each corner is visible. If a corner is visible, make sure that the bordering chunks exist and
            // are set to visible

            boolean pointVisible = false;

            // top left corner
            if(pointVisible(chunk.corners[0])) {
                // top left corner visible
                pointVisible = true;
                checkUp(newChunks, chunk);
                checkLeft(newChunks, chunk);
            }

            // top right corner
            if(pointVisible(chunk.corners[1])){
                // top right corner visible
                pointVisible = true;
                checkUp(newChunks, chunk);
                checkRight(newChunks, chunk);
            }

            // bottom left corner
            if(pointVisible(chunk.corners[2])){

                pointVisible = true;

                // bottom left corner visible

                checkDown(newChunks, chunk);
                checkLeft(newChunks, chunk);
            }

            // bottom right corner
            if(pointVisible(chunk.corners[3])){

                pointVisible = true;
                // bottom right corner visible

                checkDown(newChunks, chunk);
                checkRight(newChunks, chunk);
            }

            if(!pointVisible){
                chunk.setVisible(false);
                removeChunks.add(chunk);
            }
        }

        // add all newly visible chunks
        this.visibleChunks.addAll(newChunks);
        this.visibleChunks.removeAll(removeChunks);
    }

    private void checkRight(List<TileChunk> newChunks, TileChunk chunk) {
        if(chunk.getRight() == null){
            // need to create a new chunk right
            TileChunk newChunk = new TileChunk(chunk.x + TileChunk.CHUNK_SIZE, chunk.y, this, true);
            addConnections(newChunk);
            newChunks.add(newChunk);
            this.allChunks.add(newChunk);
        }else if(!(chunk.getRight().isVisible())){
            chunk.getRight().setVisible(true);
            newChunks.add(chunk.getRight());
        }
    }

    private void checkDown(List<TileChunk> newChunks, TileChunk chunk) {
        if(chunk.getDown() == null){
            // need to create a new chunk above
            TileChunk newChunk = new TileChunk(chunk.x, chunk.y + TileChunk.CHUNK_SIZE, this, true);
            addConnections(newChunk);
            newChunks.add(newChunk);
            this.allChunks.add(newChunk);
        }else{
            if(!chunk.getDown().isVisible()){
                chunk.getDown().setVisible(true);
                newChunks.add(chunk.getDown());
            }
        }
    }

    private void checkUp(List<TileChunk> newChunks, TileChunk chunk) {
        if(chunk.getUp() == null){
            // need to create a new chunk above
            TileChunk newChunk = new TileChunk(chunk.x, chunk.y - TileChunk.CHUNK_SIZE, this, true);
            addConnections(newChunk);
            newChunks.add(newChunk);
            this.allChunks.add(newChunk);
        }else{
            if(!chunk.getUp().isVisible()){
                chunk.getUp().setVisible(true);
                newChunks.add(chunk.getUp());
            }
        }
    }

    private void checkLeft(List<TileChunk> newChunks, TileChunk chunk) {
        if(chunk.getLeft() == null){
            // need to create a new chunk left
            TileChunk newChunk = new TileChunk(chunk.x - TileChunk.CHUNK_SIZE, chunk.y, this, true);
            addConnections(newChunk);
            newChunks.add(newChunk);
            this.allChunks.add(newChunk);
        }else{
            if(!chunk.getLeft().isVisible()){
                chunk.getLeft().setVisible(true);
                newChunks.add(chunk.getLeft());
            }
        }
    }

    public boolean pointVisible(Coordinate coord){
        return between(-1 * this.posX, -1 * this.posX + WIDTH, coord.x)
                && between(-1 * this.posY, -1 * this.posY + HEIGHT, coord.y);
    }

    public static boolean between(float min, float max, float check){
        return check > min && check < max;
    }

    public List<TileChunk> getVisibleChunks() {
        return visibleChunks;
    }

    private void addConnections(TileChunk chunk){

        // left
        TileChunk chunkToTheLeft = findChunk(this.allChunks, chunk, Direction.LEFT);
        if(chunkToTheLeft != null){
            chunk.setLeft(chunkToTheLeft);
            chunkToTheLeft.setRight(chunk);
        }

        // right
        TileChunk chunkToTheRight = findChunk(this.allChunks, chunk, Direction.RIGHT);
        if(chunkToTheRight != null){
            chunk.setRight(chunkToTheRight);
            chunkToTheRight.setLeft(chunk);
        }

        // Up
        TileChunk chunkAbove = findChunk(this.allChunks, chunk, Direction.UP);
        if(chunkAbove != null){
            chunk.setUp(chunkAbove);
            chunkAbove.setDown(chunk);
        }

        // down
        TileChunk chunkBelow = findChunk(this.allChunks, chunk, Direction.DOWN);
        if(chunkBelow != null){
            chunk.setDown(chunkBelow);
            chunkBelow.setUp(chunk);
        }
    }

    public TileChunk findChunk(List<TileChunk> chunkList, TileChunk chunk, Direction direction){
        float xOffset = direction.xChunkOffset();
        float yOffset = direction.yChunkOffset();

        Coordinate searchCoord = new Coordinate((int)(chunk.x + xOffset), (int)(chunk.y + yOffset));

        for(TileChunk tileChunk : chunkList){
            if(tileChunk.corners[0].equals(searchCoord)){
                return tileChunk;
            }
        }

        return null;
    }
}
