# libgdx-tiledmappacker
A standalone version of gdx-tools' TexturePacker.

See the Releases section for runnable JARs.

[See the libGDX wiki for more information](https://libgdx.com/wiki/tools/texture-packer).

Given one or more images and optionally a configuration JSON file, TexturePacker will pack an atlas from the images so
they can be loaded more efficiently and moved more conveniently.

This is both a usable command-line tool for packing atlases, and a small library to do the same thing.

## As A Runnable Tool

See the wiki article above for how to use this from the command-line, though you can mostly replace any of the
convoluted classpath-setting code to run the JAR with a normal `java -jar libgdx-texturepacker-runnable-1.12.0.0.jar`.
There's also a runnable available for the TextureUnpacker, which was only available to run before through a rather
complicated set of steps. The unpacker uses `java -jar libgdx-textureunpacker-runnable-1.12.0.0.jar` .

Both runnable JARs work on Windows (x64), Linux (x64 and ARM), and MacOS (x64 and ARM). This means you can run libGDX's
TexturePacker on macOS machines with Apple Silicon chips.

The TexturePacker tool here has the same defaults as the one in gdx-tools, through version 1.11.0.1 . After that, this
tool (will) default to turning on fast packing, turning off legacy format, and turning off pretty-printing for atlases.
I have good reasons for this! Legacy format is only needed if you are loading atlases with libGDX 1.9.13 or older, which
is an old enough version that you can be expected to have a working set of tools if you still need it. Pretty-print only
applies if you're reading atlas files as a human reader, which in my opinion should be fairly rare. Both of those
options make atlas files larger if turned on, sometimes significantly, for no real benefit. Fast packing is turned on
because it can make the difference between maybe a half-hour packing process (if you have a very large atlas and fast
mode is on), and what could be over 10 hours (for the same very large atlas with fast mode off). The only downside to
fast packing is that it sometimes doesn't pack as tightly, and might need another atlas page as a result. However, if a
pack process seems like it will take all day, many people just won't bother, and might never notice that fast mode could
make a sizable difference. Having fast mode on by default makes packing start fast with loose packing, and you can
choose to make it slow with tight packing if you need it. 

## As A Library

You can depend on this using Gradle, Maven, or other project-handling tools.

If you use Gradle (more likely), you can get TexturePacker as a tiny library from Maven Central with:

`implementation 'com.github.tommyettinger:libgdx-texturepacker:1.12.0.0'`

and/or the separate TextureUnpacker:

`implementation 'com.github.tommyettinger:libgdx-textureunpacker:1.12.0.0'`

This is compatible with LWJGL2 and LWJGL3, but won't run on Android, iOS, or HTML targets because it uses AWT.

If you instead use Maven (unlikely unless you specifically chose it):
```
<dependencies>
  <dependency>
    <groupId>com.github.tommyettinger</groupId>
    <artifactId>libgdx-texturepacker</artifactId>
    <version>1.12.0.0</version>
  </dependency>
</dependencies>
```

and/or TextureUnpacker:

```  
<dependencies>
  <dependency>
    <groupId>com.github.tommyettinger</groupId>
    <artifactId>libgdx-textureunpacker</artifactId>
    <version>1.12.0.0</version>
  </dependency>
</dependencies>
```

Another option is to use JitPack to handle building a release for you. You can get TexturePacker from JitPack with:

`implementation 'com.github.tommyettinger.libgdx-texturepacker:libgdx-texturepacker:1.12.0.0'`

and/or the separate TextureUnpacker:

`implementation 'com.github.tommyettinger.libgdx-texturepacker:libgdx-textureunpacker:1.12.0.0'`

These can be useful if Maven Central is down or slow.

This was taken from inside [libGDX](https://github.com/libgdx/libgdx) and moved so it can be run more easily from
outside that framework. It still has the same license as libGDX, Apache 2.0 . The `StartupHelper` class in
the tests is by damios, from the [guacamole](https://github.com/crykn/guacamole) library, also Apache 2.0 . StartupHelper has since been
augmented to help some common issues on Windows as well as macOS.

# Changelog
1.12.0.0 : Updated to libGDX 1.12.0. Changed defaults: `legacy=false`, `pretty=false`, `fast=true`.

1.11.0.1 : Libraries don't contain all of libGDX inside them now, which should help the size of games.
           Runnables are still "fat JARs," and can be run with the same steps as before.

1.11.0.0 : Initial release; this included some minor fixes but mostly cleans up dependencies.
