# Apache Maven Aggregator

Manifest to fetch every Apache Maven git repositories using [Google repo](https://source.android.com/source/using-repo).

## Bootstrapping Basics

```
mkdir maven
cd maven
repo init -u https://github.com/hboutemy/maven-aggregator.git
repo sync
repo start master --all
```

Then simply use the cloned content with normal `git` commands.
