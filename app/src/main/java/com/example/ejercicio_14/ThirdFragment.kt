package com.example.ejercicio_14

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_14.QuizTest.Quiz
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment(), Adaptador2.OnItemClickListener {

    private lateinit var miRecyclerView: RecyclerView
    private lateinit var dataset: List<Pelicula>
    private lateinit var dataset2: List<Cancion>
    private lateinit var dataset3: List<Libro>
    private lateinit var dataset4: List<Libro>

    var tematica: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataset = PeliculaList()
        dataset2 = CancionList()
        dataset3 = LibroList()
        dataset4 = listOf()

        tematica = arguments?.getString("tematica") ?: "pelicula"

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_third, container, false).apply { }
        miRecyclerView = rootView.findViewById(R.id.myRecyclerView2)
        miRecyclerView.layoutManager = LinearLayoutManager(activity)
        miRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        if (tematica == "pelicula") {
            miRecyclerView.adapter = Adaptador2((activity as MainActivity), dataset, null, null, this)
        } else if (tematica == "cancion"){
            miRecyclerView.adapter = Adaptador2((activity as MainActivity), null, dataset2, null, this)
        }else{
            (activity as MainActivity).miViewModel2.listaLibros.observe(activity as MainActivity){ Libros ->
                Libros?.let {
                    //dataset4 = it
                    miRecyclerView.adapter = Adaptador2((activity as MainActivity), null, null, it, this)
                }
            }
        }

        rootView.findViewById<FloatingActionButton>(R.id.fab2).setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_editFragment2)
        }

        return rootView
    }

    override fun onImageClick(imagen: String) {
        val bundle = bundleOf("imagen" to imagen)
        findNavController().navigate(R.id.action_thirdFragment_to_detailFragment, bundle)
    }

    override fun onItemClick(titulo: String) {
        Toast.makeText(activity, "El titulo es: $titulo", Toast.LENGTH_LONG).show()
    }

}

private fun PeliculaList(): List<Pelicula> {
    val lista = mutableListOf<Pelicula>()
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/dracula-de-bram-stoker-3159/dracula-de-bram-stoker-3159-c.jpg",
            "Drácula",
            "Terror",
            "1992"
        )
    )
    lista.add(
        Pelicula(
            "https://th.bing.com/th/id/OIP.pCpNa1ncZaBHdy4zokgFgAHaKa?w=131&h=184&c=7&o=5&dpr=1.25&pid=1.7",
            "Rambo II",
            "Acción",
            "1996"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2020/02/t-1000-terminator-2-1857499.jpg?itok=DmQ4ajn1",
            "Terminator II",
            "Acción",
            "1991"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2019/11/pulp-fiction_0.jpg?itok=_di21pHF",
            "Pulp Fiction",
            "Suspense",
            "1994"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2019/05/show-truman.jpg?itok=ps21ptku",
            "El show de Truman",
            "Comedia",
            "1998"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2018/06/peliculas-mas-taquilleras-verano-1975_18.jpg?itok=TTeQQC6n",
            "Forrest Gump",
            "Biografía",
            "1994"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2019/09/jurassic-park.jpg?itok=d2iKmMsd",
            "Parque Jurásico",
            "Aventuras",
            "1993"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2020/02/american-beauty-1875111.jpg?itok=AMhgARsU",
            "American Beauty",
            "comedia/drama",
            "1999"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2020/02/neo-trinity-matrix-1867247.jpg?itok=u9ItB65x",
            "Matrix",
            "Ciencia Ficción",
            "1999"
        )
    )
    lista.add(
        Pelicula(
            "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/950/public/media/image/2019/12/jumanji-1995.jpg?itok=wwwML_D8",
            "Jumanji",
            "Aventuras",
            "1999"
        )
    )
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/el-senor-de-los-anillos-el-retorno-del-rey-1119/el-senor-de-los-anillos-el-retorno-del-rey-1119-c.jpg",
            "El señor de los Anillos",
            "Fantasía",
            "2001"
        )
    )
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/las-cronicas-de-narnia-el-leon-la-bruja-y-el-armario-500/las-cronicas-de-narnia-el-leon-la-bruja-y-el-armario-500-c.jpg",
            "Las Crónicas de Narnia",
            "Fantasía",
            "2005"
        )
    )
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/piratas-del-caribe-la-maldicion-de-la-perla-negra-1224/piratas-del-caribe-la-maldicion-de-la-perla-negra-1224-c.jpg",
            "Piratas del Caribe",
            "Aventuras",
            "2003"
        )
    )
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/apocalypse-now-1766/apocalypse-now-1766-c.jpg",
            "Apocalypse Now",
            "Bélica",
            "1979"
        )
    )
    lista.add(
        Pelicula(
            "https://decine21.com/img/upload/obras/centauros-del-desierto-2974/centauros-del-desierto-2974-c.jpg",
            "Centauros del Desierto",
            "Western",
            "1956"
        )
    )

    return lista
}

