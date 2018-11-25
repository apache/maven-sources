# Apache Maven Sources

Manifest to fetch every [Apache Maven](https://maven.apache.org) git repositories using [Google repo](https://source.android.com/source/using-repo).

## Bootstrapping Basics

```
mkdir maven
cd maven
repo init -u https://gitbox.apache.org/repos/asf/maven-sources.git
repo sync
repo start master --all
```

Then simply use the cloned content with normal `git` commands.

## Sources Overview

See [Maven Sources Overview](https://maven.apache.org/scm.html) description.
