`xdg-java`
==========

This is a Java API that provides an easy to use implementations of
various
[Free Desktop specifications](https://www.freedesktop.org/wiki/Specifications/).
Use the correct application specific configuration, data and cache
directories. Safely read and write desktop entries without worrying
about parsing and escaping.

Specifications
--------------

The following specifications are currently implemented:

### [Base Directory Specification](http://www.freedesktop.org/wiki/Specifications/menu-spec/)

Use this to access standard directories for data, cache and
configuration.

Here's how to find the path to a cache file with the name `myCache`:

    File cacheFile = new File(BaseDirectory.get(BaseDirectory.XDG_CONFIG_HOME), "myCache");

    
### The [Desktop Entry Specification](http://www.freedesktop.org/wiki/Specifications/desktop-entry-spec/)

Use this to read and write desktop entries, with full validation.

Here's how to read a desktop entry named `some.desktop`:

    BufferedReader reader = new BufferedReader(new FileReader("some.desktop"));
    DesktopEntry entry = new DesktopEntryReader(reader).read();
    System.out.println("Name: " + entry.get(DesktopEntry.KEY_NAME));


Install
-------

Build and install using maven:

    $ mvn clean install


If you are using maven, add a dependency to your `pom.xml` file:

    <dependency>
      <groupId>org.freedesktop</groupId>
      <artifactId>xdg-java</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

If you are using gradle, add a runtime dependency to your
`build.gradle` file:

    runtime "org.freedesktop:xdg-java:0.0.1-SNAPSHOT@jar"

For Ivy, add the following dependency to your `ivy.xml` file:

    <dependency org="org.freedesktop" name="xdg-java" rev="0.0.1-SNAPSHOT"/>


Contributing
------------
See `CONTRIBUTING.md` for details.


License
-------

LGPL v2. Please see the `LICENSE` file for details.

© Copyright Omair Majid
