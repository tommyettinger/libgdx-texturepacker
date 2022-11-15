# libgdx-tiledmappacker
A standalone version of gdx-tools' TiledMapPacker.

See the Releases section for runnable JARs.

[See the libGDX wiki for maybe more information](https://libgdx.com/wiki/tools/texture-packer), but that probably isn't
the right article. The Tiled Map Packer wasn't really documented like the other tools in libGDX.

Given one or more TMX tilemaps, packs all tileset resources used across the maps, or the resources used per map, into a
single, or multiple (one per map), `TextureAtlas` and produces a new TMX file to be loaded with an `AtlasTiledMapLoader`
loader. Optionally, it can keep track of unused tiles and omit them from the generated atlas, reducing the resource size.

**Usage (all desktop platforms)**:

`java -jar libgdx-tiledmappacker-runnable-1.11.0.4.jar inputDir [outputDir] [--strip-unused] [--combine-tilesets] [-v]`

The inputDir should contain a .tmx file and any tilesets it uses, placed how the .tmx map specifies them. The outputDir,
if not specified, will be the folder `output/` next to the inputDir. If `--strip-unused` is present, then tiles that
aren't in the .tmx map will not be present in the resulting atlas. if `--combine-tilesets` is present, all tilesets will
be merged into "some kind of monster tileset;" this is not currently recommended unless you know what you are doing. If
`-v` is present, this uses verbose mode, and will print much more output about everything it does.

The original TMX map file will be parsed by using the `TmxMapLoader` loader, thus access to a valid OpenGL context is
**required**; that's why an `Lwjgl3Application` is created by this preprocessor (with a tiny window).

The new TMX map file will contain a new property, "atlas", whose value will enable the `AtlasTiledMapLoader` to
correctly read the associated `TextureAtlas` representing the tileset.

This can be depended on and launched from code in an LWJGL3 project, typically calling the `TiledMapPacker.main()`
method or the `processInputDir()` method on a new `TiledMapPacker` object. This creates a new window, but thanks to
LWJGL3's features, it won't supersede any existing window, and should close on its own after packing completes.

You can depend on this using Gradle, Maven, or other project-handling tools.

If you use Gradle (more likely):

`implementation 'com.github.tommyettinger:libgdx-tiledmappacker:1.11.0.4'`

If you instead use Maven (unlikely unless you specifically chose it):
```
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>libgdx-tiledmappacker</artifactId>
  <version>1.11.0.4</version>
  <type>module</type>
</dependency>
```
(I don't actually know if it needs `<type>module</type>`; you can try without it.)

This was taken from inside [libGDX](https://github.com/libgdx/libgdx) and moved so it can be run more easily from
outside that framework. It still has the same license as libGDX, Apache 2.0 . The `StartOnFirstThreadHelper` class is by
damios, from the [guacamole](https://github.com/crykn/guacamole) library, also Apache 2.0 .

# Changelog

1.11.0.4: This is the first complete release published to Maven Central, so you can depend on it as a library if needed.
This also switches away from `gdx-lwjgl3-glfw-awt-macos` and just uses `glfw_async`, which is a part of recent LWJGL3,
because the former has some odd bugs with window resizing and movement. It still doesn't need `-XstartOnFirstThread` !

1.11.0.3: Whoops. See 1.11.0.4, released just after this.

1.11.0.2 : Support usage where the tool is launched from code, by not forcibly exiting the application when packing
completes (just the window is closed).

1.11.0.1 : Small release to minimize the JAR with ProGuard and to use `gdx-lwjgl3-glfw-awt-macos` to make running this
easier on macOS.

1.11.0.0 : Initial release; this included the port to use PixmapPacker and LWJGL3 instead of TexturePacker and LWJGL2.
