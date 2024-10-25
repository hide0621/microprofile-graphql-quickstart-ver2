package service

import entity.Film
import entity.Hero
import jakarta.enterprise.context.ApplicationScoped
import type.LightSaber
import java.time.LocalDate
import java.time.Month

@ApplicationScoped
class GalaxyService {

    /**
     * addメソッドを使うので、MutableListを使う
     */
    private val heroes: MutableList<Hero> = mutableListOf()
    private val films: MutableList<Film> = mutableListOf()

    fun allFilms(): List<Film> = films

    /**
     * このメソッドは、[films]リストから指定されたIDの映画を取得する
     * 同期処理用のゲッター
     */
    fun getFilm(id: Int): Film = films[id]

    /**
     * このメソッドは、[films]リストから指定されたIDの映画を取得する
     * 非同期処理用のゲッター
     */
//    fun getFilm(id: Int): Uni<Film> {
//        return Uni.createFrom().item(films[id])
//    }

    /**
     * [it]はラムダ式の暗黙の変数で、この場合は[heroes]リストの要素（ここの[Hero]オブジェクト）を指す
     *
     * [contains]は、リスト(今回は[episodeIds])が指定された要素を含む場合にtrueを返す
     */
    fun getHeroesByFilm(film: Film): List<Hero> =
        heroes.filter { it.episodeIds.contains(film.episodeID) }

    fun addHero(hero: Hero) {
        heroes.add(hero)
    }

    fun deleteHero(id: Int): Hero = heroes.removeAt(id)

    fun getHeroesBySurname(surname: String): List<Hero> = heroes.filter { it.surname.equals(surname) }

    /**
     * 以下の初期化処理（オブジェクト生成）のコードは直感に反しておらず分かりやすいため、色んなシーンで使えそう
     * この初期化をするなら、クラスにプライマリーコンストラクタ（引数なしのデフォルトコンストラクタも含む）もセカンダリーコンストラクタも不要
     */
    init {
        Film()
            .apply {
                title = "A New Hope"
                releaseDate = LocalDate.of(1977, Month.MAY, 25)
                episodeID = 4
                director = "George Lucas"
            }
            .let { films.add(it) }

        Film()
            .apply {
                title = "The Empire Strikes Back"
                releaseDate = LocalDate.of(1980, Month.MAY, 21)
                episodeID = 5
                director = "George Lucas"
            }
            .let { films.add(it) }

        Film()
            .apply {
                title = "Return Of The Jedi"
                releaseDate = LocalDate.of(1983, Month.MAY, 25)
                episodeID = 6
                director = "George Lucas"
            }
            .let { films.add(it) }

        Hero()
            .apply {
                name = "Luke"
                surname = "Skywalker"
                height = 1.7
                mass = 73
                lightSaber = LightSaber.GREEN
                darkSide = false
                episodeIds.addAll(listOf(4, 5, 6))
            }
            .let { heroes.add(it) }

        Hero()
            .apply {
                name = "Leia"
                surname = "Organa"
                height = 1.5
                mass = 51
                darkSide = false
                episodeIds.addAll(listOf(4, 5, 6))
            }
            .let { heroes.add(it) }

        Hero()
            .apply {
                name = "Darth"
                surname = "Vader"
                height = 1.9
                mass = 89
                darkSide = true
                lightSaber = LightSaber.RED
                episodeIds.addAll(listOf(4, 5, 6))
            }
            .let { heroes.add(it) }
    }
}