# Apache Maven Sources

Manifest to fetch every [Apache Maven Sources](https://maven.apache.org/scm.html) Git repositories using [Google repo](https://source.android.com/source/using-repo): [`default.xml` file](default.xml) configures the
directory structure where the different Git repositories will be put.

In addition, an [`aggregator`](aggregator/) build structure is provided to build everything as one aggregated build.

## Bootstrapping Basics

```
mkdir maven
cd maven
repo init -u https://gitbox.apache.org/repos/asf/maven-sources.git
repo sync
repo start master --all
```

Resulting directory tree looks like:

```
|-- core
|   |-- build-cache
|   |-- maven
|   |-- maven-4.0.x
|   |-- mvnd
|   |-- resolver
|   |-- resolver-ant-tasks
|   |-- wrapper
|   `-- 3.x
|       |-- its
|       |-- maven-3
|       |-- mvnd-1
|       `-- resolver-1
|-- doxia
|   |-- doxia
|   |-- site
|   |-- sitetools
|   `-- tools
|       |-- converter
|       |-- doxia-book-maven-plugin
|       `-- linkcheck
|-- misc
|   |-- archetypes
|   |-- dist-tool
|   |-- gh-actions-shared
|   |   |-- main
|   |   `-- v4
|   |-- indexer
|   |-- jenkins
|   |   |-- env
|   |   `-- lib
|   |-- plugin-testing
|   |-- pom
|   |   |-- apache
|   |   |-- apache-resources
|   |   `-- maven
|   |-- skins
|   |   `-- fluido
|   `-- wagon
|-- plexus
|   |-- classworlds
|   |-- codehaus-plexus.github.io
|   |-- components
|   |   |-- archiver
|   |   |-- compiler
|   |   |-- i18n
|   |   |-- interactivity
|   |   |-- interpolation
|   |   |-- io
|   |   |-- languages
|   |   |-- resources
|   |   |-- sec-dispatcher
|   |   |-- testing
|   |   `-- velocity
|   |-- modello
|   |-- plexus-containers
|   |-- pom
|   |   `-- plexus
|   |-- utils
|   |-- testing
|   `-- xml
|-- plugins
|   |-- core
|   |   |-- maven-clean-plugin
|   |   |-- maven-compiler-plugin
|   |   |-- maven-deploy-plugin
|   |   |-- maven-install-plugin
|   |   |-- maven-resources-plugin
|   |   |-- maven-site-plugin
|   |   |-- maven-verifier-plugin
|   |   `-- surefire
|   |-- core-4
|   |   |-- maven-clean-plugin
|   |   |-- maven-compiler-plugin
|   |   |-- maven-deploy-plugin
|   |   |-- maven-install-plugin
|   |   |-- maven-resources-plugin
|   |   |-- maven-site-plugin
|   |-- packaging
|   |   |-- maven-acr-plugin
|   |   |-- maven-ear-plugin
|   |   |-- maven-ejb-plugin
|   |   |-- maven-jar-plugin
|   |   |-- maven-jlink-plugin
|   |   |-- maven-jmod-plugin
|   |   |-- maven-rar-plugin
|   |   |-- maven-shade-plugin
|   |   |-- maven-source-plugin
|   |   `-- maven-war-plugin
|   |-- packaging-4
|   |   |-- maven-jar-plugin
|   |   `-- maven-source-plugin
|   |-- reporting
|   |   |-- jxr
|   |   |-- maven-changelog-plugin
|   |   |-- maven-changes-plugin
|   |   |-- maven-checkstyle-plugin
|   |   |-- maven-doap-plugin
|   |   |-- maven-javadoc-plugin
|   |   |-- maven-jdeps-plugin
|   |   |-- maven-pmd-plugin
|   |   `-- maven-project-info-reports-plugin
|   `-- tools
|       |-- archetype
|       |-- enforcer
|       |-- maven-antrun-plugin
|       |-- maven-artifact-plugin
|       |-- maven-assembly-plugin
|       |-- maven-dependency-plugin
|       |-- maven-gpg-plugin
|       |-- maven-help-plugin
|       |-- maven-invoker-plugin
|       |-- maven-jarsigner-plugin
|       |-- maven-jdeprscan-plugin
|       |-- maven-remote-resources-plugin
|       |-- maven-scm-publish-plugin
|       |-- maven-scripting-plugin
|       |-- maven-stage-plugin
|       |-- maven-toolchains-plugin
|       |-- plugin-tools
|       |-- release
|       `-- scm
|-- shared
|   |-- archiver
|   |-- common-artifact-filters
|   |-- dependency-analyzer
|   |-- dependency-tree
|   |-- file-management
|   |-- filtering
|   |-- invoker
|   |-- jarsigner
|   |-- mapping
|   |-- reporting-api
|   |-- reporting-exec
|   |-- reporting-impl
|   |-- script-interpreter
|   |-- shared-incremental
|   |-- shared-io
|   |-- shared-jar
|   |-- shared-resources
|   |-- shared-utils
|   `-- verifier
|-- shared-4
|   |-- archiver
|   `-- filtering
|-- sisu
|   `-- sisu-project
|-- site
|-- sources
|   `-- aggregator
`-- studies
    |-- consumer-pom
    |-- master
    |-- maven-basedir-filesystem
    |-- maven-ci-extension
    |-- maven-default-plugins
    |-- maven-eventsound-extension
    `-- maven-extension-demo
```

Then simply use the content in this tree with normal `git` commands.

## Building Everything

Once content is cloned with previous instructions, you can build each local clone as a separate project.

You can also build absolutely everything in one aggregated run (WARNING: more than 400 modules...):

```
cd sources/aggregator
mvn --fail-at-end -Prun-its verify
mvn --fail-at-end -Preporting site
```

## Sources Overview

See [Maven Sources Overview](https://maven.apache.org/scm.html) description.
