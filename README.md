# microprofile-graphql-quickstart-ver2

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

[こちらの方のコード](https://github.com/treetips/kotlin-quarkus-multiproject-example/tree/main/graphql-server)を参考にしました。

また、クエリを実行する先のEdittor（**GraphiQL**）は[こちら](http://localhost:8080/q/graphql-ui/?query=%23%20Welcome%20to%20GraphiQL%0A%23%0A%23%20GraphiQL%20is%20an%20in-browser%20tool%20for%20writing%2C%20validating%2C%20and%0A%23%20testing%20GraphQL%20queries.%0A%23%0A%23%20Type%20queries%20into%20this%20side%20of%20the%20screen%2C%20and%20you%20will%20see%20intelligent%0A%23%20typeaheads%20aware%20of%20the%20current%20GraphQL%20type%20schema%20and%20live%20syntax%20and%0A%23%20validation%20errors%20highlighted%20within%20the%20text.%0A%23%0A%23%20GraphQL%20queries%20typically%20start%20with%20a%20%22%7B%22%20character.%20Lines%20that%20start%0A%23%20with%20a%20%23%20are%20ignored.%0A%23%0A%23%20An%20example%20GraphQL%20query%20might%20look%20like%3A%0A%23%0A%23%20%20%20%20%20%7B%0A%23%20%20%20%20%20%20%20field(arg%3A%20%22value%22)%20%7B%0A%23%20%20%20%20%20%20%20%20%20subField%0A%23%20%20%20%20%20%20%20%7D%0A%23%20%20%20%20%20%7D%0A%0A%23%20query%20allFilms%20%7B%0A%23%20%20%20allFilms%20%7B%0A%23%20%20%20%20%20title%0A%23%20%20%20%20%20%23%20director%0A%23%20%20%20%20%20releaseDate%0A%23%20%20%20%20%20%23%20episodeID%0A%23%20%20%20%7D%0A%23%20%7D%0A%0A%23%20query%20allFilms%20%7B%0A%23%20%20%20allFilms%20%7B%0A%23%20%20%20%20%20title%0A%23%20%20%20%20%20releaseDate%0A%23%20%20%20%7D%0A%23%20%7D%0A%0A%23%20query%20getFilm%20%7B%0A%23%20%20%20film(filmId%3A%201)%20%7B%0A%23%20%20%20%20%20title%0A%23%20%20%20%20%20director%0A%23%20%20%20%20%20releaseDate%0A%23%20%20%20%20%20episodeID%0A%23%20%20%20%7D%0A%23%20%7D%0A%0A%23%20query%20getFilms%20%7B%0A%23%20%20%20film0%3A%20film(filmId%3A%200)%20%7B%0A%23%20%20%20%20%20title%0A%23%20%20%20%20%20director%0A%23%20%20%20%20%20releaseDate%0A%23%20%20%20%20%20episodeID%0A%23%20%20%20%7D%0A%23%20%20%20film1%3A%20film(filmId%3A%201)%20%7B%0A%23%20%20%20%20%20title%0A%23%20%20%20%20%20director%0A%23%20%20%20%20%20releaseDate%0A%23%20%20%20%20%20episodeID%0A%23%20%20%20%7D%0A%23%20%7D%0A%0Aquery%20getFilmHeroes%20%7B%0A%20%20film(filmId%3A%201)%20%7B%0A%20%20%20%20title%0A%20%20%20%20director%0A%20%20%20%20releaseDate%0A%20%20%20%20episodeID%0A%20%20%20%20heroes%20%7B%0A%20%20%20%20%20%20name%0A%20%20%20%20%20%20height%0A%20%20%20%20%20%20mass%0A%20%20%20%20%20%20darkSide%0A%20%20%20%20%20%20lightSaber%0A%20%20%20%20%7D%0A%20%20%7D%0A%7D%0A%0A%23%0A%23%20Keyboard%20shortcuts%3A%0A%23%0A%23%20%20%20Prettify%20query%3A%20%20Shift-Ctrl-P%20(or%20press%20the%20prettify%20button)%0A%23%0A%23%20%20Merge%20fragments%3A%20%20Shift-Ctrl-M%20(or%20press%20the%20merge%20button)%0A%23%0A%23%20%20%20%20%20%20%20%20Run%20Query%3A%20%20Ctrl-Enter%20(or%20press%20the%20play%20button)%0A%23%0A%23%20%20%20%20Auto%20Complete%3A%20%20Ctrl-Space%20(or%20just%20start%20typing)%0A%23%0A%0A&operationName=getFilmHeroes)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/microprofile-graphql-quickstart-ver2-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- SmallRye GraphQL ([guide](https://quarkus.io/guides/smallrye-graphql)): Create GraphQL Endpoints using the code-first approach from MicroProfile GraphQL
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
