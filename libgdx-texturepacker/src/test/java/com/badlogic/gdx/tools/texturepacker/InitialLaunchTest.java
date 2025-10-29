package com.badlogic.gdx.tools.texturepacker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.tools.StartupHelper;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class InitialLaunchTest extends ApplicationAdapter {
    TextureAtlas atlas;
    SpriteBatch batch;
    @Override
    public void create() {
        FileHandle loc = Gdx.files.local("tmp/cgAtlas"+ (MathUtils.random(1) * 3) +"/default.atlas");
        if(!loc.exists()) loc = Gdx.files.local("tmp/cgAtlas3/default.atlas");
        atlas = new TextureAtlas(loc);
//        atlas = new TextureAtlas(Gdx.files.local("tmp/atlas/default.atlas"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(atlas.getTextures().first(), 0, 0);
        batch.end();
    }

    /**
     * PR not used: Processed in 89456 ms.
     * PR used:     Processed in 8949 ms.
     * fast==false: Processed in 3226472 ms.
     * @param arg ignored
     * @throws FileNotFoundException if test files are not found
     */
    public static void main(String[] arg) throws FileNotFoundException {
        if (StartupHelper. startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.

        long startTime = System.currentTimeMillis();
        TexturePacker.Settings settings = new Json().fromJson(TexturePacker.Settings.class, new FileReader("testGraphics/cg/pack.json"));
        int speed = 0;
        if(settings.fast) speed = 3;
        TexturePacker.process(settings, "testGraphics/cg/", "tmp/cgAtlas" + speed, "default.atlas");
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        TexturePacker.process(settings, "testGraphics/unpacked", "tmp/atlas", "default.atlas");
        System.out.println("Processed in " + (System.currentTimeMillis() - startTime) + " ms.");
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.disableAudio(true);
        new Lwjgl3Application(new InitialLaunchTest(), cfg);
    }
}
