import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        GamePanel panel = new GamePanel();
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 24;
    private static final int HEIGHT = 24;
    private static final int TILE_SIZE = 25;
    private static final int INITIAL_DELAY = 150;
    private static final int SPEED_INCREASE_INTERVAL = 5;
    private static final int SPEED_INCREMENT = 10;
    private static final int MIN_DELAY = 50;

    private final Timer timer;
    private int dx = 1, dy = 0;
    private final LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private boolean gameOver = false;
    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
        timer = new Timer(INITIAL_DELAY, this);
        timer.start();
    }

    private void initGame() {
        snake.clear();
        snake.add(new Point(12, 12));
        snake.add(new Point(11, 12));
        snake.add(new Point(10, 12));
        generateFood();
        dx = 1;
        dy = 0;
        score = 0;
        gameOver = false;
        if (timer != null) {
            timer.setDelay(INITIAL_DELAY);
            timer.start();
        }
    }

    private void generateFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(HEIGHT);
        } while (snake.contains(new Point(x, y)));
        food = new Point(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            checkCollision();
        }
        repaint();
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = new Point(head.x + dx, head.y + dy);
        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            score++;
            increaseDifficulty();
            generateFood();
        } else {
            snake.removeLast();
        }
    }

    private void increaseDifficulty() {
        if (score % SPEED_INCREASE_INTERVAL == 0) {
            int newDelay = timer.getDelay() - SPEED_INCREMENT;
            if (newDelay >= MIN_DELAY) {
                timer.setDelay(newDelay);
            }
        }
    }

    private void checkCollision() {
        Point head = snake.getFirst();
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver = true;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                break;
            }
        }

        if (gameOver) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw snake
        for (Point p : snake) {
            g.setColor(p == snake.getFirst() ? Color.GREEN : new Color(45, 180, 0));
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE - 2, TILE_SIZE - 2);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE - 2, TILE_SIZE - 2);

        // Draw score and speed
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Speed: " + (INITIAL_DELAY - timer.getDelay()) / SPEED_INCREMENT + "x", 10, 40);

        // Game over message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String msg = "Game Over! Score: " + score;
            int msgWidth = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, (getWidth() - msgWidth) / 2, getHeight() / 2);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            String restartMsg = "Press SPACE to restart";
            int restartWidth = g.getFontMetrics().stringWidth(restartMsg);
            g.drawString(restartMsg, (getWidth() - restartWidth) / 2, getHeight() / 2 + 40);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameOver) {
            if (key == KeyEvent.VK_UP && dy != 1) {
                dx = 0;
                dy = -1;
            } else if (key == KeyEvent.VK_DOWN && dy != -1) {
                dx = 0;
                dy = 1;
            } else if (key == KeyEvent.VK_LEFT && dx != 1) {
                dx = -1;
                dy = 0;
            } else if (key == KeyEvent.VK_RIGHT && dx != -1) {
                dx = 1;
                dy = 0;
            }
        } else {
            if (key == KeyEvent.VK_SPACE) {
                initGame();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}