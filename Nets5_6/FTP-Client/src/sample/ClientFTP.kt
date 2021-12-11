package sample

import javafx.application.Application
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.lang.Exception
import java.io.FileOutputStream
import kotlin.Throws
import javafx.scene.Parent
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import kotlin.jvm.JvmStatic

class ClientFTP : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("form.fxml"))
        primaryStage.title = "FTP Client"
        primaryStage.scene = Scene(root, 500.0, 350.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(*args)
        }
    }
}