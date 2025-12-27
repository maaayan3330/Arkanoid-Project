package game;
import ball.Ball;
import listener.Block;
import sprites.Sprite;
import sprites.SpriteCollection;
import collision.Collidable;
import listener.Paddle;
import listener.ScoreIndicator;
import geometricShapes.Point;
import geometricShapes.Rectangle;


import java.util.ArrayList;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;


/**
 * @author Maayan Ifergan
 *
 * @version 2
 *
 * @since 10.01.2024
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle1;
    private Counter counterForBlocks = new Counter();
    private Counter counterForBalls = new Counter();
    private Counter score = new Counter();
    private final ScoreTrackingListener scoreTracking =
            new ScoreTrackingListener(score);
    private final ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    public static final int WIDTH_OF_PADDLE = 70;
    public static final int HIGHT_OF_PADDLE = 15;
    public static final int WIDTH_OF_BLOCK = 60;
    public static final int HIGHT_OF_BLOCK = 20;
    public static final double HIGHT_OF_BOARD = 600;
    public static final double WIDTH_OF_BOARD = 800;
    public static final int ROWS = 6;
    public static final int COLUMNS = 12;
    public static final double VX = 0;
    public static final double VY = -4;
    public static final int RADIUS = 5;
    public static final double START_X_BALL1 = 370;
    public static final double START_Y_BALL1 = 400;
    public static final double START_X_BALL2 = 390;
    public static final double START_Y_BALL2 = 400;
    public static final double START_X_BALL3 = 410;
    public static final double START_Y_BALL3 = 400;
    public static final double BOARD_LIMIT = 20;
    public static final double ZERO = 0;
    public static final int START_POSITION_X = 40;
    public static final int START_POSITION_Y = 40;

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added. Should not be null.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Removes a collidable object from the environment.
     * This method delegates the removal of the specified collidable object to the environment
     * in which it resides.
     *
     * @param c The collidable object to be removed from the environment.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s The sprite to be added. Should not be null.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the collection of sprites.
     * This method delegates the removal of the specified sprite to the collection of sprites
     * in which it resides.
     *
     * @param s The sprite to be removed from the collection.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initializes the object.
     * This method is typically called to set up the initial state or configuration of the object.
     * The specific actions taken during initialization depend on the implementation.
     */
    public void initialize() {
        this.environment = new GameEnvironment(new ArrayList<>());
        this.sprites = new SpriteCollection(new ArrayList<>());
        // Create the paddel
        createPaddle();
        // Create ball
        createBall();
        // Create the blocks and add them to the game
        createBlocks();
        // Create borders
        createBorders();
        // create the down wall
        deathRegionBlock();
        // create score
        setScore();
    }

    /**
     * Creates a paddle object.
     * This method is responsible for instantiating and configuring a paddle object.
     * The specific details of the paddle creation depend on the implementation.
     */
    public void createPaddle() {
        Point point = new Point(360, 575);
        Rectangle rectangle = new Rectangle(point, WIDTH_OF_PADDLE, HIGHT_OF_PADDLE);
        Paddle paddle = new Paddle(rectangle, Color.YELLOW);
        paddle.addToGame(this);
        this.paddle1 = paddle;
    }
    /**
     * Creates a ball object.
     * This method is responsible for instantiating and configuring a ball object.
     * The specific details of the ball creation depend on the implementation.
     */
    public void createBall() {
        // Ball 1
        Ball ball1 = new Ball(START_X_BALL1, START_Y_BALL1, RADIUS, Color.BLACK);
        ball1.setVelocity(VX, VY);
        ball1.addToGame(this);
        ball1.setGameEnvironment(environment);
        // Ball 2
        Ball ball2 = new Ball(START_X_BALL2, START_Y_BALL2, RADIUS, Color.BLACK);
        ball2.setVelocity(VX, VY);
        ball2.addToGame(this);
        ball2.setGameEnvironment(environment);
        // Ball 3
        Ball ball3 = new Ball(START_X_BALL3, START_Y_BALL3, RADIUS, Color.BLACK);
        ball3.setVelocity(VX, VY);
        ball3.addToGame(this);
        ball3.setGameEnvironment(environment);
        counterForBalls.increase(3);
    }

    /**
     * Creates borders or boundaries for the game environment.
     * This method is responsible for instantiating and configuring the borders that define the game area.
     * The specific details of the border creation depend on the implementation.
     */
    public void createBorders() {
        // Create up border
        Point pointUpLeft = new Point(ZERO, 20);
        Rectangle rectangleUp = new Rectangle(pointUpLeft, WIDTH_OF_BOARD, BOARD_LIMIT);
        Block blockUp = new Block(Color.gray, rectangleUp);
        blockUp.addToGame(this);
        // create left border
        Point pointLeftUp = new Point(ZERO, 20);
        Rectangle rectangleLeft = new Rectangle(pointLeftUp, BOARD_LIMIT, HIGHT_OF_BOARD);
        Block blockLeft = new Block(Color.gray, rectangleLeft);
        blockLeft.addToGame(this);
        // Create right bored
        Point pointRightUp = new Point(WIDTH_OF_BOARD - BOARD_LIMIT, 20);
        Rectangle rectangleRight = new Rectangle(pointRightUp, BOARD_LIMIT, HIGHT_OF_BOARD);
        Block blockRight = new Block(Color.gray, rectangleRight);
        blockRight.addToGame(this);
    }

    /**
     * Represents the death region block in the game.
     * This method handles the functionality associated with the death region block,
     * such as removing balls that come into contact with it.
     */
    public void deathRegionBlock() {
        BallRemover ballRemover = new BallRemover(this, counterForBalls);
        // Create down border
        Point pointDownLeft = new Point(ZERO, HIGHT_OF_BOARD + 5);
        Rectangle rectangleDown = new Rectangle(pointDownLeft, WIDTH_OF_BOARD, BOARD_LIMIT);
        Block blockDown = new Block(Color.gray, rectangleDown);
        blockDown.addHitListener(ballRemover);
        blockDown.addToGame(this);
    }

    /**
     * Creates blocks for the game environment.
     * This method is responsible for instantiating and configuring blocks within the game.
     * The specific details of block creation depend on the implementation.
     */
    public void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, counterForBlocks);
        int xStartPosition = START_POSITION_X;
        int yStartPosition = START_POSITION_Y;
        // Define an array of colors for each line
        Color[] lineColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA,
                Color.DARK_GRAY, Color.CYAN, Color.ORANGE, Color.BLACK, Color.GRAY};
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - i * 2; j++) {
                // Create for evert row
                Point upperLeft = new Point(xStartPosition, yStartPosition);
                Rectangle rectangle = new Rectangle(upperLeft, WIDTH_OF_BLOCK, HIGHT_OF_BLOCK);
                Block block = new Block(lineColors[i], rectangle);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracking);
                counterForBlocks.increase(1);
                xStartPosition += WIDTH_OF_BLOCK;
            }
            // Take it a little bit after
            xStartPosition = START_POSITION_X + (i + 1) * WIDTH_OF_BLOCK;
            yStartPosition += HIGHT_OF_BLOCK;
        }
    }

    /**
     * Sets the score for this game instance.
     */
    public void setScore() {
        scoreIndicator.addToGame(this);
    }


    /**
     * Runs the main logic or game loop.
     * This method is responsible for executing the primary logic of the application,
     * typically involving continuous updating, rendering, and handling user input.
     * The specific details of the game loop depend on the implementation.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("Ass5Game", (int) WIDTH_OF_BOARD, (int) HIGHT_OF_BOARD);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        paddle1.setKeyboard(keyboard);

        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;

            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            // all the balls run way
            if (counterForBalls.getValue() == 0) {
                gui.close();
                return;
            }
            if (counterForBlocks.getValue() == 0) {
                // he won
                score.increase(100);
                gui.close();
                return;
            }
        }

    }
}
