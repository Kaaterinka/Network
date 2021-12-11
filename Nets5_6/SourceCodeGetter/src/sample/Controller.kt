package sample

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL

class Controller {
    @FXML
    private val webAddressText: TextField? = null

    @FXML
    private val sourceCode: TextArea? = null

    @get:FXML
    private val sourceCodeButtonClick: Unit
        get() {
            sourceCode!!.text = ""
            try {
                val url = URL(webAddressText!!.text)
                try {
                    val urlConnection = url.openConnection()
                    val reader = BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                    var string = reader.readLine()
                    while (string != null) {
                        sourceCode.text = """
                        ${sourceCode.text}
                        $string
                        """.trimIndent()
                        string = reader.readLine()
                    }
                } catch (e: Exception) {
                    sourceCode.text = "Something wrong!!!"
                    println(e.message)
                }
            } catch (e: Exception) {
                sourceCode.text = "Wrong URL"
                println(e.message)
            }
        }
}