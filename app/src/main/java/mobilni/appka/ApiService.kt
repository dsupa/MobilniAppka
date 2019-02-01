package mobilni.appka

import android.util.Log
import org.json.JSONObject
import java.net.URL

interface ApiService {

    companion object {

        fun getJoke(): Joke {
            var result: String
            result = URL("https://api.chucknorris.io/jokes/random").readText()
            return parseJsonToJoke(result)
        }

        private fun parseJsonToJoke(json:String):Joke
        {
            val jObject = JSONObject(json)
            val id = jObject.get("id").toString()
            val joke = jObject.get("value").toString()
            val result = Joke(id, joke)
            Log.i("XXX", result.toString())
            return result
        }
    }
}

data class Joke(var id:String, var joke:String)