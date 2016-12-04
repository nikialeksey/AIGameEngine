package org.nikialeksey.gameengine.ai.turingproof;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App extends ApplicationAdapter {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Set<String> keys = new HashSet<>(Arrays.asList("110", "101", "011", "010", "001"));
    //    private static final Set<String> keys = new HashSet<>(Arrays.asList("111", "101", "000"));
//    private static final Set<String> keys = new HashSet<>(Arrays.asList("111", "100", "010", "001"));
    private static final Color COLOR = Color.CHARTREUSE;
    private static final Color BACKGROUND = Color.BLACK;

    private Batch batch;
    private Pixmap pixmap;

    public static void main(String[] args) {
        new LwjglApplication(new App(), "", WIDTH, HEIGHT);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);
        initPixmap();
    }

    private void initPixmap() {
        clearPixmap();

        pixmap.setColor(COLOR);
        pixmap.drawPixel(0, HEIGHT - 1);
        for (int step = 1; step < WIDTH; step++) {
            updateColumn(keys, step);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        shiftLeft();
        updateColumn(keys, WIDTH - 1);
        Texture texture = new Texture(pixmap);
        batch.draw(texture, 0, 0);
        batch.end();
        texture.dispose();
    }

    private void shiftLeft() {
        for (int y = 0; y < pixmap.getHeight(); y++) {
            clearPixel(0, y);
            for (int x = 1; x < pixmap.getWidth(); x++) {
                drawPixel(x - 1, y, pixelColor(x, y));
            }
        }
    }

    private void updateColumn(Set<String> keys, int step) {
        for (int y = 0; y < pixmap.getHeight(); y++) {
            int c1 = getBinaryReprOfPixColor(step - 1, y - 1);
            int c2 = getBinaryReprOfPixColor(step - 1, y);
            int c3 = getBinaryReprOfPixColor(step - 1, y + 1);

            String key = "" + c1 + c2 + c3;
            if (keys.contains(key)) {
                drawPixel(step, y);
            } else {
                clearPixel(step, y);
            }
        }
    }

    private void clearPixmap() {
        pixmap.setColor(BACKGROUND);
        pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
    }

    private void clearPixel(int x, int y) {
        pixmap.setColor(BACKGROUND);
        pixmap.drawPixel(x, y);
    }

    private void drawPixel(int x, int y) {
        drawPixel(x, y, COLOR);
    }

    private void drawPixel(int x, int y, int color) {
        drawPixel(x, y, new Color(color));
    }

    private void drawPixel(int x, int y, Color color) {
        pixmap.setColor(color);
        pixmap.drawPixel(x, y);
    }

    private int pixelColor(int x, int y) {
        return pixmap.getPixel(x, y);
    }

    private int getBinaryReprOfPixColor(int x, int y) {
        if (x < 0 || y < 0) return 1;
        int rgba8888 = pixmap.getPixel(x, y);
        Color color = new Color(rgba8888);
        if (color.equals(COLOR)) return 1;
        return 0;
    }
}
