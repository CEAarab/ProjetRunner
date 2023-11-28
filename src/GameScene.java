import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

class GameScene extends Scene {
    private Camera camera;
    private StaticThing lb;
    private StaticThing rb;
    private int numberOfLives;
    private Hero hero;

    public GameScene(Group root) {
        super(root, 800, 600);

        this.camera = new Camera(0, 0);
        this.numberOfLives = 3;

        this.lb = new StaticThing(800, 600, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\desert.png");
        this.rb = new StaticThing(800, 600, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\desert.png");
        root.getChildren().addAll(lb.getImageView(), rb.getImageView());

        double totalWidth = numberOfLives * 30;
        double startX = 240;
        for (int i = 0; i < numberOfLives; i++) {
            StaticThing heart = new StaticThing(20, 20, "C:\\Users\\Dell_Latitude_3510\\Downloads\\Ressources audio et image pour le runner-20231107\\img\\heart.jpg");
            heart.getImageView().setTranslateX(startX + i * 30);
            heart.getImageView().setTranslateY(10);
            ((Group) this.getRoot()).getChildren().add(heart.getImageView());
        }

        Hero hero = new Hero(80, 400, 600, 800, 0, 6);
        this.hero = hero;
        root.getChildren().add(hero.getSprite());

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3), event -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(200), event -> updateHero()));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();

        this.setOnMouseClicked(event -> {
            hero.setAttitude(1);
            updateHero();
            hero.jump();
        });
    }

    private void updateHero() {
        hero.setIndex((hero.getIndex() + 1) % hero.getMaxIndex());
        if (hero.getAttitude() == 0) {
            hero.setMaxIndex(6);
        } else if (hero.getAttitude() == 1) {
            hero.setMaxIndex(2);
        } else if (hero.getAttitude() == 2) {
            hero.setMaxIndex(6);
        } else if (hero.getAttitude() == 3) {
            hero.setMaxIndex(2);
        }
        hero.getSprite().setViewport(hero.createViewport(hero.getIndex(), hero.getAttitude(), hero.getMaxIndex()));
    }

    private void update() {
        camera.setX(camera.getX() + 1);
        render();
    }

    public void render() {
        double cameraX = camera.getX();
        double width = lb.getImageView().getFitWidth();
        double leftBackgroundX = -cameraX % width;
        double rightBackgroundX = width + leftBackgroundX;

        lb.getImageView().setX(leftBackgroundX);
        lb.getImageView().setY(0);

        rb.getImageView().setX(rightBackgroundX);
        rb.getImageView().setY(0);
    }
}
