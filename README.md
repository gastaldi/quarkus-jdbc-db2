# Quarkus DB2

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.jdbc/quarkus-jdbc-db2?logo=apache-maven&style=flat-square)](https://central.sonatype.com/artifact/io.quarkiverse.jdbc/quarkus-jdbc-db2)
[![Build](https://github.com/quarkiverse/quarkus-jdbc-db2/actions/workflows/build.yml/badge.svg)](https://github.com/quarkiverse/quarkus-jdbc-db2/actions/workflows/build.yml)

This repository hosts the [IBM DB2](https://www.ibm.com/products/db2) integration for [Quarkus](https://quarkus.io).
It bundles the blocking (JDBC), Flyway and reactive DB2 extensions together with their shared Dev Services,
lifted out of the Quarkus core repository so they can evolve independently.

## Extensions

All artifacts are published under the `io.quarkiverse.jdbc` group id.

| Directory                          | Runtime artifact                      | Description                                                                                       |
|------------------------------------|---------------------------------------|--------------------------------------------------------------------------------------------------|
| `extensions/jdbc-db2`              | `quarkus-jdbc-db2`                     | Blocking DB2 datasource support via the IBM DB2 JDBC driver (Agroal).                             |
| `extensions/flyway-db2`            | `quarkus-flyway-db2`                   | Flyway support for DB2. Added automatically when `quarkus-flyway` and `quarkus-jdbc-db2` are present. |
| `extensions/reactive-db2-client`   | `quarkus-reactive-db2-client`         | Reactive DB2 client built on the Vert.x DB2 client.                                               |
| `extensions/devservices`           | `quarkus-devservices-db2`             | Shared DB2 Dev Services (Testcontainers) used by the JDBC and reactive extensions.               |

Each extension follows the standard Quarkus `runtime` + `deployment` module layout, except `devservices`,
which is a single deployment-time module shared by the JDBC and reactive extensions.

## Repository layout

```
quarkus-jdbc-db2
├── pom.xml                         # quarkus-db2-parent (repository aggregator + dependency management)
├── extensions
│   ├── jdbc-db2                    # quarkus-jdbc-db2-parent (runtime + deployment)
│   ├── flyway-db2                  # quarkus-flyway-db2-parent (runtime + deployment)
│   ├── reactive-db2-client         # quarkus-reactive-db2-client-parent (runtime + deployment)
│   └── devservices                 # quarkus-devservices-db2
├── integration-tests
│   ├── jdbc-db2                    # basic JDBC smoke test
│   ├── jpa-db2                     # Hibernate ORM over the JDBC DB2 driver
│   ├── reactive-db2-client         # reactive DB2 client tests
│   └── hibernate-reactive-db2      # Hibernate Reactive over the reactive DB2 client
└── docs                            # Antora documentation
```

## Building

```shell
mvn install
```

The build targets the current Quarkus `999-SNAPSHOT` (main). Build it locally first if it is not already
available in your Maven repository.

### Running the integration tests

The `jpa-db2`, `reactive-db2-client` and `hibernate-reactive-db2` integration tests need a DB2 container, which
requires accepting the IBM DB2 license. They are therefore **skipped by default**. To run them, make sure a
container runtime is available and set the `test-containers` property (which activates the `test-db2` profiles):

```shell
mvn verify -Dtest-containers
```

The DB2 container image and its license acceptance are configured through the `db2.image` Maven property
(see the `quarkus-db2-parent` `pom.xml`).

## Documentation

The documentation for these extensions is maintained in the `docs/` directory following
[Antora's Standard File and Directory Set](https://docs.antora.org/antora/2.3/standard-directories/),
and is published to <https://docs.quarkiverse.io/>.

## Contributing

Contributions are welcome! Please refer to the [Quarkiverse wiki](https://github.com/quarkiverse/quarkiverse/wiki)
for the policies and conventions used across Quarkiverse projects.
