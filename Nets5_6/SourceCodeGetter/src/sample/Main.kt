package sample

import javafx.application.Application
import kotlin.Throws
import java.lang.Exception
import javafx.stage.Stage
import javafx.scene.Parent
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import kotlin.jvm.JvmStatic

class Main : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("form.fxml"))
        primaryStage.title = "Source Code Getter"
        primaryStage.scene = Scene(root, 600.0, 400.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(*args)
        }
    }
}