# libgdx-tiledmappacker
A standalone version of gdx-tools' TexturePacker.

See the Releases section for runnable JARs.

[See the libGDX wiki for more information](https://libgdx.com/wiki/tools/texture-packer).

Given one or more images and optionally a configuration JSON file, TexturePacker will pack an atlas from the images so
they can be loaded more efficiently and moved more conveniently.

This is both a usable command-line tool for packing atlases, and a small library to do the same thing.

## As A Runnable Tool

See the wiki article above for how to use this from the command-line, though you can mostly replace any of the
convoluted classpath-setting code to run the JAR with a normal `java -jar libgdx-texturepacker-runnable-1.11.0.1.jar`.
There's also a runnable available for the TextureUnpacker, which was only available to run before through a rather
complicated set of steps. The unpacker uses `java -jar libgdx-textureunpacker-runnable-1.11.0.1.jar` .

Both runnable JARs work on Windows (x64), Linux (x64 and ARM), and MacOS (x64 and ARM). This means you can run libGDX's
TexturePacker on macOS machines with Apple Silicon chips.

## As A Library

You can depend on this using Gradle, Maven, or other project-handling tools.

If you use Gradle (more likely), you can get TexturePacker as a tiny library from Maven Central with:

`implementation 'com.github.tommyettinger:libgdx-texturepacker:1.11.0.1'`

and/or the separate TextureUnpacker:

`implementation 'com.github.tommyettinger:libgdx-textureunpacker:1.11.0.1'`

This is compatible with LWJGL2 and LWJGL3, but won't run on Android, iOS, or HTML targets because it uses AWT.

If you instead use Maven (unlikely unless you specifically chose it):
```
<dependencies>
  <dependency>
    <groupId>com.github.tommyettinger</groupId>
    <artifactId>libgdx-texturepacker</artifactId>
    <version>1.11.0.1</version>
  </dependency>
</dependencies>
```

and/or TextureUnpacker:

```  
<dependencies>
  <dependency>
    <groupId>com.github.tommyettinger</groupId>
    <artifactId>libgdx-textureunpacker</artifactId>
    <version>1.11.0.1</version>
  </dependency>
</dependencies>
```

Another option is to use JitPack to handle building a release for you. You can get TexturePacker from JitPack with:

`implementation 'com.github.tommyettinger.libgdx-texturepacker:libgdx-texturepacker:1.11.0.1'`

and/or the separate TextureUnpacker:

`implementation 'com.github.tommyettinger.libgdx-texturepacker:libgdx-textureunpacker:1.11.0.1'`

These can be useful if Maven Central is down or slow.

This was taken from inside [libGDX](https://github.com/libgdx/libgdx) and moved so it can be run more easily from
outside that framework. It still has the same license as libGDX, Apache 2.0 . The `StartOnFirstThreadHelper` class in
the tests is by damios, from the [guacamole](https://github.com/crykn/guacamole) library, also Apache 2.0 .

# Changelog

1.11.0.1 : Libraries don't contain all of libGDX inside them now, which should help the size of games.
           Runnables are still "fat JARs," and can be run with the same steps as before.

1.11.0.0 : Initial release; this included some minor fixes but mostly cleans up dependencies.
