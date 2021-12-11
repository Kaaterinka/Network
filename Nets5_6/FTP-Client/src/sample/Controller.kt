package sample

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.File
import java.io.FileOutputStream
import org.apache.commons.net.ftp.FTPClient;

class Controller {
    @FXML
    var directoryChooserButton: Button? = null

    @FXML
    var downloadButton: Button? = null

    @FXML
    var resultMessage: Label? = null

    @FXML
    var pathTpFileText: TextField? = null

    @FXML
    var ftpServerUrl: TextField? = null

    @FXML
    var loginField: TextField? = null

    @FXML
    var passwordField: PasswordField? = null

    @FXML
    var directoryText: TextField? = null
    private var currentDirectory: String? = null

    @FXML
    fun directoryChooserButtonClick() {
        try {
            val directoryChooser = DirectoryChooser()
            directoryChooser.initialDirectory = File("C:\\Users\\user\\source\\repos\\Network")
            directoryChooser.title = "Choose the directory to save the file"
            val file = directoryChooser.showDialog(Stage())
            currentDirectory = file.toString()
            directoryText!!.text = currentDirectory
        } catch (e: Exception) {
            println(e.message)
        }
    }

    @FXML
    fun downloadButtonClick() {
        var file: File? = null
        var result = false
        try {
            val fClient = FTPClient()
            val ftpServer = ftpServerUrl!!.text
            val login = loginField!!.text
            val password: String = passwordField!!.text
            val path = pathTpFileText!!.text
            val temp = path.split("/".toRegex()).toTypedArray()
            val fileName = temp[temp.size - 1]
            file = File(currentDirectory, fileName)
            file.createNewFile()
            val fileOutputStream = FileOutputStream(file)
            try {
                fClient.connect(ftpServer)
                fClient.enterLocalPassiveMode()
                fClient.login(login, password)
                if (fClient.retrieveFile(path, fileOutputStream)) {
                    result = true
                }
                fClient.logout()
                fileOutputStream.close()
                fClient.disconnect()
            } catch (ex: Exception) {
                result = false
                System.err.println(ex)
            }
        } catch (e: Exception) {
            result = false
            println(e.message)
        }
        if (result) {
            resultMessage!!.textFill = Color.GREEN
            resultMessage!!.text = "Successful saving"
        } else {
            resultMessage!!.textFill = Color.RED
            resultMessage!!.text = "Unsuccessful saving"
            file?.delete()
        }
    }
}