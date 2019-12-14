# Apache Maven Sources

Manifest to fetch every [Apache Maven](https://maven.apache.org) git repositories using [Google repo](https://source.android.com/source/using-repo).

In addition, an aggregator structure is provided to build everything as one aggregated build.

## Bootstrapping Basics

```
mkdir maven
cd maven
repo init -u https://gitbox.apache.org/repos/asf/maven-sources.git
repo sync
repo start master --all
```

Then simply use the cloned content with normal `git` commands.

## Building Everything

Once content is cloned with previous instructions, you can build each repository as a separate project.

You can also build absolutely everything in one aggregated run (WARNING: more than 400 modules...):

```
cd sources
mvn --fail-at-end -Prun-its verify
mvn --fail-at-end -Preporting site
```

## Sources Overview

See [Maven Sources Overview](https://maven.apache.org/scm.html) description.
