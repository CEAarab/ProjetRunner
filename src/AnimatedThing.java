import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public abstract class AnimatedThing {
    private double x;
    private double y;
    private double width;
    private double height;
    private double ratio_w;
    private double ratio_h;
    private ImageView sprite;
    private double attitude;
    private int index;
    private int maxIndex;
    private double windowSize;
    private double offset;
    protected final int windowSizeX;
    protected final int windowSizeY;
    protected final int offsetBetweenFrames;
    private int durationBetweenFrames;

    public AnimatedThing(double posX, double posY, double objWidth, double objHeight, int attitudeVal,
                         String file, int currentIndex, int frameDuration, int maxIndexValue,
                         int windowX, int windowY, int frameOffset) {
        this.attitude = attitudeVal;
        this.index = currentIndex;
        this.durationBetweenFrames = frameDuration;
        this.maxIndex = maxIndexValue;
        this.windowSizeX = windowX;
        this.windowSizeY = windowY;
        this.offsetBetweenFrames = frameOffset;

        Image image = new Image(file);
        this.sprite = new ImageView(image);
        this.sprite.setViewport(createViewport(currentIndex, attitudeVal, maxIndexValue));
        this.sprite.setX(posX);
        this.sprite.setY(posY);
        this.width = objWidth;
        this.height = objHeight;
        this.x = posX;
        this.y = posY;
        this.ratio_h = 1;
        this.ratio_w = 1;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public double getAttitude() {
        return attitude;
    }

    public void setAttitude(double attitude) {
        this.attitude = attitude;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex) {
        this.maxIndex = maxIndex;
    }

    public double getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(double windowSize) {
        this.windowSize = windowSize;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    abstract public Rectangle2D createViewport(int index, double altitude, int maxIndex);

    public void jump() {
        double acc = 0.2;
        attitude = 1;
        y = sprite.getY();

        Timeline jumpTimeline = new Timeline();
        jumpTimeline.setCycleCount(5);
        jumpTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> {
                    y -= (0.2 * height * ratio_h) * acc;
                    sprite.setY(y);
                }
        ));

        Timeline gravityTimeline = new Timeline();
        gravityTimeline.setCycleCount(5);
        gravityTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> {
                    y += (0.2 * height * ratio_h) * acc;
                    sprite.setY(y);
                }
        ));

        jumpTimeline.setOnFinished(e -> {
            gravityTimeline.setOnFinished(event -> attitude = 0);
            gravityTimeline.play();
        });

        jumpTimeline.play();
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }
}
