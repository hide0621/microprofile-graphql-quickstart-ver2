package resource

import entity.Film
import entity.Hero
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.DefaultValue
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Mutation
import org.eclipse.microprofile.graphql.Name
import org.eclipse.microprofile.graphql.Query
import org.eclipse.microprofile.graphql.Source
import service.GalaxyService

/**
 * GraphQL APIのエンドポイントになるクラス
 */
@GraphQLApi
class FilmResource {
    @Inject
    private lateinit var galaxyService: GalaxyService

    /**
     * [allFilms]という名前のクエリを定義
     */
    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    fun getAllFilms(): List<Film> = galaxyService.allFilms()

    /**
     * [film]という名前のクエリを定義
     * 同期処理用のメソッド
     */
    @Query("film")
    @Description("Get a Films from a galaxy far far away")
    fun getFilm(@Name("filmId") id: Int): Film = galaxyService.getFilm(id)

    /**
     * [film]という名前のクエリを定義
     * 非同期処理用のメソッド
     */
//    @Query
//    @Description("Get a Films from a galaxy far far away")
//    fun getFilm(@Name("filmId") id:Int): Uni<Film> {
//        return service.getFilm(id)
//    }

    /**
     * [film]という名前のクエリを定義
     * @NonBlocking
     */
//    @Query
//    @Description("Get a Films from a galaxy far far away")
//    @NonBlocking
//    fun getFilm(@Name("filmId") id:Int):Film {
//        return service.getFilm(id)
//    }

    fun getHeroes(@Source film: Film): List<Hero> = galaxyService.getHeroesByFilm(film)

    /**
     * バッチ処理の形式でヒーローを取得する
     */
    fun getHeroesBatch(@Source films: List<Film>): List<List<Hero>> {
        return films.map { galaxyService.getHeroesByFilm(it) }
    }

    @Query("heroesWithSurname")
    fun getHeroesWithSurname(@DefaultValue("Skywalker") surname: String): List<Hero> {
        return galaxyService.getHeroesBySurname(surname)
    }

    /**
    [Mutation]のvalue[createHero]に合わせて、以下のようなクエリをGraphiQLに投げる
     */
    /**
    mutation createHero {
        createHero(hero: {
            name: "Han",
            surname: "Solo"
            height: 1.85
            mass: 80
            darkSide: false
            episodeIds: [4, 5, 6]
        }
        )
        {
            name
            surname
        }
    }
     */
    @Mutation("createHero")
    fun createHero(hero: Hero): Hero {
        galaxyService.addHero(hero)
        return hero
    }

    @Mutation("deleteHero") fun deleteHero(id: Int): Hero = galaxyService.deleteHero(id)

}