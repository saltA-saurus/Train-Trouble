public class Train {
    public int x, y, velX, velY, col;
    Train() {
        x = 45;
        y = 700;
        velX = 0;
        velY = -1;
        col = (int)(Math.random() * ((5 - 0) + 1));

    }
}