private fun QuizList(): List<Quiz> {
    val lista = mutableListOf<Quiz>()
    lista.add(
        Quiz(
            "https://cdn.neow.in/news/images/uploaded/2020/02/1582627771_androidstudio_story.jpg",
            "Android Studio",
            "Mobil Apps",
            "1999"
        )
    )
    lista.add(
        Quiz(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/HTML5_logo_and_wordmark.svg/230px-HTML5_logo_and_wordmark.svg.png",
            "Html",
            "Lenguaje Marcas",
            "1992"
        )
    )
    lista.add(
        Quiz(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ9B6iGiWJgAso-MytnEyMFb0HmD7qbYiK-Q&usqp=CAU",
            "CSS",
            "Lenguaje Marcas",
            "1996"
        )
    )
    lista.add(
        Quiz(
            "https://www.silicon.es/wp-content/uploads/2016/10/linux-logo-2-684x500.png",
            "Linux",
            "Programación",
            "1999"
        )
    )
    lista.add(
        Quiz(
            "https://programaenlinea.net/wp-content/uploads/2020/03/visual-studio-2013-logo.png",
            "Visual Studio",
            "Lenguaje Programación",
            "1994"
        )
    )
    lista.add(
        Quiz(
            "https://unity.com/logo-unity-web.png",
            "Unity",
            "Juegos",
            "1998"
        )
    )
    lista.add(
        Quiz(
            "https://cms-assets.tutsplus.com/uploads/users/769/posts/25334/preview_image/get-started-with-laravel-6-400x277.png",
            "Laravel",
            "Web Development",
            "1994"
        )
    )
    lista.add(
        Quiz(
            "https://i.blogs.es/6091fa/java/450_1000.jpg",
            "Java",
            "Lenguaje Programación",
            "1993"
        )
    )
    lista.add(
        Quiz(
            "https://i0.wp.com/unaaldia.hispasec.com/wp-content/uploads/2019/06/django.png?fit=1200%2C800&ssl=1&w=640",
            "Django",
            "Web Framework",
            "1999"
        )
    )
    lista.add(
        Quiz(
            "https://cdn.neow.in/news/images/uploaded/2020/02/1582627771_androidstudio_story.jpg",
            "Matrix",
            "Ciencia Ficción",
            "1999"
        )
    )
    lista.add(
        Quiz(
            "https://noviello.it/content/images/2020/12/php-1.jpg",
            "PHP",
            "Backend",
            "1999"
        )
    )
    lista.add(
        Quiz(
            "https://2.bp.blogspot.com/-14deW6jye1I/XC4wFwitcPI/AAAAAAAAUzw/xKyIF-NcuWM9okIs5sF1pXfWlanTj4Z6gCLcBGAs/s1600/swift-og.png",
            "Swift",
            "Lenguaje programación",
            "2001"
        )
    )
    lista.add(
        Quiz(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Python.svg/1200px-Python.svg.png",
            "Python",
            "Lenguaje Programación",
            "2005"
        )
    )
    lista.add(
        Quiz(
            "https://blog.sosafeapp.com/content/images/2020/04/blog---mi-primera-app-en-Kotlin.png",
            "kotlin",
            "Programación",
            "2003"
        )
    )
    lista.add(
        Quiz(
            "https://www.hostingseguro.co/media/site/images/mysql-logo900.jpg",
            "Mysql",
            "Base de datos",
            "1979"
        )
    )
    lista.add(
        Quiz(
            "https://www.anerbarrena.com/wp-content/uploads/2017/08/programacion-javascript-js-e1503258707880.jpg",
            "JavaScript",
            "Frontend",
            "1956"
        )
    )

    return lista
}

