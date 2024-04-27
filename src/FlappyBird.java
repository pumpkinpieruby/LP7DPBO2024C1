import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // pipe attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    // score
    int score = 0;
    JLabel scoreLabel;

    // game over flag
    boolean gameOver = false;

    // restart flag
    boolean restartFlag = false;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // pipes cooldown
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) // prevent placing new pipes if game over
                    placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 100, 30);
        add(scoreLabel);
    }

    public void placePipes() {
        int randomPipePosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPipePosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPipePosY + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

        if (gameOver) {
            // Tampilkan pesan "Press 'R' to restart"
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            String restartMessage = "Press 'R' to restart";
            int messageWidth = g.getFontMetrics().stringWidth(restartMessage);
            g.drawString(restartMessage, (frameWidth - messageWidth) / 2, frameHeight / 2);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move() {
        if (!gameOver) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));

            boolean passedPipe = false; // Variabel untuk menandakan apakah pemain telah melewati pipa yang sejajar

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityY());

                // check collision
                if (player.getPosX() + player.getWidth() > pipe.getPosX() && player.getPosX() < pipe.getPosX() + pipe.getWidth()) {
                    if (player.getPosY() < pipe.getPosY() + pipe.getHeight() && player.getPosY() + player.getHeight() > pipe.getPosY()) {
                        gameOver = true;
                    }
                }

                // check if passed pipe
                if (pipe.getPosX() + pipe.getWidth() < player.getPosX() && !pipe.isPassed()) {
                    pipe.setPassed(true);
                    passedPipe = true; // Setelah melewati pipa, set variabel passedPipe menjadi true
                }

                // cek jika pemain melewati sepasang pipa
                if (passedPipe && i % 2 == 1) {
                    score++; // Jika pemain telah melewati sepasang pipa, tambahkan skor
                    scoreLabel.setText("Score: " + score);
                    passedPipe = false; // Reset variabel passedPipe menjadi false untuk pipa berikutnya
                }
            }

            // check if player hits bottom
            if (player.getPosY() + player.getHeight() >= frameHeight) {
                gameOver = true;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocityY(-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver && !restartFlag) {
            restartFlag = true; // Set restartFlag menjadi true
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void restartGame() {
        // reset player position
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);

        // clear pipes and reset score
        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: 0");

        // reset game over flag
        gameOver = false;

        // reset restart flag
        restartFlag = false;
    }
}
