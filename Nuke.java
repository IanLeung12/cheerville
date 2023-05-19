import java.awt.*;

public class Nuke extends NonMovable{

    Nuke(int x, int y, int maxAge) {
        super(x, y, maxAge);
    }

    public Color getColor() {
        float h = 0.01f + 0.15f * ((float) this.getAge() / this.getMaxAge());
        return Color.getHSBColor(h, 0.75f, 0.75f);
    }
}