private fun CancionList(): List<Cancion> {
    val lista = mutableListOf<Cancion>()
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.oqVEU84ZuLMdE44Fq9VKfwHaEo?w=292&h=182&c=7&o=5&dpr=1.25&pid=1.7",
            "Héroes del Silencio",
            "Rock",
            "1992"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.5MKLUWIGTxPaE1_CA8S4ggHaEK?w=277&h=180&c=7&o=5&dpr=1.25&pid=1.7",
            "Mike Oldfield",
            "Suspense",
            "1994"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.OzzD1s_AWYd4zS_Os_y2hgHaE8?w=272&h=181&c=7&o=5&dpr=1.25&pid=1.7",
            "The Beatles",
            "Pop",
            "1996"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.X5zlVgjZaGykJVUxqyO9dwAAAA?w=139&h=180&c=7&o=5&dpr=1.25&pid=1.7",
            "Fito y Fitipaldis",
            "Rock",
            "1991"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.6RpUZQ4ZmIIto-YYUTSEmwHaKd?w=132&h=186&c=7&o=5&dpr=1.25&pid=1.7",
            "Muchachito",
            "Flamenco",
            "1994"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.IkR8bR0zxtWMy1wAZawAMQHaK4?w=123&h=181&c=7&o=5&dpr=1.25&pid=1.7",
            "Metalica",
            "Heavy Metal",
            "1998"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.-y0SDuwuLDv26izdMUAD_QHaFL?w=223&h=180&c=7&o=5&dpr=1.25&pid=1.7",
            "Peret",
            "Rumba",
            "1993"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.COi4Hljy-AZOCcr7IZSVCQHaKD?w=152&h=207&c=7&o=5&dpr=1.25&pid=1.7",
            "Bob Marley",
            "Reggae",
            "1999"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.Kui5AiKvdCPhzz__vMmibwHaHa?w=172&h=180&c=7&o=5&dpr=1.25&pid=1.7",
            "La Polla Records",
            "Punk",
            "2001"
        )
    )
    lista.add(
        Cancion(
            "https://th.bing.com/th/id/OIP.fGlKluLSvRvh9oP2QMWnEQHaE_?w=233&h=182&c=7&o=5&dpr=1.25&pid=1.7",
            "Celtas Cortos",
            "Rock",
            "2003"
        )
    )

    return lista
}

private fun LibroList(): List<Libro> {
    val lista = mutableListOf<Libro>()
    lista.add(
        Libro(
            1,
            "https://th.bing.com/th/id/OIP.I2b5msR3WS-oFLREDnBrewHaMq?w=182&h=311&c=7&o=5&dpr=1.25&pid=1.7",
            "El Rey recibe",
            "Policiaca",
            "2018"
        )
    )
    lista.add(
        Libro(
            2,
            "https://www.disco100.com/media/catalog/product/cache/c0af96276a9645a9ee076389055e2eec/0/6/0602537617036.jpg",
            "El Hobbit",
            "Ficción",
            "1984"
        )
    )

    return lista
}

public fun GetPeliculas(): List<Pelicula> {
    return PeliculaList()
}

public fun GetQuiz(): List<Quiz> {
    return QuizList()
}

public fun GetCanciones(): List<Cancion> {
    return CancionList()
}