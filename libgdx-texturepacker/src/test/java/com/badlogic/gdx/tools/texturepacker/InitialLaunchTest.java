package com.badlogic.gdx.tools.texturepacker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.StartupHelper;
import com.badlogic.gdx.utils.ScreenUtils;

public class InitialLaunchTest extends ApplicationAdapter {
    TextureAtlas atlas;
    SpriteBatch batch;
    @Override
    public void create() {
        atlas = new TextureAtlas(Gdx.files.local("tmp/atlas/default.atlas"));
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);;
        batch.begin();
        batch.draw(atlas.getTextures().first(), 0, 0);
        batch.end();
    }

    public static void main(String[] arg) {
        if (StartupHelper. startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.

        TexturePacker.Settings settings = new TexturePacker.Settings();
        TexturePacker.process(settings, "testGraphics/unpacked", "tmp/atlas", "default.atlas");
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.disableAudio(true);
        new Lwjgl3Application(new InitialLaunchTest(), cfg);
    }
}
